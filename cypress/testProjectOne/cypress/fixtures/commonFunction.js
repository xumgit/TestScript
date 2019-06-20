
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

function generateIpAddress(index) {
    var ipAddress = "127.1.1.1";   
    if (index == 0) {
        index = 1;
    }
                    
    if (index > 0 && index <= 255) { 
        ipAddress = "127.1.1." + index;
    } else if (index > 255 && index <= 510) {
        index = index - 255;
        ipAddress = "127.1.2." + index;
    } else if (index > 510 && index <= 765) {
        index = index - 510;
        ipAddress = "127.1.3." + index;
    } else if (index > 765 && index <= 1020) {
        index = index - 765;
        ipAddress = "127.1.4." + index;
    }

    return ipAddress
}

function generateMacAddress(index) {
    var macAddress = "70:AF:24:1A:97:51"; 
       
    if (index >= 0 && index <= 9) {
        macAddress = "0" + index + ":AF:24:1A:97:51";
    } else if (index >= 10 && index <= 99){
        macAddress = index + ":AF:24:1A:97:51";
    } else if (index >= 100 && index <= 999) {
        index = Math.floor(index / 10)
        macAddress = "AF:" + index + ":24:1A:97:51";
    } else if (index >=1000 && index <= 9999) {
        index = Math.floor(index / 100)
        macAddress = "AF:BE:" + index + ":1A:97:51";
    }

    return macAddress
}

function generateRoomId(index){
    var roomId = "00000";

    if (index >= 0 && index <= 9) {
        roomId = "0000" + index;
    } else if (index >= 10 && index <= 99){
        roomId = "000" + index;
    } else if (index >= 100 && index <= 999) {
        roomId = "00" + index;
    } else if (index >= 1000 && index <= 9999) {
        roomId = "0" + index;
    } else if (index >= 10000 && index <= 99999) {
        roomId = index;
    }

    return roomId
}

function generateTVSerialNumber(self, index) {
    var prefixTvseri = "1234";
    var tvSerialNumber = "12345678";

    if (index >= 0 && index < 10) {
        tvSerialNumber = prefixTvseri + index;
    } else if (index >= 10 && index < 990) {
        tvSerialNumber = prefixTvseri + index;
    } else if (index >= 99 && index < 999) {
        tvSerialNumber = prefixTvseri + index;
    } else if (index >= 999 && index < 9999) {
        tvSerialNumber = prefixTvseri + index;
    }

    return tvSerialNumber
}

function sleep(interval) {
    return new Promise(resolve => {
        setTimeout(resolve, interval);
    });
}

async function Send_TVDiscovery_data(tvData, index) {
    let commonRe = JSON.parse(JSON.stringify(commonRequest)); 
    let destTVData = JSON.parse(JSON.stringify(tvData));  
    let tvDiscoveryDataObj = destTVData['TVDiscoveryData'];
    let commandDetailsObj = tvDiscoveryDataObj['CommandDetails'];
    let tvDiscoveryParametersObj = commandDetailsObj['TVDiscoveryParameters'];
    let webServiceParametersObj = commandDetailsObj['WebServiceParameters'];
    tvDiscoveryParametersObj['TVIPAddress'] = generateIpAddress(index);
    let tvMACAddress = generateMacAddress(index);
    tvDiscoveryParametersObj['TVMACAddress'] = tvMACAddress;
    let tvRoomID = generateRoomId(index);
    tvDiscoveryParametersObj['TVRoomID'] = tvRoomID;
    let tvSerialNumber = generateTVSerialNumber(index);
    tvDiscoveryParametersObj['TVSerialNumber'] = tvSerialNumber;
    let tvUniqueID = tvSerialNumber + tvMACAddress.replace(/:/gi, "");
    webServiceParametersObj['TVUniqueID'] = tvUniqueID;

    commonRe.url = destTVData.WebServicesUrl;
    commonRe.body = JSON.stringify(tvDiscoveryDataObj);
    //console.log("tvDiscoveryDataObj:" + JSON.stringify(tvDiscoveryDataObj));
    cy.request(commonRe).then((resp)=>{
        //cy.log("send TVDiscovery status:" + resp.status);                  
    });
    //await sleep(500);
}

