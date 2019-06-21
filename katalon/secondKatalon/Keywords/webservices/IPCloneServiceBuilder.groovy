package webservices

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
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

import internal.GlobalVariable
import java.text.SimpleDateFormat
import tvemulation.*

public class IPCloneServiceBuilder extends WebServiceBuilder {

	public IPCloneServiceBuilder(){
		super(JsonConstants.ipCloneServiceFunValue);
	}


	JsonObject buildUpgradeSuccessfullReadyForUpgradeJson(TVEmulator tvEmulator){
		String tvUniqueId = tvEmulator.getTvUniqueID();
		String sessionStartTime = tvEmulator.getSessionStartTime();
		String sessionEndTime = tvEmulator.getSessionEndTime();
		Map<String, String> cloneItemVersions = tvEmulator.getCloneItemVersions();

		String cloneToServerSessionStartTime = ""
		String cloneToServerSessionEndTime = ""
		String cloneToServerSessionStatus = ""
		String cloneToServerStatus = JsonConstants.readyStatusValue
		//String sessionStartTime = getCurrentDateTime()
		//String sessionEndTime = getCurrentDateTime()
		String sessionStatus = JsonConstants.successFulCloneStatusValue
		String currentUpgradeStatus = JsonConstants.readyForUpgradeValue


		return buildIpCloneServiceJson(	cloneItemVersions,
				cloneToServerSessionStartTime, cloneToServerSessionEndTime, cloneToServerSessionStatus,
				cloneToServerStatus,
				sessionStartTime, sessionEndTime, sessionStatus,
				currentUpgradeStatus, tvUniqueId, JsonConstants.cookieNumValue, JsonConstants.astaSvcVerValue);
	}

	JsonObject buildUpgradeInProgressJson(TVEmulator tvEmulator){
		String tvUniqueId = tvEmulator.getTvUniqueID();
		String sessionStartTime = tvEmulator.getSessionStartTime();
		Map<String, String> cloneItemVersions = tvEmulator.getCloneItemVersions();

		String cloneToServerSessionStartTime = ""
		String cloneToServerSessionEndTime = ""
		String cloneToServerSessionStatus = ""
		String cloneToServerStatus = JsonConstants.notReadyStatusValue
		//String sessionStartTime = getCurrentDateTime()
		String sessionEndTime = ""
		String sessionStatus = JsonConstants.inProgressValue
		String currentUpgradeStatus = JsonConstants.upgradeInProgressValue


		return buildIpCloneServiceJson(	cloneItemVersions,
				cloneToServerSessionStartTime, cloneToServerSessionEndTime, cloneToServerSessionStatus,
				cloneToServerStatus,
				sessionStartTime, sessionEndTime, sessionStatus,
				currentUpgradeStatus, tvUniqueId, JsonConstants.cookieNumValue, JsonConstants.astaSvcVerValue);
	}

	@Keyword
	def String getCurrentDateTime(){
		def date = new Date()
		def sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm")
		return sdf.format(date)
	}

	private JsonObject buildIpCloneServiceJson(Map<String, String> cloneItemVersions,
			String cloneToServerSessionStartTime,String cloneToServerSessionEndTime, String cloneToServerSessionStatus,
			String cloneToServerStatus,
			String sessionStartTime,String sessionEndTime, String sessionStatus,
			String currentUpgradeStatus, String tvUniqueId, int cookieNum, String svrVer){

		JsonObject commandDetailsJson = new JsonObject()


		commandDetailsJson.add(JsonConstants.cloneToServerParametersKey, buildCloneToServerParametersJson(cloneItemVersions,
				cloneToServerSessionStartTime,
				cloneToServerSessionEndTime,
				cloneToServerSessionStatus,
				cloneToServerStatus))
		commandDetailsJson.add(JsonConstants.ipCloneParametersKey, buildIPCloneParameters(cloneItemVersions,
				sessionStartTime,
				sessionEndTime,
				sessionStatus,
				currentUpgradeStatus))
		return super.buildResponseServiceJson(tvUniqueId, 10, commandDetailsJson, cookieNum, svrVer);
	}

