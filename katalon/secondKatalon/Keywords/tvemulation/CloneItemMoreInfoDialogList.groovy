package tvemulation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


public class CloneItemMoreInfoDialogList implements Iterable<CloneItemMoreInfoDialogItem>{
	private ArrayList<CloneItemMoreInfoDialogItem> cloneItemList = new ArrayList<>();



	public void addInitialCloneItem (String cloneItemName, String identifier){
		String textColor = GlobalVariable.blackMoreInfoCloneIdentifierColor;
		String cellColor = GlobalVariable.whiteCellColor;
		if(identifier.equalsIgnoreCase("")){
			//empty cell, no identifier
			textColor = GlobalVariable.whiteMoreInfoCloneIdentifierColor
		}
		add(cloneItemName, identifier, textColor, cellColor);
	}

	public void addSuccesfullUpgradedCloneItem (String cloneItemName, String identifier){
		String textColor = GlobalVariable.greenMoreInfoCloneIdentifierColor;
		String cellColor = GlobalVariable.whiteCellColor;
		if(identifier.equalsIgnoreCase("")){
			//empty cell, no identifier
			textColor = GlobalVariable.whiteMoreInfoCloneIdentifierColor
		}
		add(cloneItemName, identifier, textColor, cellColor);
	}

	public void addSuccessfullUpgradedCloneItems(Map<String, String> cloneItemVersions){
		String identifier;
		Set<String> cloneItemNames = cloneItemVersions.keySet();
		for(String cloneItemName: cloneItemNames){
			identifier = cloneItemVersions[cloneItemName];
			this.addSuccesfullUpgradedCloneItem(cloneItemName, identifier);
		}
	}

	void addInitialCloneItems(Map<String, String>cloneItemVersions){
		String identifier;
		Set<String> cloneItemNames = cloneItemVersions.keySet();
		for(String cloneItemName: cloneItemNames){
			identifier = cloneItemVersions[cloneItemName];
			this.addInitialCloneItem(cloneItemName, identifier);
		}
	}

	public void addUnsuccesfullUpgradedCloneItem (String cloneItemName, String identifier){
		String textColor = GlobalVariable.redCloneIdentifierColor;
		String cellColor = GlobalVariable.whiteCellColor;
		if(identifier.equalsIgnoreCase("")){
			//empty cell, no identifier, use cell color
			cellColor = GlobalVariable.redCellColor;
			textColor = GlobalVariable.emptyMoreInfoCloneIdentifierColor;

		}
		add(cloneItemName, identifier, textColor, cellColor);
	}
	public void add(String cloneItemName, String expectedText, String expectedTextCssColor, String expectedCellCssColor){
		CloneItemMoreInfoDialogItem existingItem = getCloneItem(cloneItemName);
		if(null == existingItem){
			cloneItemList.add(new CloneItemMoreInfoDialogItem(cloneItemName, expectedText, expectedTextCssColor, expectedCellCssColor));
		}else{
			existingItem.expectedText = expectedText;
			existingItem.expectedTextCssColor = expectedTextCssColor;
			existingItem.expectedCellCssColor = expectedCellCssColor;
		}
	}
	@Override
	public Iterator<CloneItemMoreInfoDialogItem> iterator() {
		return cloneItemList.iterator();
	}

	String getExpectedText(String cloneItemName){
		return getCloneItem(cloneItemName).expectedText;
	}

	String getExpectedTextCssColor(String cloneItemName){
		return getCloneItem(cloneItemName).expectedTextCssColor;
	}

	String getExpectedCellCssColor(String cloneItemName){
		return getCloneItem(cloneItemName).expectedCellCssColor;
	}
	private CloneItemMoreInfoDialogItem getCloneItem(String cloneItenName){
		for(CloneItemMoreInfoDialogItem item : this.cloneItemList){
			if(item.cloneItemName.equalsIgnoreCase(cloneItenName)){
				return item;
			}
		}
		return null;
	}

	public class CloneItemMoreInfoDialogItem{
		public String cloneItemName;
		public String expectedText;
		public String expectedTextCssColor;
		public String expectedCellCssColor;
		public CloneItemMoreInfoDialogItem(String cloneItemName, String expectedText, String expectedTextCssColor, String expectedCellCssColor){
			this.cloneItemName = cloneItemName;
			this.expectedText = expectedText;
			this.expectedTextCssColor = expectedTextCssColor;
			this.expectedCellCssColor = expectedCellCssColor;
		}
	}
}
