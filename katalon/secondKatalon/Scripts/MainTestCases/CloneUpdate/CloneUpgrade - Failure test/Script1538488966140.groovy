import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import webservices.JsonConstants as JsonConstants
import tvemulation.CloneItemMoreInfoDialogList as CloneItemMoreInfoDialogList
import tvemulation.TVEmulator as TVEmulator

try {
    'Initialize a TVEmulator with initial clone item versions.'
    TVEmulator tvEmulator = new TVEmulator('FZ1A16140605531C5A6BB5C57F', findTestData('TVTestDetails/CloneItemVersions'))

    WebUI.callTestCase(findTestCase('SubTestCases/GoToCmndAndLogin'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('SubTestCases/Upload clone'), [:], FailureHandling.STOP_ON_FAILURE)

    'Send a TVDiscoveryService to make the TV visible in the TVList of CMND.'
    String responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildTVDiscoveryJson()], 
        FailureHandling.STOP_ON_FAILURE)

    'Send a IPCloneService to make the firmware and clone version visible in the FW and Clone columns of CMND. '
    responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()], 
        FailureHandling.STOP_ON_FAILURE)

    'Assign the first clone to the first TV in the TVList'
    WebUI.callTestCase(findTestCase('SubTestCases/Assign the first clone to the first TV'), [:], FailureHandling.STOP_ON_FAILURE)

    'Check if clone identifier is blue'
    WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.blueCloneIdentifierColor], 
        FailureHandling.CONTINUE_ON_FAILURE)

    'First upgrade attempt, send a IPCloneService JSON with ReadyForUpgrade, to receive response with clone item URLs from CMND.'
    responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()], 
        FailureHandling.STOP_ON_FAILURE)

    'Extract the clone item URLs  for the clone items which need to be upgraded by parsing the response of CMND.'
    Map<String, String> cloneItemUrls = CustomKeywords.'webservices.IPCloneServiceParser.extractUrls'(responseText)

    'Load the clone item URLs into the TVEmulator. The TVEmulator will download the clone items and extract the version for the next IPCloneService to CMND.'
    tvEmulator.loadCloneItemsWithUrl(cloneItemUrls)

    'Create an array of expected clone item URLs from CMND. Expected URLs are for a RoomSpecific, TVSettings, TVChannelList, WelcomeLogo, MediaChannel and SmartInfoPages clone item.'
    ArrayList<String> expectedCloneItemsWithUrl = new ArrayList<String>(Arrays.asList(JsonConstants.roomSpecificSettingsValue, 
        JsonConstants.tvSettingsValue, JsonConstants.tvChannelListValue, JsonConstants.welcomeLogoValue, JsonConstants.mediaChannelsValue, 
        JsonConstants.smartInfoPagesValue))

    'Compare received clone item URLs with expected clone item URLs.'
    WebUI.callTestCase(findTestCase('SubTestCases/Check URLs'), [('cloneItemUrlMap') : cloneItemUrls, ('expectedCloneItemWithUrlList') : expectedCloneItemsWithUrl], 
        FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.delay(10)

    'First upgrade attempt, send a IPCloneService JSON with UpgradeInProgress to indicate the upgrade has started to CMND.'
    responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeInProgressJson()], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(5)

    'Check if clone identifier is orange'
    WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.orangeCloneIdentifierColor], 
        FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.delay(10)

    'First upgarde attempt, change the TVChannelList clone item version back to the initial version, to simulate the TVChannelList was not changed and thus the clone item upgrade failed.'
    tvEmulator.setUnsuccessfullCloneItemUpgrade(JsonConstants.tvChannelListValue, '26/06/2018:14:10')

    'First upgarde attempt, change the MediaChannels clone item version back to the initial version, which is empty, to simulate the MediaChannels were not changed and still not preset and thus the clone item upgrade failed.'
    tvEmulator.setUnsuccessfullCloneItemUpgrade(JsonConstants.mediaChannelsValue, '')

    'Second and third upgrade attempts, the upgrades must fail due to the incorrect clone item identifiers.'
    for (int retryCounter = 0; retryCounter < 2; retryCounter++) {
        'Second and thrid upgrade attempt, send a IPCloneService JSON with ReadyForUpgrade and with incorrect clone item identifiers, to receive response with clone item URLs from CMND.'
        responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()], 
            FailureHandling.STOP_ON_FAILURE)

        'Extract the clone item URLs  for the clone items which need to be upgraded by parsing the response of CMND.'
        cloneItemUrls = CustomKeywords.'webservices.IPCloneServiceParser.extractUrls'(responseText)

        'Create an array of expected clone item URLs from CMND. Expected URLs are for a TVChannelList and MediaChannel clone item.'
        expectedCloneItemsWithUrl = new ArrayList<String>(Arrays.asList(JsonConstants.tvChannelListValue, JsonConstants.mediaChannelsValue))

        'Compare received clone item URLs with expected clone item URLs.'
        WebUI.callTestCase(findTestCase('SubTestCases/Check URLs'), [('cloneItemUrlMap') : cloneItemUrls, ('expectedCloneItemWithUrlList') : expectedCloneItemsWithUrl], 
            FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.delay(10)

        'Second and thrid upgrade attempt, send a IPCloneService JSON with UpgradeInProgress to indicate the upgrade has started to CMND.'
        responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeInProgressJson()], 
            FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(5)

        'Check if clone identifier is orange'
        WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.orangeCloneIdentifierColor], 
            FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    'Fourth and fifth upgrade attempts, the CMND must not share the clone item URLs anymore and the upgarde must be marked as failed as the retry counter maximum has been reached.'
    for (int retryCounter = 0; retryCounter < 2; retryCounter++) {
        'Fourth and fifth upgrade attempt, send a IPCloneService JSON with ReadyForUpgrade and with incorrect clone item identifiers, to receive response with clone item URLs from CMND.'
        responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()], 
            FailureHandling.STOP_ON_FAILURE)

        'Extract the URLs for the clone items which need to be upgraded by parsing the response of CMND'
        cloneItemUrls = CustomKeywords.'webservices.IPCloneServiceParser.extractUrls'(responseText)

        'Create an array of expected clone item URLs from CMND. There are no URLs expected.'
        expectedCloneItemsWithUrl = new ArrayList<String>()

        'Compare received clone item URLs with expected clone item URLs.'
        WebUI.callTestCase(findTestCase('SubTestCases/Check URLs'), [('cloneItemUrlMap') : cloneItemUrls, ('expectedCloneItemWithUrlList') : expectedCloneItemsWithUrl], 
            FailureHandling.CONTINUE_ON_FAILURE)

        'Check if clone identifier is red'
        WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.redCloneIdentifierColor], 
            FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    'Check the clone identifier text, text color and cell color of the More Info dialog.'
    WebUI.callTestCase(findTestCase('SubTestCases/Check more info dialog'), [('cloneItemsMoreInfoDialogDetails') : tvEmulator.getCloneItemMoreInfoDialogList()], 
        FailureHandling.STOP_ON_FAILURE)
}
finally { 
    'Delete the uploaded clone, firmware and TV from TVList.'
    WebUI.callTestCase(findTestCase('SubTestCases/CleanUp'), [:], FailureHandling.STOP_ON_FAILURE)
}