	private JsonObject buildCloneToServerParametersJson(Map<String, String> cloneItemVersions, String sessionStartTime,String sessionEndTime, String sessionStatus, String cloneToServerStatus){
		JsonObject cloneToServerParametersJson = new JsonObject()
		cloneToServerParametersJson.add(JsonConstants.cloneItemsAvailableToServerKey, buildCloneItemsAvailableToServerJsonArray(cloneItemVersions))
		cloneToServerParametersJson.add(JsonConstants.cloneToServerSessionStatusKey, buildCloneToServerSessionStatusJson(cloneItemVersions, sessionStartTime, sessionEndTime, sessionStatus))
		cloneToServerParametersJson.addProperty(JsonConstants.cloneToServerStatusKey, cloneToServerStatus)
		return cloneToServerParametersJson
	}
	private JsonArray buildCloneItemsAvailableToServerJsonArray(Map<String, String> cloneItemVersions){
		//Same as buildCloneItemsRequiredForUpgradeJsonArray but without firmware
		//TODO does contain DataDump
		return removeCloneItemFromCloneItemJsonArray(buildCloneItemsRequiredForUpgradeJsonArray(cloneItemVersions), JsonConstants.mainFirmwareValue)
	}
	private JsonObject buildCloneToServerSessionStatusJson(Map<String, String> cloneItemVersions, String sessionStartTime,String sessionEndTime, String sessionStatus){
		JsonObject cloneToServerSessionStatusJson = new JsonObject()
		cloneToServerSessionStatusJson.add(JsonConstants.cloneItemStatusKey, buildCloneToServerCloneItemStatusJsonArray(cloneItemVersions))
		cloneToServerSessionStatusJson.addProperty(JsonConstants.sessionStartTimeKey, sessionStartTime)
		cloneToServerSessionStatusJson.addProperty(JsonConstants.sessionEndTimeKey, sessionEndTime)
		cloneToServerSessionStatusJson.addProperty(JsonConstants.sessionStatusKey, sessionStatus)
		return cloneToServerSessionStatusJson
	}
	private JsonArray buildCloneToServerCloneItemStatusJsonArray(Map<String, String> cloneItemVersions){
		//Same as buildCloneItemStatusJsonArray but without firmware
		return removeCloneItemFromCloneItemStatusJsonArray(buildCloneItemStatusJsonArray(cloneItemVersions), JsonConstants.mainFirmwareValue)
	}

	private JsonArray removeCloneItemFromCloneItemJsonArray(JsonArray cloneItemArray, String cloneItemName){
		JsonArray returnArray = new JsonArray()
		for(JsonObject cloneItemDetails: cloneItemArray){
			if(!isCloneItemNameEqual(cloneItemDetails, cloneItemName)){
				returnArray.add(cloneItemDetails)
			}
		}
		return returnArray
	}
	private JsonArray removeCloneItemFromCloneItemStatusJsonArray(JsonArray cloneItemStatusArray, String cloneItemName){
		JsonArray returnArray = new JsonArray()
		for(JsonObject cloneItemStatus: cloneItemStatusArray){
			JsonObject cloneItemDetails = cloneItemStatus.get(JsonConstants.cloneItemDetailsKey)
			if(!isCloneItemNameEqual(cloneItemDetails,cloneItemName)){
				returnArray.add(cloneItemStatus)
			}
		}
		return returnArray
	}
	private boolean isCloneItemNameEqual(JsonObject cloneItemDetails, String cloneItemName){
		return (cloneItemDetails.get(JsonConstants.cloneItemNameKey).toString().equals('"' + cloneItemName + '"'))
	}

