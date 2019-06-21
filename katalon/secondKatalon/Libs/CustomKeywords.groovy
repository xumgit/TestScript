
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.util.Map

import java.lang.String


def static "tvemulation.CloneItemVersionExtractor.getCloneItemVersions"(
    	java.util.Map<String, String> cloneItemUrls	) {
    (new tvemulation.CloneItemVersionExtractor()).getCloneItemVersions(
        	cloneItemUrls)
}

def static "tvemulation.CloneItemVersionExtractor.getVersion"(
    	String url	
     , 	String cloneItemName	) {
    (new tvemulation.CloneItemVersionExtractor()).getVersion(
        	url
         , 	cloneItemName)
}

def static "webservices.IPCloneServiceParser.testExtractUrls"() {
    (new webservices.IPCloneServiceParser()).testExtractUrls()
}

def static "webservices.IPCloneServiceParser.extractUrls"(
    	String ipCloneServiceJson	) {
    (new webservices.IPCloneServiceParser()).extractUrls(
        	ipCloneServiceJson)
}

def static "webservices.IPCloneServiceBuilder.getCurrentDateTime"() {
    (new webservices.IPCloneServiceBuilder()).getCurrentDateTime()
}
