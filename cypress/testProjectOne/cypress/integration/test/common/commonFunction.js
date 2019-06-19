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