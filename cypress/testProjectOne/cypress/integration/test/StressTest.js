const {generateTVDiscovery, generateReadyForUpgrade, generateUpGradeInProgress, generateNotInUpgradeMode} = require('../../fixtures/commonFunction.js')
const {Login_CMND_page, Navigation_to_TVS_tv_page, Select_AllTV, Assign_Clone_data, Click_Force_Upgrade_button, checkCloneColor} = require('../../fixtures/cmndFunction.js')

var generateTvs = 3
const selectCloneType = "Channels"
const selectCloneName = "CypressTestCloneData"
var assignIdentifier = "10/06/2019:15:05"


context('Stress Test', () => {
   
    beforeEach(function() {
	    cy.log('beforeEach function')
        cy.fixture('tvdata.json').as('TVData') 
    })

    it('Start', () => { 
        Login_CMND_page();     
        cy.wait(2000);
        Navigation_to_TVS_tv_page();
        cy.wait(2000);
        cy.get('@TVData').then((tvData) => {                               
            generateTVDiscovery(tvData, generateTvs);           
        })
        cy.wait(2000);
        cy.get('@TVData').then((tvData) => { 
            generateReadyForUpgrade(tvData, generateTvs);
        })
        cy.wait(2000);
        Select_AllTV();
        cy.get('@TVData').then((tvData) => {            
            assignIdentifier = Assign_Clone_data(tvData, selectCloneType, selectCloneName);
            cy.wait(3000);
            checkCloneColor("AssignClone", tvData.BlueColor);
        })
        cy.wait(1000);  
        Click_Force_Upgrade_button();
        cy.wait(1000);
        cy.get('@TVData').then((tvData) => { 
            generateUpGradeInProgress(tvData, generateTvs);
            cy.wait(3000);
            checkCloneColor("UpGradeInProgress", tvData.OrangeColor);
        })           
        cy.wait(5000);
        cy.get('@TVData').then((tvData) => { 
            let changeCloneItem = {};
            changeCloneItem[tvData.CloneItems["TVChannelList"]] = assignIdentifier;
            //cy.log("assignIdentifier:" + JSON.stringify(changeCloneItem))
            generateNotInUpgradeMode(tvData, generateTvs, changeCloneItem);
            cy.wait(3000);
            checkCloneColor("NotInUpgradeMode", tvData.GreenColor);
            cy.wait(10000);
        })         
    })
})