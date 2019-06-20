
const commonRequest = {
    method: 'POST',
    form: false,
    headers: {
        "Authorization": "whateverYouNeedForAuthentication",
        "Content-Type": "application/json",
        "Accept": "application/json"
    },
    url: null,
    body: null
}

var assignIdentifier = "10/06/2019:15:05"

function Login_CMND_page() {
    cy.log("Login CMND page")
    cy.visit('/')
    cy.wait(1000)
    cy.get('#username', {timeout: 5000}).type("admin")
    cy.get('#password', {timeout: 5000}).type("tpvision")
    cy.wait(1000)
    cy.get('.btn').click()
    cy.wait(3000)
}

function Navigation_to_TVS_tv_page() {
    cy.log("Navigation to TVS_tv page")
    cy.wait(3000)
    cy.get('#nav_tvs')
        .as('mainNaviTVs')
        //.parent('li')
        //.should('not.have.class', 'active')
        .then(($parentDom) => {
            cy.wait(3000)
            if (!$parentDom.parent().hasClass('active')) {
                cy.get('@mainNaviTVs').click()
                cy.wait(3000)
            }
        })

    cy.wait(3000)

    cy.get('a[data-table=tabs-devices]')
        .as('mainNaviTVs_tvs')
        //.parent("li")
        //.should('not.have.class', 'active')
        .should('be.visible')
        .then(($parentD) => {
            cy.wait(3000)
            if (!$parentD.parent().hasClass('active')) {
                cy.get('@mainNaviTVs_tvs').click()
                cy.wait(3000)
            }
        })
}

function Select_AllTV() {
    cy.log("Select TV")
    cy.wait(3000)
    cy.get("#grid_devices > thead > tr > .select-cell > .select-box")
    .as("selectAllObj")
    .should('exist')
    .then(($targetDom) => {
        cy.wait(1000)
        cy.log("checked:" + $targetDom.is(':checked'))
        if (!$targetDom.is(':checked')) {
            cy.get('@selectAllObj').click()
            cy.wait(3000)
        }
    })
}

function Assign_Clone_data(tvData, selectCloneType, selectCloneName) {
    cy.wait(3000)
    cy.get("#assign_select")
    .click()
    .then(($dom) => {
        cy.wait(1000)
        let liLen = $dom.find("ul>li").length
        for (let i = 0; i < liLen; i++) {
            let cloneTypeObj = $dom.find("ul>li:eq(" + i + ")")
            let cloneTypeText = cloneTypeObj.text()
            if (null != cloneTypeText && cloneTypeText.indexOf(selectCloneType) > -1) {
                cloneTypeObj.click()
                break
            }
        }
        //$dom.find("ul li:eq(5)").click() // click channels
        cy.wait(3000)
        cy.get("#assignSelectRows > tr")
        //.its('length')
        //.should('be.gt', 1)
        //.contains('CypressTestCloneData')
        //.click()
        .each(($tr, index, $arrList) => {
            let selectText = $tr.find("td").eq(0).text()
            //cy.log("text_" + index + ":" + selectText)
            if (selectText.indexOf(selectCloneName) > -1) {
                //cy.log("CloneData select success")
                $tr.click()                              
            }
        })
        .then(() => {
            cy.wait(10000)
            cy.get("#tvsBody tr:eq(0)")
            .find("#tv_CloneDiv")
            .should('have.css', 'color', tvData.BlueColor)
            .then(($tvCloneDiv) => {
                let assignCloneIdentifier = $tvCloneDiv.attr("sicloneident")
                let IdentifierArr = assignCloneIdentifier.split("-")
                let yeatMonthDay = IdentifierArr[0].split("/")
                let hourMinutesSeconds = IdentifierArr[1].split(":")
                assignIdentifier = "\"" + yeatMonthDay[2] + "/" + yeatMonthDay[1] + "/" + yeatMonthDay[0] 
                                    + ":" + hourMinutesSeconds[0] + ":" + hourMinutesSeconds[1] + "\"" 
            })
        })
    })
    return assignIdentifier
}

function Click_Force_Upgrade_button() {
    cy.get("#allBtn")
    .should('be.visible')
    .contains('Force')
    .click()
}

function checkCloneColor(logType, targetColor) {    
    cy.wait(3000)
    var allSame = true
    cy.get("#tvsBody > tr")
    .each(($tr, index, $arrList) => {
        let trColor = $tr.find("#tv_CloneDiv").css("color")
        //cy.log(trColor + "," + targetColor)
        if (trColor != targetColor) {
            allSame = false
        }
    })
    if (allSame) {
        cy.log(logType + ", check color success!")
    } else {
        cy.log(logType + ", check color failed!")
    }
}

module.exports = {Login_CMND_page, Navigation_to_TVS_tv_page, Select_AllTV, Assign_Clone_data, Click_Force_Upgrade_button, checkCloneColor}