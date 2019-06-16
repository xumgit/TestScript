package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object url
     
    /**
     * <p></p>
     */
    public static Object macurl
     
    /**
     * <p></p>
     */
    public static Object testAPI1
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['url' : 'C:\\\\eclipse\\\\KatalonProject\\\\firstKatalon\\\\testHtml\\\\test.html', 'macurl' : 'Users\\\\apple\\\\Desktop\\\\codeReview\\\\KatalonProject\\\\firstKatalon\\\\testHtml\\\\test.html', 'testAPI1' : 'http://localhost:8080/angularjs/deletedata'])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        url = selectedVariables['url']
        macurl = selectedVariables['macurl']
        testAPI1 = selectedVariables['testAPI1']
        
    }
}
