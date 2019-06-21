package tvemulation

import java.util.zip.ZipEntry
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.apache.commons.io.FileUtils
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable
import webservices.*

public class CloneItemVersionExtractor {
	private String upgradeInfoXmlTag = "upgradeinfo"
	private String softwareVersionXmlTag = "softwareversion";
	private String softwareIdentifierPath = "/META-INF/updateinfo/upg.xml";


	@Keyword
	Map<String,String> getCloneItemVersions(Map<String,String> cloneItemUrls){
		Map<String,String> cloneItemVersions = new HashMap<>()
		Set cloneItemNames = cloneItemUrls.keySet()
		String url
		String version
		for(String cloneItemName: cloneItemNames){
			url = cloneItemUrls[cloneItemName]
			version = getVersion(url,cloneItemName)
			cloneItemVersions.put(cloneItemName, version)
		}
		return cloneItemVersions
	}


	@Keyword
	def getVersion(String url, String cloneItemName){
		String destinationFolder = System.getProperty('user.dir') + GlobalVariable.TempDownloadPath
		if(!(new File(destinationFolder)).exists()){
			(new File(destinationFolder)).mkdirs()
		}else{
			deleteAllFilesInDirectory(destinationFolder)
		}
		String filename = extractFilename(url)
		String localUrl = destinationFolder + File.separator + filename
		download(url,localUrl)
		File archive = new File(localUrl)
		unzipArchive(archive, destinationFolder)

		String identifierPath
		String identifier
		if(cloneItemName.equals(JsonConstants.mainFirmwareValue)){
			//Firmware
			identifierPath = new File(destinationFolder + this.softwareIdentifierPath).getAbsolutePath()
			identifier = getFirmwareIdentifier(identifierPath)
		}else{
			//CloneItem
			identifierPath = getCloneItemIdentifierPath(new File(destinationFolder))
			identifier = getCloneIdentifier(identifierPath)
		}

		println "IdentifierPath = " + identifierPath
		println "Identifier = " + identifier
		deleteAllFilesInDirectory(destinationFolder)
		return identifier
	}
	def extractFilename(String url) {
		return url.substring(url.lastIndexOf('/') + 1); //Do not use File.seperator for URLs
	}
	def download(String remoteUrl, String localUrl) {
		def file = new FileOutputStream(localUrl)
		def out = new BufferedOutputStream(file)
		out << new URL(remoteUrl).openStream()
		out.close()
	}
	def unzipArchive(File archive, String destinationFolder) {
		java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(archive)
		ZipEntry zipEntry
		InputStream inputStream
		FileOutputStream fileOutputStream
		BufferedOutputStream bufferedOuputStream
		byte[] buffer
		String zipEntryName
		String filenameToExtractToo

		zipFile.entries().each{
			zipEntry = (ZipEntry)it
			inputStream = zipFile.getInputStream(zipEntry)
			buffer = new byte[(int)zipEntry.getSize()]
			zipEntryName = zipEntry.getName()
			filenameToExtractToo = destinationFolder + File.separator + zipEntryName
			println "zipEntryName = " + zipEntryName + ", filenameToExtractToo = " + filenameToExtractToo // contains path inside zip


			if (zipEntry.isDirectory()){
				File folder = new File(filenameToExtractToo)
				if(!folder.exists()){
					folder.mkdirs()
				}
			}else{
				String filename = extractFilename(filenameToExtractToo)
				String folder = filenameToExtractToo.replace(filename, "")
				if(!(new File(folder)).exists()){
					(new File(folder)).mkdirs()
				}

				fileOutputStream = new FileOutputStream(filenameToExtractToo)

				int len
				if((len = inputStream.read(buffer, 0, buffer.length)) != -1){
					fileOutputStream.write(buffer, 0, len)
					println zipEntry.getName() + " unzipped"
				}else{
					println "Nothing unzipped"
				}
				fileOutputStream.close()

			}
			inputStream.close()
		}
		zipFile.close()
	}

	def getCloneItemIdentifierPath(File searchPath){
		ArrayList<String> matchingPaths = getIdentifierPaths(searchPath)
		if(matchingPaths.size == 1){
			return matchingPaths[0]
		}else{
			throw new Exception("Did not find one identifier file. Matching paths size = " + matchingPaths.size)
		}
	}

	def getIdentifierPaths(File searchPath){
		File[] files = searchPath.listFiles()
		ArrayList<String> matchingPaths = new ArrayList<String>()
		files.each{
			File itFile = (File)it
			if(itFile.isDirectory()){
				matchingPaths.addAll(getIdentifierPaths(itFile))

			}else{
				if(itFile.getName().contains("Identifier")){
					matchingPaths.add(itFile.getAbsolutePath())
				}
			}
		}
		return matchingPaths
	}

	def getCloneIdentifier(String identifierPath){
		File identifierFile = new File(identifierPath)
		return identifierFile.text
	}
	def getFirmwareIdentifier(String identifierPath){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(identifierPath));
		Element rootElement = document.getDocumentElement();
		NodeList nodeList = rootElement.getElementsByTagName(this.softwareVersionXmlTag)
		return nodeList.item(0).getChildNodes().item(0).getNodeValue();
	}
	def deleteAllFilesInDirectory(String path){
		File folder = new File(path)
		int maxNumberOfRetries = 3;
		int deleteCounter = 0
		while (deleteCounter < maxNumberOfRetries){
			try
			{
				FileUtils.cleanDirectory(folder);
				return;
			}catch(IOException ex){
				// folder not released by OS
				deleteCounter++;
				Thread.sleep(5000);
			}
		}
	}
}
