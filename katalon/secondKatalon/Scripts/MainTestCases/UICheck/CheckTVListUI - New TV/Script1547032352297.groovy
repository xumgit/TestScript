import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import tvemulation.TVEmulator as TVEmulator

try {
	'Initialize a TVEmulator with initial clone item versions.'
	TVEmulator tvEmulator = new TVEmulator('FZ1A16140605531C5A6BB5C57F', findTestData('TVTestDetails/CloneItemVersions'))

	WebUI.callTestCase(findTestCase('SubTestCases/GoToCmndAndLogin'), [:], FailureHandling.STOP_ON_FAILURE)

	'Send a TVDiscoveryService to make the TV visible in the TVList of CMND.'
	String responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildTVDiscoveryJson()],
		FailureHandling.STOP_ON_FAILURE)

	'Send a IPCloneService to make the firmware and clone version visible in the FW and Clone columns of CMND. '
	responseText = WebUI.callTestCase(findTestCase('SubTestCases/WebServices/SendWebService'), [('httpBody') : tvEmulator.buildUpgradeSuccessfullReadyForUpgradeJson()],
		FailureHandling.STOP_ON_FAILURE)
	
	'Check if clone identifier is black'
	WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier color'), [('css_color') : GlobalVariable.blackCloneIdentifierColor],
		FailureHandling.CONTINUE_ON_FAILURE)
	
	'Check if clone identifier text is None'
	WebUI.callTestCase(findTestCase('SubTestCases/Check clone identifier text'), [('expectedValue') : GlobalVariable.noneCloneIdentifier],
		FailureHandling.CONTINUE_ON_FAILURE)
	
	
	'Check the clone identifier text, text color and cell color of the More Info dialog.'
	WebUI.callTestCase(findTestCase('SubTestCases/Check more info dialog'), [('cloneItemsMoreInfoDialogDetails') : tvEmulator.getCloneItemMoreInfoDialogList()],
		FailureHandling.STOP_ON_FAILURE)
}	
finally {
	'Delete the uploaded clone, firmware and TV from TVList.'
	WebUI.callTestCase(findTestCase('SubTestCases/CleanUp'), [:], FailureHandling.STOP_ON_FAILURE)
}
