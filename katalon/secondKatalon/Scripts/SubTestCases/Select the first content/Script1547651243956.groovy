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

'Press the assign content button'
WebUI.click(findTestObject('Page_Smart Install - Manage Files/button_Assign content'))

'Wait until the content options are shown, max 30sec'
WebUI.verifyElementPresent(findTestObject('Page_Smart Install - Manage Files/Assign content dialog/img_first_smart_info'),
	30, FailureHandling.STOP_ON_FAILURE)

'Select the for content'
WebUI.click(findTestObject('Page_Smart Install - Manage Files/Assign content dialog/img_first_smart_info'))
WebUI.delay(5)
'Wait untill trash icon of clone is visible, indicating the assign has finished'
WebUI.waitForElementVisible(findTestObject('Page_Smart Install - Manage Files/span_trash_button_latest_clone'), 30, FailureHandling.STOP_ON_FAILURE)