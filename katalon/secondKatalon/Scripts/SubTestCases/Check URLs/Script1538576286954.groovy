import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

KeywordLogger log = new KeywordLogger()

Map<String, String> cloneItemUrls = cloneItemUrlMap.clone()

ArrayList<String> expectedCloneItemsWithUrl = expectedCloneItemWithUrlList.clone()

'Check if number of expected URLs matches with number of received URLs.'
if (!(WebUI.verifyEqual(cloneItemUrls.size(), expectedCloneItemsWithUrl.size(), FailureHandling.CONTINUE_ON_FAILURE))) {
    log.logInfo((('Number of expected clone items with an URL does not match with received clone items URLs. Expected = ' + 
        expectedCloneItemsWithUrl.size()) + ', received = ') + cloneItemUrls.size())

    printCloneItemNamesArray(expectedCloneItemsWithUrl, log, 'Expected clone items with a URL:')

    printCloneItemNamesArray(cloneItemUrls.keySet(), log, 'Received clone items with a URL:')
}

for (String cloneItemNameWithUrl : cloneItemUrls.keySet()) {
    'Check if URL for expected clone item is received.'
    if (expectedCloneItemsWithUrl.contains(cloneItemNameWithUrl)) {
        expectedCloneItemsWithUrl.remove(cloneItemNameWithUrl)
    }
}

'Check if expected URLs were not received.'
if (!(WebUI.verifyEqual(expectedCloneItemsWithUrl.size(), 0, FailureHandling.CONTINUE_ON_FAILURE))) {
    log.logInfo('Certian expected clone items with an URL were not received.')

    printCloneItemNamesArray(expectedCloneItemsWithUrl, log, 'Missing clone items with a URL:')

    printCloneItemNamesArray(cloneItemUrls.keySet(), log, 'Received clone items with a URL:')
}

def printCloneItemNamesArray(def expectedCloneItems, KeywordLogger log, String startMessage) {
    String message = startMessage

    for (String cloneItem : expectedCloneItems) {
        message = ((message + ' ') + cloneItem)
    }
    
    log.logInfo(message)
}

