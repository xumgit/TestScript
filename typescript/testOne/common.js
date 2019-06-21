function strMapToObj(strMap) {
    let obj = Object.create(null);
    for (let [k, v] of strMap) {
        obj[k] = v;
    }
    return obj;
}
function objToStrMap(obj) {
    let strMap = new Map();
    for (let k of Object.keys(obj)) {
        strMap.set(k, obj[k]);
    }
    return strMap;
}
function mapToArrayJson(map) {
    return JSON.stringify([...map]);
}
function jsonToStrMap(jsonStr) {
    return objToStrMap(JSON.parse(jsonStr));
}
function jsonToMap(jsonStr) {
    return new Map(JSON.parse(jsonStr));
}
