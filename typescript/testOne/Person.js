"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Person {
    constructor(name, age, year, month, day) {
        this.name = name;
        this.age = age;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    printStr() {
        console.log(this.year + "/" + this.month + "/" + this.day);
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
    getAge() {
        return this.age;
    }
    setAge(age) {
        this.age = age;
    }
}
exports.Person = Person;
