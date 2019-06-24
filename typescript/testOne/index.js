console.log("Start Type Script");
var message = "Hello World";
console.log(message);
class Site {
    name() {
        console.log("Hello Site");
    }
}
var obj = new Site();
obj.name();
let num = 11;
let str = "testStr";
let flag = true;
let arr = [1, 2, 3, 4, 5];
let arr2 = [[1, 2, 3], [4, 5, 6]];
var Color;
(function (Color) {
    Color[Color["red"] = 0] = "red";
    Color[Color["green"] = 1] = "green";
    Color[Color["blue"] = 2] = "blue";
})(Color || (Color = {}));
;
let c = Color.green;
if (num > 100) {
    console.log("greate than 100");
}
else if (num > 50) {
    console.log("greate than 50");
}
else if (num > 10) {
    console.log("greate than 10");
}
else if (num > 0) {
    console.log("greate than 0");
}
switch (str) {
    case "A": {
        console.log("A");
        break;
    }
    case "B": {
        console.log("B");
        break;
    }
    case "C": {
        console.log("C");
        break;
    }
    case "testStr": {
        console.log("testStr");
        break;
    }
    default: {
        console.log("default");
        break;
    }
}
var i;
var total = 5;
var sum = 0;
for (i = 0; i < total; i++) {
    sum += i;
}
console.log("sum:" + sum);
arr.forEach((val, index, srcArray) => {
    console.log(val + "|" + index + "|(" + srcArray + ")");
});
function greet() {
    return "Hello Greet";
}
console.log("result:" + greet());
function add(x, y) {
    return x + y;
}
console.log("result:" + add(1, 2));
function buildName(firstName, lastName) {
    if (lastName)
        return firstName + " " + lastName;
    else
        return firstName;
}
let result1 = buildName("Bob");
let result2 = buildName("Bob", "Adams");
console.log("result1:" + result1 + ",result2:" + result2);
function calculate_discount(price, rate = 0.50) {
    var discount = price * rate;
    console.log("result:", discount);
}
calculate_discount(1000);
calculate_discount(1000, 0.30);
var foo = (x) => 10 + x;
console.log(foo(100));
var foo1 = (x) => {
    x = 10 + x;
    console.log(x);
};
foo1(100);
let s1 = "a1";
let s2 = "a2";
let s3 = s1.concat(s2);
console.log("s3:" + s3);
var sites = new Array("a", "b", "c");
console.log("res:" + typeof sites);
if ((typeof sites) == 'object') {
    var len = sites.length;
    for (let i = 0; i < len; i++) {
        console.log(i + ":" + sites[i]);
    }
}
var customer = {
    firstName: "Tome",
    lastName: "Harden",
    sayHi: () => { return "hello"; }
};
console.log("customer:" + customer.sayHi());
class Person1 {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    print() {
        console.log(this.name + "|" + this.age);
    }
    getName() {
        return this.name;
    }
    setName(name) {
        this.name = name;
    }
}
var p = new Person1("abc", 15);
p.print();
p.setName("def");
p.print();
var person = require("./Person");
var p2 = new person.Person("a", 11, "2000", "11", "10");
p2.print();
console.log(p2.getName() + "-" + p2.getAge());
p2.printStr();
// // 1. Set
// let set1 = new Set(['red', 'green', 'blue']);
// for (let item of set1.keys()) {
//     //console.log("keys:" + item);
// }
// for (let item of set1.values()) {
//     //console.log("values:" + item);
// }
// set1.forEach((value, key) => {
//     //console.log("value:" + value + ",key:" + key)
// })
// let set1Arr = [...set1]
// console.log("set1Arr:" + set1Arr)
// let set2 = new Set(['AAA', 'BBB', 'CCC'])
// let set3 = new Set([...set2].filter(item => {
//     if (item === 'AAA') {
//         return item;
//     }
// }))
// // size(), add(), delete(), has(), clear()
// set3.add('DDD').add('EEE')
// //console.log("set3:" + [...set3])
// // 2.Map
// const map1 = new Map()
// map1.set("key1", "AAA").set("Key2", "BBB")
// for (let [value, key] of map1) {
//     console.log("value:" + value + ",key:" + key)
// }
