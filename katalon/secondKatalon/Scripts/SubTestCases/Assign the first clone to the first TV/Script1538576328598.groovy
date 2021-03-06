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

WebUI.callTestCase(findTestCase('SubTestCases/GoToTvList'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SubTestCases/Select the first TV'), [:], FailureHandling.STOP_ON_FAILURE)

'Open the clone dropdown list'
WebUI.click(findTestObject('Page_SmartInstall - Manage TV  IP s/button_None_CloneDropDownList'))

WebUI.delay(5)

'Select the first clone of the list'
WebUI.click(findTestObject('Page_SmartInstall - Manage TV  IP s/span_ATX_clone_2k16MS_0_from_CloneDropDownList'))

WebUI.delay(5)

'Deselect TV'
WebUI.click(findTestObject('Page_SmartInstall - Manage TV  IP s/input_Actions_select_first_tv'))

WebUI.delay(10)

