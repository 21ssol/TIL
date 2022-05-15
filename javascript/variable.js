'use strict';

let name = '홍';
const maxNumber = 5;

console.log(name);

let a = 12;
let b = 12.2;
console.log(`value:${a}, type:${typeof a}`);
console.log(`value:${b}, type:${typeof b}`);

const infinity = 1/0;
const negativeInfinity = -1/0;
const nAn = 'not a number'/2;
console.log(infinity);
console.log(negativeInfinity);
console.log(nAn);

const char = 'c';
const brendan = 'brendan';
const greeting = 'hello' + ' brendan';
console.log(`value: ${greeting} type:${typeof greeting}`);
console.log("hi " + brendan + " !");
console.log(`hi ${brendan} !`);

const helloBob = `hi ${brendan} !`;
console.log(`value: ${helloBob} type: ${typeof helloBob}`);
console.log('value: ' + helloBob + ' type: ' + typeof helloBob);

//false : 0, null, undefined, NaN, '' 
const canRead = true;
const test = 3<1;
console.log(`value: ${canRead}, type : ${typeof canRead}`);
console.log(`value: ${test}, type: ${typeof test}`);

//null empty value;
let nothing = null;
console.log(`value: ${nothing}, type: ${typeof nothing}`);

//undefined : 정해지지 않은 값
let x;
console.log(`value: ${x}, type: ${typeof x}`);

//symbol, create unique identifies for objects, 고유식별자
const symbol1 = Symbol('id');
const symbol2 = Symbol('id');
console.log(symbol1 === symbol2);
const gsymbol1 = Symbol.for('id');
const gsymbol2 = Symbol.for('id');
console.log(gsymbol1 === gsymbol2);
console.log(`value: ${symbol1.description}, type: ${typeof symbol1}`);
console.log(`value: ${gsymbol1.description}, type: ${typeof gsymbol1}`);

let text = 'hello';
console.log(text.charAt(0));
console.log(`value: ${text}, type: ${typeof text}`);
text = 1;
console.log(`value: ${text}, type: ${typeof text}`);
text = '8' / '2';  //8,2는 숫자로 변환
console.log(`value: ${text}, type: ${typeof text}`);
// console.log(text.charAt(0)); 오류
text = '7' + 5;  // + 기호때문에 5가 문자열로 변환
console.log(`value: ${text}, type: ${typeof text}`);

const obj = {name: 'obj', age:20};
console.log(`value: ${obj}, type: ${typeof obj}`);
console.log(`name: ${obj.name}, age: ${obj.age}`);