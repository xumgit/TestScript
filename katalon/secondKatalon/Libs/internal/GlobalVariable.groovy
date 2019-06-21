package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p>Profile default : IP address CMND server for web services API testing</p>
     */
    public static Object ipAddressCmnd
     
    /**
     * <p></p>
     */
    public static Object portApacheTomcat
     
    /**
     * <p></p>
     */
    public static Object webservicesPath
     
    /**
     * <p></p>
     */
    public static Object orangeCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object blueCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object redCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object blackCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object greenCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object whiteCellColor
     
    /**
     * <p></p>
     */
    public static Object redCellColor
     
    /**
     * <p></p>
     */
    public static Object greenMoreInfoCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object whiteMoreInfoCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object blackMoreInfoCloneIdentifierColor
     
    /**
     * <p></p>
     */
    public static Object FirmwarePath2k16MS
     
    /**
     * <p></p>
     */
    public static Object ClonePath2k16MS
     
    /**
     * <p></p>
     */
    public static Object TempDownloadPath
     
    /**
     * <p></p>
     */
    public static Object noneCloneIdentifier
     
    /**
     * <p></p>
     */
    public static Object smartInfoPath
     
    /**
     * <p></p>
     */
    public static Object emptyMoreInfoCloneIdentifierColor
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            ipAddressCmnd = selectedVariables['ipAddressCmnd']
            portApacheTomcat = selectedVariables['portApacheTomcat']
            webservicesPath = selectedVariables['webservicesPath']
            orangeCloneIdentifierColor = selectedVariables['orangeCloneIdentifierColor']
            blueCloneIdentifierColor = selectedVariables['blueCloneIdentifierColor']
            redCloneIdentifierColor = selectedVariables['redCloneIdentifierColor']
            blackCloneIdentifierColor = selectedVariables['blackCloneIdentifierColor']
            greenCloneIdentifierColor = selectedVariables['greenCloneIdentifierColor']
            whiteCellColor = selectedVariables['whiteCellColor']
            redCellColor = selectedVariables['redCellColor']
            greenMoreInfoCloneIdentifierColor = selectedVariables['greenMoreInfoCloneIdentifierColor']
            whiteMoreInfoCloneIdentifierColor = selectedVariables['whiteMoreInfoCloneIdentifierColor']
            blackMoreInfoCloneIdentifierColor = selectedVariables['blackMoreInfoCloneIdentifierColor']
            FirmwarePath2k16MS = selectedVariables['FirmwarePath2k16MS']
            ClonePath2k16MS = selectedVariables['ClonePath2k16MS']
            TempDownloadPath = selectedVariables['TempDownloadPath']
            noneCloneIdentifier = selectedVariables['noneCloneIdentifier']
            smartInfoPath = selectedVariables['smartInfoPath']
            emptyMoreInfoCloneIdentifierColor = selectedVariables['emptyMoreInfoCloneIdentifierColor']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
