//const commonFun = require('common/commonFunction.js')
var generateTvs = 2
//import 'common/commonFunction'

function Send_TVDiscovery_data(tvData) {
    cy.log("Send TVDiscovery, generate a emulator tv")
    cy.wait(3000)
    //console.log("TVDiscoveryData:" + tvData.TVDiscoveryData);
    commonRequest.url = tvData.WebServicesUrl
    commonRequest.body = JSON.stringify(tvData.TVDiscoveryData);
    cy.request(commonRequest).then((resp)=>{
        //console.log("resp:" + JSON.stringify(resp));
        cy.log("send TVDiscovery status:" + resp.status)
        cy.wait(1000)             
    })
}

context('Unit Test', () => {
    beforeEach(function() {
	    cy.log('beforeEach => runs before each test in the block')
        cy.fixture('tvdata.json').as('TVData')
    })


    it('Send Many TVS', () => {
        cy.get('@TVData').then((tvData) => {
            for (let i = 0; i < generateTvs; i++) {
                let ipaddress = generateIpAddress(i);
                console.log("ipaddress:" + ipaddress)
            }
        })
    })
})