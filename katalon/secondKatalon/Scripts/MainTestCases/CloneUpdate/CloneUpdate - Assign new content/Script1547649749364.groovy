import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import tvemulation.TVEmulator
try
{
	'Initialize a TVEmulator with initial clone item versions.'
	TVEmulator tvEmulator = new TVEmulator('FZ1A16140605531C5A6BB5C57F', findTestData('TVTestDetails/CloneItemVersions'))
	'Go to CMND and login'
	WebUI.callTestCase(findTestCase('SubTestCases/GoToCmndAndLogin'), [:], FailureHandling.STOP_ON_FAILURE)
	'Upload the clone'
	WebUI.callTestCase(findTestCase('SubTestCases/Upload clone'), [:], FailureHandling.STOP_ON_FAILURE)
	'Send a TVDiscoveryService to make the TV visible in the TVList of CMND.'
	String responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildTVDiscoveryJson()],
		FailureHandling.STOP_ON_FAILURE)
	'Send a IPCloneService to make the firmware and clone version visible in the FW and Clone columns of CMND. '
	responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()],
		FailureHandling.STOP_ON_FAILURE)
	'Assing first clone to first TV'
	WebUI.callTestCase(findTestCase('SubTestCases/Assign the first clone to the first TV'), [:], FailureHandling.STOP_ON_FAILURE)
	'First upgrade attempt, send a IPCloneService JSON with ReadyForUpgrade, to receive response with clone item URLs from CMND.'
	responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()],
		FailureHandling.STOP_ON_FAILURE)
	'Extract the clone item URLs  for the clone items which need to be upgraded by parsing the response of CMND.'
	Map<String, String> cloneItemUrls = CustomKeywords.'webservices.IPCloneServiceParser.extractUrls'(responseText)
	'Load the clone item URLs into the TVEmulator. The TVEmulator will download the clone items and extract the version for the next IPCloneService to CMND.'
	tvEmulator.loadCloneItemsWithUrl(cloneItemUrls)
	WebUI.delay(10)
	'First upgrade attempt, send a IPCloneService JSON with UpgradeInProgress to indicate the upgrade has started to CMND.'
	responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeInProgressJson()],
		FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(15)
	'First upgrade attempt, send a IPCloneService JSON with ReadyForUpgrade and clone session status successfull to indicate a successfull upgrade to CMND.'
	responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()],
		FailureHandling.STOP_ON_FAILURE)

	
	'Upload a Smart Info page'
	WebUI.callTestCase(findTestCase('SubTestCases/Upload smart info'), [:], FailureHandling.STOP_ON_FAILURE)
	'Goes to the clone list'
	WebUI.callTestCase(findTestCase('SubTestCases/GoToCloneList'), [:], FailureHandling.STOP_ON_FAILURE)
	'Select the first clone'
	WebUI.callTestCase(findTestCase('SubTestCases/Select the first clone'), [:], FailureHandling.STOP_ON_FAILURE)
	'Assign the first content'
	WebUI.callTestCase(findTestCase('SubTestCases/Select the first content'), [:], FailureHandling.STOP_ON_FAILURE)
	'Check content name of clone'
	String contentName = WebUI.getText(findTestObject('Page_Smart Install - Manage Files/div_content_first_clone'))
	'Check actual content via regex, which has to start with Holiday Inn and what comes after does not matter'
	WebUI.verifyMatch(contentName, '^Holiday Inn.*', true, FailureHandling.STOP_ON_FAILURE)
	

	'Assing first clone to first TV'
	WebUI.callTestCase(findTestCase('SubTestCases/Assign the first clone to the first TV'), [:], FailureHandling.STOP_ON_FAILURE)
	'Check if clone identifier is blue'
	WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.blueCloneIdentifierColor],
		FailureHandling.CONTINUE_ON_FAILURE)
}
finally { 
    'Delete the uploaded clone, firmware and TV from TVList.'
    WebUI.callTestCase(findTestCase('SubTestCases/CleanUp'), [:], FailureHandling.STOP_ON_FAILURE)
}