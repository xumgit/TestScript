console.log("Start Type Script")

var message:string = "Hello World" 
console.log(message)

class Site {
    name(): void {
        console.log("Hello Site");
    }
}

var obj = new Site();
obj.name();

let num: number = 11;
let str: string = "testStr";
let flag: boolean = true;
let arr: number[] = [1, 2, 3, 4, 5];
enum Color {red, green, blue};
let c: Color = Color.green;

if (num > 100) {
    console.log("greate than 100");
} else if (num > 50) {
    console.log("greate than 50");
} else if (num > 10) {
    console.log("greate than 10");
} else if (num > 0) {
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

var i:number;
var total:number = 5;
var sum:number = 0;
for (i = 0; i < total; i++) {
    sum += i;
}
console.log("sum:" + sum);

arr.forEach((val, index, srcArray) => {
    console.log(val + "|" + index + "|(" + srcArray + ")");
});

function greet():string {
    return "Hello Greet";
}
console.log("result:" + greet());

function add(x: number, y: number): number {
    return x + y;
}
console.log("result:" + add(1, 2));

function buildName(firstName: string, lastName?: string) {
    if (lastName)
        return firstName + " " + lastName;
    else
        return firstName;
}
let result1 = buildName("Bob"); 
let result2 = buildName("Bob", "Adams"); 
console.log("result1:" + result1 + ",result2:" + result2);

function calculate_discount(price:number,rate:number = 0.50) { 
    var discount = price * rate; 
    console.log("result:",discount); 
} 
calculate_discount(1000);
calculate_discount(1000,0.30);

var foo = (x:number)=>10 + x 
console.log(foo(100));
var foo1 = (x:number)=> {    
    x = 10 + x; 
    console.log(x);  
} 
foo1(100);
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
