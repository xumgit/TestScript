const {generateTVDiscovery, generateReadyForUpgrade, generateUpGradeInProgress, generateNotInUpgradeMode} = require('../../fixtures/commonFunction.js')
const {Login_CMND_page, Navigation_to_TVS_tv_page, Select_AllTV} = require('../../fixtures/cmndFunction.js')
var generateTvs = 5



context('Stress Test', () => {
    beforeEach(function() {
	    cy.log('beforeEach => runs before each test in the block')
        cy.fixture('tvdata.json').as('TVData')
    })

    it('Open CMND Page and Login', () => {
        //Login_CMND_page();
        //cy.wait(2000);
        //Navigation_to_TVS_tv_page();
    })

    it('Genarate Many TVs', () => {
        cy.wait(2000);
        cy.get('@TVData').then((tvData) => {
            let changeCloneItem = {};
            changeCloneItem[tvData.CloneItems["TVChannelList"]] = "01/06/2019:08:05";
            generateTVDiscovery(tvData, generateTvs);
        })
    })

    it('Send ReadForUpgrade for display SW version', () => {
        cy.wait(2000);
        cy.get('@TVData').then((tvData) => {
            generateReadyForUpgrade(tvData, generateTvs);
        })
    })

    it('Select AllTV', () => {
        //cy.wait(2000);
        //Select_AllTV();
    })
})