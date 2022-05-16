/*자바스크립트는 동기통신으로 순서대로 시작(var나 함수는 위로 hoisting)*/

// console.log('1');
// console.log('2');
// console.log('3');

console.log('1');
setTimeout(()=>{console.log('2');}, 1000);
console.log('3');

/** 자동으로 호출되는 메서드*/
function printImmediately(print){  //함수 선언
    print()
}
printImmediately(()=> console.log('hello'));

//비동기 콜백
function printWithDelay(print, timeout){
    setTimeout(print,timeout);
}
printWithDelay(()=> console.log('async callback'), 2000);