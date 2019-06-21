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
import com.google.gson.JsonObject

import tvemulation.*
import internal.GlobalVariable

public class TVDiscoveryServiceBuilder extends WebServiceBuilder{
	public TVDiscoveryServiceBuilder(){
		super(JsonConstants.tvDiscoveryServiceFunValue);
	}

	JsonObject buildTVDiscoveryJson(TVEmulator tvEmulator){
		JsonObject commandDetailsJson = new JsonObject();
		commandDetailsJson.add(JsonConstants.tvDiscoveryParametersKey, buildTVDiscoveryParametersJson(tvEmulator));
		return super.buildResponseServiceJson(tvEmulator.getTvUniqueID(), 10, commandDetailsJson,
				JsonConstants.cookieNumValue, JsonConstants.astaSvcVerValue);
	}

	private JsonObject buildTVDiscoveryParametersJson(TVEmulator tvEmulator){
		JsonObject tvDiscoveryParametersJson = new JsonObject();
		tvDiscoveryParametersJson.addProperty(JsonConstants.powerStatusKey, 		tvEmulator.getPowerStatus());
		tvDiscoveryParametersJson.addProperty(JsonConstants.tvIPAddressKey, 		tvEmulator.getIpAddress());
		tvDiscoveryParametersJson.addProperty(JsonConstants.tvMACAddressKey,		tvEmulator.getMacAddress());
		tvDiscoveryParametersJson.addProperty(JsonConstants.tvModelNumberKey,		tvEmulator.getModelNumber());
		tvDiscoveryParametersJson.addProperty(JsonConstants.tvRoomIDKey, 			tvEmulator.getRoomId());
		tvDiscoveryParametersJson.addProperty(JsonConstants.tvSerialNumberKey,	tvEmulator.getSerialNumber());
		tvDiscoveryParametersJson.addProperty(JsonConstants.vSecureTVIDKey, 		tvEmulator.getVSecureId());
		return tvDiscoveryParametersJson;
	}
}