	private JsonObject buildIPCloneParameters(Map<String, String> cloneItemVersions, String sessionStartTime, String sessionEndTime, String sessionStatus, String currentUpgradeStatus){
		JsonObject ipCloneParametersJson = new JsonObject()
		ipCloneParametersJson.add(JsonConstants.cloneItemsRequiredForUpgradeKey, buildCloneItemsRequiredForUpgradeJsonArray(cloneItemVersions))
		ipCloneParametersJson.add(JsonConstants.cloneSessionStatusKey, buildCloneSessionStatusJson(cloneItemVersions, sessionStartTime, sessionEndTime, sessionStatus))
		ipCloneParametersJson.addProperty(JsonConstants.currentUpgradeStatusKey, currentUpgradeStatus)
		return ipCloneParametersJson
	}
	private JsonArray buildCloneItemsRequiredForUpgradeJsonArray(Map<String, String> cloneItemVersions){
		JsonArray cloneItemsRequiredForUpgradeJsonArray = new JsonArray()
		JsonObject cloneItemsRequiredForUpgradeJson
		Set cloneItemNames = cloneItemVersions.keySet()
		String cloneItemVersionNo
		for(String cloneItemName: cloneItemNames){
			cloneItemsRequiredForUpgradeJson = new JsonObject()
			cloneItemVersionNo = cloneItemVersions[cloneItemName]
			cloneItemsRequiredForUpgradeJson.addProperty(JsonConstants.cloneItemNameKey, cloneItemName)
			cloneItemsRequiredForUpgradeJson.addProperty(JsonConstants.cloneItemVersionNoKey, cloneItemVersionNo)
			cloneItemsRequiredForUpgradeJsonArray.add(cloneItemsRequiredForUpgradeJson)
		}
		return cloneItemsRequiredForUpgradeJsonArray
	}

	private JsonObject buildCloneSessionStatusJson(Map<String, String> cloneItemVersions, String sessionStartTime, String sessionEndTime, String sessionStatus){
		JsonObject cloneSessionStatusJson = new JsonObject()
		cloneSessionStatusJson.add(JsonConstants.cloneItemStatusKey, buildCloneItemStatusJsonArray(cloneItemVersions))
		cloneSessionStatusJson.addProperty(JsonConstants.sessionStartTimeKey, sessionStartTime)
		cloneSessionStatusJson.addProperty(JsonConstants.sessionEndTimeKey, sessionEndTime)
		cloneSessionStatusJson.addProperty(JsonConstants.sessionStatusKey, sessionStatus)
		return cloneSessionStatusJson
	}
	private JsonArray buildCloneItemStatusJsonArray(Map<String, String> cloneItemVersions){
		JsonArray cloneItemStatusJsonArray = new JsonArray()
		JsonObject cloneItemStatusJson
		JsonObject cloneItemDetailsJson

		Set cloneItemNames = cloneItemVersions.keySet()
		String cloneItemVersionNo
		String cloneStatus
		for(String possibleCloneItemName: JsonConstants.allPossibleCloneItemNameValuesAsta){
			cloneItemStatusJson = new JsonObject()
			cloneItemDetailsJson = new JsonObject()

			if(cloneItemNames.contains(possibleCloneItemName)){
				cloneItemVersionNo = cloneItemVersions[possibleCloneItemName]
				cloneStatus = "Successful"
			}else{
				cloneItemVersionNo = ""
				cloneStatus = "NotAvailable"
			}
			cloneItemDetailsJson.addProperty(JsonConstants.cloneItemNameKey, possibleCloneItemName)
			cloneItemDetailsJson.addProperty(JsonConstants.cloneItemVersionNoKey, cloneItemVersionNo)
			cloneItemStatusJson.add(JsonConstants.cloneItemDetailsKey, cloneItemDetailsJson)
			cloneItemStatusJson.addProperty(JsonConstants.cloneStatusKey, cloneStatus)
			cloneItemStatusJsonArray.add(cloneItemStatusJson)
		}

		return cloneItemStatusJsonArray
	}
}