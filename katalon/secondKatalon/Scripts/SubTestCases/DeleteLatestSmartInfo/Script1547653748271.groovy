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

WebUI.callTestCase(findTestCase('SubTestCases/GoToCreate'), [:], FailureHandling.STOP_ON_FAILURE)

'Mouse over the SmartInfo to display the trash icon'
WebUI.mouseOver(findTestObject('Page_Websites overview  CMND CMS/img_imported_smartinfo'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_Websites overview  CMND CMS/a_trash_icon_first_smart_info'))
WebUI.delay(5)
'Press OK button of the are you sure dialog'
WebUI.click(findTestObject('Page_Websites overview  CMND CMS/Delete dialog/input_delete_confirm'))

'Wait untill the delete dialog was disappeared, max 30sec'
WebUI.verifyElementNotPresent(findTestObject('Page_Websites overview  CMND CMS/Delete dialog/input_delete_confirm'), 30, FailureHandling.STOP_ON_FAILURE)