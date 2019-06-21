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

import internal.GlobalVariable

public class JsonConstants {
	//Property keys
	public static String cmdTypeKey = "CmdType"
	public static String cookieKey = "Cookie"
	public static String funKey = "Fun"
	public static String svcKey = "Svc"
	public static String svcVerKey = "SvcVer"
	public static String commandDetailsKey = "CommandDetails"

	public static String powerStatusKey = "PowerStatus";
	public static String tvIPAddressKey = "TVIPAddress";
	public static String tvMACAddressKey = "TVMACAddress";
	public static String tvModelNumberKey = "TVModelNumber";
	public static String tvRoomIDKey = "TVRoomID";
	public static String tvSerialNumberKey = "TVSerialNumber";
	public static String vSecureTVIDKey = "VSecureTVID";

	public static String tvDiscoveryParametersKey = "TVDiscoveryParameters"

	public static String cloneToServerParametersKey = "CloneToServerParameters"
	public static String cloneItemsAvailableToServerKey = "CloneItemsAvailableToServer"
	public static String cloneToServerSessionStatusKey = "CloneToServerSessionStatus"
	public static String cloneToServerStatusKey = "CloneToServerStatus"

	public static String ipCloneParametersKey = "IPCloneParameters"
	public static String cloneItemsRequiredForUpgradeKey = "CloneItemsRequiredForUpgrade"
	public static String cloneSessionStatusKey = "CloneSessionStatus"
	public static String cloneItemStatusKey = "CloneItemStatus"
	public static String cloneItemDetailsKey = "CloneItemDetails"
	public static String cloneItemNameKey = "CloneItemName"
	public static String cloneItemVersionNoKey = "CloneItemVersionNo"
	public static String cloneStatusKey = "CloneStatus"
	public static String sessionStartTimeKey = "SessionStartTime"
	public static String sessionEndTimeKey = "SessionEndTime"
	public static String sessionStatusKey = "SessionStatus"
	public static String cloneItemDownloadDetailsKey = "CloneItemDownloadDetails"

	public static String currentUpgradeStatusKey = "CurrentUpgradeStatus"

	public static String webServiceParametersKey = "WebServiceParameters"
	public static String pollingFrequencyKey = "PollingFrequency"
	public static String tvUniqueIDKey = "TVUniqueID"

	//Property values
	public static String responseCmdTypeValue = "Response"
	public static int cookieNumValue = 293
	//Possible Fun values
	public static String tvDiscoveryServiceFunValue = "TVDiscoveryService";
	public static String ipCloneServiceFunValue = "IPCloneService"
	//Possible Svc values
	public static String webServicesSvcValue = "WebServices"
	public static String astaSvcVerValue = "3.0"
	//Possible CloneStatus values
	public static String successFulCloneStatusValue = "Successful"
	public static String notAvailableCloneStatusValue = "NotAvailable"

	public static String roomSpecificSettingsValue = "RoomSpecificSettings"
	public static String welcomeLogoValue = "WelcomeLogo"
	public static String tvSettingsValue = "TVSettings"
	public static String mainFirmwareValue = "MainFirmware"
	public static String tvChannelListValue = "TVChannelList"
	public static String smartInfoPagesValue = "SmartInfoPages"
	public static String customDashboardFallbackValue = "CustomDashboardFallback"
	public static String scriptValue = "Script"
	public static String mediaChannelsValue = "MediaChannels"
	public static String androidAppsValue = "AndroidApps"
	public static String smartInfoImagesValue = "SmartInfoImages"

	//Possible SessionStatus values
	public static String inProgressValue = "InProgress"
	//Possible CloneToServerStatus values
	public static String readyStatusValue = "Ready"
	public static String notReadyStatusValue = "NotReady"
	//Possible CurrentUpgradeStatus values
	public static String upgradeInProgressValue = "UpgradeInProgress"
	public static String readyForUpgradeValue = "ReadyForUpgrade"
	//Possible PowerStatus values
	public static String standbyValue = "Standby"
	public static String onValue = "On"

	public static String[] allPossibleCloneItemNameValuesAsta = [
		roomSpecificSettingsValue,
		welcomeLogoValue,
		tvSettingsValue,
		mainFirmwareValue ,
		tvChannelListValue,
		smartInfoPagesValue,
		customDashboardFallbackValue,
		scriptValue,
		mediaChannelsValue,
		androidAppsValue ,
		smartInfoImagesValue
	]//TODO: check if these are all values
}
