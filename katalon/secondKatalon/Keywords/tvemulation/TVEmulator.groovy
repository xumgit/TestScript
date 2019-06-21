package tvemulation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import webservices.*

public class TVEmulator {
	//Members
	private String tvUniqueID;
	private String powerStatus;
	private String ipAddress;
	private String macAddress;
	private String modelNumber;
	private String roomId;
	private String serialNumber;
	private String vSecureId;

	private String sessionStartTime;
	private String sessionEndTime;
	private Map<String, String> cloneItemVersions; //CloneItemVersions map which is send to CMND via a JSON
	private CloneItemMoreInfoDialogList cloneItemMoreInfoDialogList; //CloneItemMoreInfoDialogList map which is used to check the text and colors of the more info dialog

	//Setters

	//Getters
	public String getTvUniqueID(){return this.tvUniqueID; }
	public String getPowerStatus(){return this.powerStatus;}
	public String getIpAddress(){return this.ipAddress;}
	public String getMacAddress(){return this.macAddress;}
	public String getModelNumber(){return this.modelNumber;}
	public String getRoomId(){return this.roomId;}
	public String getSerialNumber(){return this.serialNumber;}
	public String getVSecureId(){return this.vSecureId;}

	public String getSessionStartTime(){ return this.sessionStartTime; }
	public String getSessionEndTime(){ return this.sessionEndTime; }
	public Map<String, String> getCloneItemVersions(){ return this.cloneItemVersions; }
	public CloneItemMoreInfoDialogList getCloneItemMoreInfoDialogList(){return this.cloneItemMoreInfoDialogList}

	//Constructors
	public TVEmulator(String tvUniqueID, InternalData initialCloneItemsVersion){
		this(
		tvUniqueID,
		JsonConstants.standbyValue,
		"192.168.103.159",
		"1C:5A:6B:B5:C5:7F",
		"32HFL5011T/12",
		"00017",
		"FZ1A1614060553",
		"800000000000000F4F91",
		new IPCloneServiceBuilder().getCurrentDateTime(),
		new IPCloneServiceBuilder().getCurrentDateTime(),
		initialCloneItemsVersion
		);
	}
	public TVEmulator(String tvUniqueID, String powerStatus, String ipAddress, String macAddress, String modelNumber,
	String roomId, String serialNumber, String vSecureId, String sessionStartTime, String sessionEndTime,
	InternalData initialCloneItemsVersion){
		this.tvUniqueID = tvUniqueID;
		this.powerStatus = powerStatus;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.modelNumber = modelNumber;
		this.roomId = roomId;
		this.serialNumber = serialNumber;
		this.vSecureId = vSecureId;

		this.sessionStartTime = sessionStartTime;
		this.sessionEndTime = sessionEndTime;
		this.cloneItemMoreInfoDialogList = new CloneItemMoreInfoDialogList();
		this.cloneItemVersions = getCloneItemVersionsFromTestData(initialCloneItemsVersion);
		this.cloneItemMoreInfoDialogList.addInitialCloneItems(this.cloneItemVersions);
	}

	//Functions
	private Map<String, String> getCloneItemVersionsFromTestData(InternalData cloneItemsVersion){
		Map<String, String> cloneItemVersions = new HashMap();
		for (def index : (0..cloneItemsVersion.getRowNumbers() - 1)) {
			String name = cloneItemsVersion.internallyGetValue("CloneItemName", index);
			String version = cloneItemsVersion.internallyGetValue("Version", index);
			cloneItemVersions.put(name, version);
		}
		return cloneItemVersions;
	}

	public void loadCloneItemsWithUrl(Map<String, String> cloneItemUrls){
		//Extract the identifiers of the clone items by downloading the clone items from the URL
		CloneItemVersionExtractor versionExtractor = new CloneItemVersionExtractor();
		Map<String,String> newCloneItemVersions = versionExtractor.getCloneItemVersions(cloneItemUrls);
		this.cloneItemMoreInfoDialogList.addSuccessfullUpgradedCloneItems(newCloneItemVersions);
		this.cloneItemVersions.putAll(newCloneItemVersions);
	}
	public void setUnsuccessfullCloneItemUpgrade(String cloneItemName, String newIncorrectVersion){
		this.cloneItemMoreInfoDialogList.addUnsuccesfullUpgradedCloneItem(cloneItemName, newIncorrectVersion);
		this.cloneItemVersions[cloneItemName] = newIncorrectVersion;
	}
	private void setSessionStartTimeToCurrent(){ this.sessionStartTime = new IPCloneServiceBuilder().getCurrentDateTime(); }
	private void setSessionEndTimeToCurrent(){ this.sessionEndTime = new IPCloneServiceBuilder().getCurrentDateTime(); }

	public String buildUpgradeSuccessfullReadyForUpgradeJson(){
		IPCloneServiceBuilder builder = new IPCloneServiceBuilder();
		setSessionEndTimeToCurrent();
		return builder.buildUpgradeSuccessfullReadyForUpgradeJson(this).toString();
	}
	public String buildUpgradeInProgressJson(){
		IPCloneServiceBuilder builder = new IPCloneServiceBuilder();
		setSessionStartTimeToCurrent();
		return builder.buildUpgradeInProgressJson(this).toString();
	}
	public String buildTVDiscoveryJson(){
		TVDiscoveryServiceBuilder builder = new TVDiscoveryServiceBuilder();
		return builder.buildTVDiscoveryJson(this).toString();
	}
}