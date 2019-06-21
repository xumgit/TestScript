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
import com.kms.katalon.core.testdata.InternalData as InternalData
import tvemulation.CloneItemMoreInfoDialogList as CloneItemMoreInfoDialogList
import tvemulation.CloneItemMoreInfoDialogList.CloneItemMoreInfoDialogItem as CloneItemMoreInfoDialogItem

CloneItemMoreInfoDialogList cloneItemMoreInfoDialogList = cloneItemsMoreInfoDialogDetails

WebUI.callTestCase(findTestCase('SubTestCases/GoToTvList'), [:], FailureHandling.STOP_ON_FAILURE)
''
WebUI.click(findTestObject('Page_SmartInstall - Manage TV  IP s/a_ATX_clone_2k16MS_1_tvinfo'))

WebUI.delay(10)


for (CloneItemMoreInfoDialogItem item : cloneItemMoreInfoDialogList) {
    String cloneItemName = item.cloneItemName

    String expectedtext = item.expectedText

    String expectedTextColor = item.expectedTextCssColor

    String expectedCellColor = item.expectedCellCssColor
	'Translate cloneItemName to testObject of repository'
    String testObjectToFind = getTestObject(cloneItemName)

    TestObject testObject = findTestObject(testObjectToFind)
	
	WebUI.comment('Check text/cell color and content for clone item: ' + cloneItemName)

    checkTextValue(testObject, expectedtext)

    checkTextColor(testObject, expectedTextColor)

    checkCellColor(testObject, expectedCellColor)
}

WebUI.click(findTestObject('Page_SmartInstall - Manage TV  IP s/More Info dialog/button_close'))

WebUI.delay(10)

String checkTextValue(TestObject testObject, String expectedValue) {
    'Get actual text'
    String textValue = WebUI.getText(testObject)
	'Check text'
    WebUI.verifyEqual(textValue, expectedValue, FailureHandling.CONTINUE_ON_FAILURE)
}

String checkTextColor(TestObject testObject, String expectedCssColorValue) {
    'Get actual text color'
    String cssValue = WebUI.getCSSValue(testObject, 'color')
	'Check text color'
    WebUI.verifyEqual(cssValue, expectedCssColorValue, FailureHandling.CONTINUE_ON_FAILURE)
}

String checkCellColor(TestObject testObject, String expectedCssColorValue) {
    'Get actual cell color'
    String cssValue = WebUI.getCSSValue(testObject, 'background-color')
	'Check cell color'
    WebUI.verifyEqual(cssValue, expectedCssColorValue, FailureHandling.CONTINUE_ON_FAILURE)
}

String getTestObject(String cloneItemName) {
    InternalData data = findTestData('CloneItemName2TestObject')

    for (def index : (0..data.getRowNumbers() - 1)) {
        String name = data.internallyGetValue('CloneItemName', index)

        if (name.equals(cloneItemName)) {
            return data.internallyGetValue('MoreInfoTestObject', index)
        }
    }
    
    return null
}

