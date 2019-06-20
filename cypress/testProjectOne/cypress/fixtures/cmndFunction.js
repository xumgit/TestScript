
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
    cy.get("#grid_devices input[name='select']")
    .should('exist')
    .then(($targetDom) => {
        cy.wait(1000)
        if (!$targetDom.is(':checked')) {
            $targetDom.click()
        }
    })
}

module.exports = {Login_CMND_page, Navigation_to_TVS_tv_page, Select_AllTV}