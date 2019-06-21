package webservices

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
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import groovy.json.JsonSlurper
import internal.GlobalVariable
import tvemulation.*

public class IPCloneServiceParser {


	@Keyword
	def testExtractUrls(){
		String ipCloneServiceJson = '''{  "Svc": "WebServices", "SvcVer": "2.0","Cookie": 293, "CmdType": "Change","Fun":"IPCloneService", "CommandDetails" : { "WebServiceParameters" : { "PollingFrequency" : 10,"PollingFrequencyGreen" : 60,"TVUniqueID" : "FZ1A16140605531C5A6BB5C57F"},"IPCloneParameters" :{"CloneItemDownloadDetails": [{"CloneItemDetails" : { "CloneItemName" : "TVSettings","CloneItemVersionNo" : "2018-09-20 16:57:29.0"},"URL" : "http://192.168.103.238:8080/SmartInstall/Profile/Clone/73/TVSettings.zip"},{"CloneItemDetails" : { "CloneItemName" : "TVChannelList","CloneItemVersionNo" : "2018-09-20 16:57:29.0"},"URL" : "http://192.168.103.238:8080/SmartInstall/Profile/Clone/73/ChannelList.zip"},{"CloneItemDetails" : { "CloneItemName" : "WelcomeLogo","CloneItemVersionNo" : "2018-09-20 16:57:29.0"},"URL" : "http://192.168.103.238:8080/SmartInstall/Profile/Clone/73/WelcomeLogo.zip"},{"CloneItemDetails" : { "CloneItemName" : "RoomSpecificSettings","CloneItemVersionNo" : "2018-09-20 16:57:29.0"},"URL" : "http://192.168.103.238:8080/SmartInstall/Profile/Clone/73/RoomSpecificSettings.zip"}]}}}'''
		extractUrls(ipCloneServiceJson)
	}

	@Keyword
	Map<String,String> extractUrls(String ipCloneServiceJson){


		Map<String,String> cloneItemUrls = new HashMap<>()

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(ipCloneServiceJson)


		Map commandDetails = parsedJson.get(JsonConstants.commandDetailsKey)
		Map ipCloneParameters = commandDetails.get(JsonConstants.ipCloneParametersKey)
		ArrayList cloneItemDownloadDetails = ipCloneParameters.get(JsonConstants.cloneItemDownloadDetailsKey)
		for(def cloneItemDownloadDetail : cloneItemDownloadDetails){
			Map cloneItemDetails = cloneItemDownloadDetail.get(JsonConstants.cloneItemDetailsKey)
			String cloneItemName = cloneItemDetails.CloneItemName
			String url = cloneItemDownloadDetail.URL

			cloneItemUrls.put(cloneItemName, url)
		}
		return cloneItemUrls
	}
}
