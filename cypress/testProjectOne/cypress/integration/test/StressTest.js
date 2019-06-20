const {generateTVDiscovery, generateReadyForUpgrade, generateUpGradeInProgress, generateNotInUpgradeMode} = require('../../fixtures/commonFunction.js')
const {Login_CMND_page, Navigation_to_TVS_tv_page, Select_AllTV, Assign_Clone_data, Click_Force_Upgrade_button, checkCloneColor} = require('../../fixtures/cmndFunction.js')

var generateTvs = 3
const selectCloneType = "Channels"
const selectCloneName = "CypressTestCloneData"
let assignIdentifier = "10/06/2019:15:05"


context('Stress Test', () => {
   
    beforeEach(function() {
	    cy.log('beforeEach function')
        cy.fixture('tvdata.json').as('TVData') 
    })

    it('Start', () => { 
        cy.get('@TVData').then((tvData) => { 
            Login_CMND_page();     
            cy.wait(2000);
            Navigation_to_TVS_tv_page();
            cy.wait(2000);                   
            generateTVDiscovery(tvData, generateTvs);
            cy.wait(2000);
            generateReadyForUpgrade(tvData, generateTvs);
            cy.wait(2000);
            Select_AllTV();
            assignIdentifier = Assign_Clone_data(tvData, selectCloneType, selectCloneName);
            cy.wait(3000);
            checkCloneColor("AssignClone", tvData.BlueColor);
            Click_Force_Upgrade_button();
            generateUpGradeInProgress(tvData, generateTvs);
            cy.wait(3000);
            checkCloneColor("UpGradeInProgress", tvData.OrangeColor);
            cy.wait(10000);
            let changeCloneItem = {};
            changeCloneItem[tvData.CloneItems["TVChannelList"]] = assignIdentifier;
            generateNotInUpgradeMode(tvData, generateTvs, changeCloneItem);
            cy.wait(3000);
            checkCloneColor("NotInUpgradeMode", tvData.GreenColor);
        })
    })
})