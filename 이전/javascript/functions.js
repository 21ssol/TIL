function printHello(){
    console.log('Hello');
    return 'hi';
}

let p = printHello();
console.log(p);

function log(message){
    console.log(message);
}

log('hello!!');
log(1234);

function changeName(obj){
    obj.name = 'coder';
}

const kim = {name:'kim'};
changeName(kim);
console.log(kim);

function showMessage(message, from){
    console.log(`${message} by ${from}`);
}
showMessage('hi');

function showMessage2(message, from='unkown'){
    console.log(`${message} by ${from}`);
}

showMessage2('hi', 'hansol');


function printAll(...args){
    for (let i =0; i < args.length; i++){
        console.log(args[i]);
    }
 
    for(const arg of args){
        console.log(arg);
    }
 
    args.forEach((arg)=> console.log(arg));
}
 
printAll('developer', 'coder', 'team');

let globalMessage = 'global'; //전역변수
function printMessage3(){
    let message = 'hello' ; //지역변수
    console.log(message);
    console.log(globalMessage);
}
printMessage3();

//console.log(message);//오류
 
console.log('------------------');
console.log(sum(3,3));
function sum(a,b){
    return a+b;
}

const result  = sum(1,2);
console.log(`sum: ${result}`);
console.log(`sum: ${sum(10,20)}`);

const print = function() {
    console.log('print');
}
print();
const printAgain = print;
printAgain();
const sumAgain = sum;
console.log(sumAgain(1,2));

function randomQuiz(answer, printYes, printNo){
    if(answer === 'love you'){
        printYes();
    }else {
        printNo();
    }
}
const printYes = function(){
    console.log("Yes !");
}
const printNo = function print(){
    console.log("No !");
}
randomQuiz('wrong', printYes, printNo);
randomQuiz('love you', printYes, printNo);

const simplePrint = function(){
    console.log('simplePrint');
}
simplePrint();

const simplePrint2 = () => console.log('simplePrint2');
simplePrint2();

(function hello(){
    console.log('IIFE');
})();