function Send_ReadyForUpgrade_data(tvData, index) {
    let commonRe = JSON.parse(JSON.stringify(commonRequest)); 
    let destTVData = JSON.parse(JSON.stringify(tvData));
    let readyForUpgradeDataObj = destTVData['ReadyForUpgradeData'];
    let tvMACAddress = generateMacAddress(index);
    let tvSerialNumber = generateTVSerialNumber(index);
    let tvUniqueID = tvSerialNumber + tvMACAddress.replace(/:/gi, "");
    readyForUpgradeDataObj['CommandDetails']['WebServiceParameters']['TVUniqueID'] = tvUniqueID;

    commonRe.url = destTVData.WebServicesUrl;
    commonRe.body = JSON.stringify(readyForUpgradeDataObj);
    cy.request(commonRe).then((resp)=>{
        //cy.log("send IPCloneService status:" + resp.status);                  
    })
}

function Send_UpGradeInProgress_data(tvData, index) {
    let commonRe = JSON.parse(JSON.stringify(commonRequest)); 
    let destTVData = JSON.parse(JSON.stringify(tvData));
    let upGradeInProgressDataObj = destTVData['UpGradeInProgressData'];
    let tvMACAddress = generateMacAddress(index);
    let tvSerialNumber = generateTVSerialNumber(index);
    let tvUniqueID = tvSerialNumber + tvMACAddress.replace(/:/gi, "");
    upGradeInProgressDataObj['CommandDetails']['WebServiceParameters']['TVUniqueID'] = tvUniqueID;

    commonRe.url = destTVData.WebServicesUrl;
    commonRe.body = JSON.stringify(upGradeInProgressDataObj);
    cy.request(commonRe).then((resp)=>{
        //cy.log("send UpGradeInProgressData status:" + resp.status);                  
    })
}

function Send_NotInUpgradeMode_data(tvData, index, changeCloneItem) {
    let commonRe = JSON.parse(JSON.stringify(commonRequest)); 
    let destTVData = JSON.parse(JSON.stringify(tvData));
    let notInUpgradeModeDataObj = changeCloneItemVersionNo(destTVData['NotInUpgradeModeData'], changeCloneItem);
    let tvMACAddress = generateMacAddress(index);
    let tvSerialNumber = generateTVSerialNumber(index);
    let tvUniqueID = tvSerialNumber + tvMACAddress.replace(/:/gi, "");
    notInUpgradeModeDataObj['CommandDetails']['WebServiceParameters']['TVUniqueID'] = tvUniqueID;

    commonRe.url = destTVData.WebServicesUrl;
    commonRe.body = JSON.stringify(notInUpgradeModeDataObj);
    cy.request(commonRe).then((resp)=>{
        //cy.log("send UpGradeInProgressData status:" + resp.status);                  
    })
}

function changeCloneItemVersionNo(tvData, changeCloneItem) {     
    let commandDetailsObj = tvData["CommandDetails"];
    let ipCloneParametersObj = commandDetailsObj["IPCloneParameters"];
    let cloneSessionStatusObj = ipCloneParametersObj["CloneSessionStatus"];
    let cloneItemStatusArrObj = cloneSessionStatusObj["CloneItemStatus"];
    let arrLen = cloneItemStatusArrObj.length;
    for(let i = 0; i < arrLen; i++) {
        let cloneItemDetailsObj = cloneItemStatusArrObj[i]["CloneItemDetails"];
        let cloneItemName = cloneItemDetailsObj["CloneItemName"];
        if (null != cloneItemName && "" != cloneItemName 
                && changeCloneItem.hasOwnProperty(cloneItemName)) {
            cloneItemDetailsObj["CloneItemVersionNo"] = changeCloneItem[cloneItemName];
        }
    }
    return tvData
}

async function generateTVDiscovery(tvData, generateTvs) {
    for (let i = 1; i <= generateTvs; i++) {          
        Send_TVDiscovery_data(tvData, i);
        //await sleep(100);
    }
}

async function generateReadyForUpgrade(tvData, generateTvs) {
    for (let i = 1; i <= generateTvs; i++) {          
        Send_ReadyForUpgrade_data(tvData, i);
        //await sleep(100);
    }
}

async function generateUpGradeInProgress(tvData, generateTvs) {
    for (let i = 1; i <= generateTvs; i++) {          
        Send_UpGradeInProgress_data(tvData, i);
        //await sleep(100);
    }
}

async function generateNotInUpgradeMode(tvData, generateTvs, changeCloneItem) {
    for (let i = 1; i <= generateTvs; i++) {          
        Send_NotInUpgradeMode_data(tvData, i, changeCloneItem);
        //await sleep(100);
    }
}

module.exports = {generateTVDiscovery, generateReadyForUpgrade, generateUpGradeInProgress, generateNotInUpgradeMode}