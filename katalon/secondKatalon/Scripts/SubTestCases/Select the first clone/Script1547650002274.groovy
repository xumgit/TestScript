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
 

TestObject checkboxFirstClone = findTestObject(	'Page_Smart Install - Manage Files/input_Action_select_first_clone')

'Wait untill clones are visible, max 30sec'
WebUI.verifyElementPresent(checkboxFirstClone, 30, FailureHandling.STOP_ON_FAILURE)

'Check if the first clone is already selected'
isSelected = WebUI.verifyElementChecked(checkboxFirstClone, 30, FailureHandling.OPTIONAL)

if (!(isSelected)) {
	'Clone if not selected, select clone'
	WebUI.click(findTestObject('Page_Smart Install - Manage Files/input_Action_select_first_clone'))
}