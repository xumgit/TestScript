console.log("Start Type Script")

// 1. Set
let set1 = new Set(['red', 'green', 'blue']);

for (let item of set1.keys()) {
    //console.log("keys:" + item);
}

for (let item of set1.values()) {
    //console.log("values:" + item);
}

set1.forEach((value, key) => {
    //console.log("value:" + value + ",key:" + key)
})

let set1Arr = [...set1]
console.log("set1Arr:" + set1Arr)

let set2 = new Set(['AAA', 'BBB', 'CCC'])
let set3 = new Set([...set2].filter(item => {
    if (item === 'AAA') {
        return item;
    }
}))
// size(), add(), delete(), has(), clear()
set3.add('DDD').add('EEE')
//console.log("set3:" + [...set3])

// 2.Map
const map1 = new Map()
map1.set("key1", "AAA").set("Key2", "BBB")
for (let [value, key] of map1) {
    console.log("value:" + value + ",key:" + key)
}

function strMapToObj(strMap) {
    let obj = Object.create(null);
    for (let [k,v] of strMap) {
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