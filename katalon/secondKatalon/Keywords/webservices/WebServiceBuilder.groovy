package webservices

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
import tvemulation.*

public class WebServiceBuilder {
	private String functionName;

	public WebServiceBuilder(String functionName){
		this.functionName = functionName;
	}

	protected JsonObject buildResponseServiceJson(String tvUniqueId, int pollingFrequency, JsonObject commandDetails,
			int cookieNum, String svrVer){
		JsonObject rootJson = new JsonObject();
		commandDetails.add(JsonConstants.webServiceParametersKey, buildWebServiceParameters(pollingFrequency, tvUniqueId))
		rootJson.add(JsonConstants.commandDetailsKey, commandDetails)
		rootJson.addProperty(JsonConstants.cmdTypeKey, JsonConstants.responseCmdTypeValue)
		rootJson.addProperty(JsonConstants.cookieKey, cookieNum)
		rootJson.addProperty(JsonConstants.funKey, this.functionName)
		rootJson.addProperty(JsonConstants.svcKey, JsonConstants.webServicesSvcValue)
		rootJson.addProperty(JsonConstants.svcVerKey, svrVer)
		return rootJson;
	}

	private JsonObject buildWebServiceParameters(int pollingFrequency, String tvUniqueID){
		JsonObject webServiceParametersJson = new JsonObject()
		webServiceParametersJson.addProperty(JsonConstants.pollingFrequencyKey, pollingFrequency)
		webServiceParametersJson.addProperty(JsonConstants.tvUniqueIDKey, tvUniqueID)
		return webServiceParametersJson
	}
}
