import birthday = require("./Birthday");

export class Person implements birthday.Birthday{
    name:string;
    age:number;

    year:string;
    month:string;
    day:string;

    constructor(name:string, age:number, year:string, month:string, day:string) {
        this.name = name;
        this.age = age;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    printStr():void {
        console.log(this.year + "/" + this.month + "/" + this.day);
    }

    print():void {
        console.log(this.name + "|" + this.age);
    }

    getName():string {
        return this.name;
    }

    setName(name:string):void {
        this.name = name;
    }

    getAge():number {
        return this.age;
    }

    setAge(age:number):void {
        this.age = age;
    }
}