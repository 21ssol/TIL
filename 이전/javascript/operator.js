console.log('my' + ' dog');
console.log('1' + 2);   // 2는 문자열로 변환
console.log(`string literals: 1 + 2 = ${1 + 2}`);

console.log( 1 + 1); 
console.log( 1 - 1);
console.log( 1 / 1);
console.log( 1 * 1);
console.log( 5 % 3);  //나머지구하기
console.log( 2 ** 3);  //거듭제곱

let counter = 2;
const preIncrement = ++counter; //counter값이 먼저 증가된후  출력, 전위연산
console.log(`preIncrement: ${preIncrement}, counter:${counter}`)

const postIncrement = counter++; //할당먼저 된후  counter가 1 증가, 후위연산
console.log(`postIncrement: ${postIncrement}, counter:${counter}`)

let x = 3;
let y = 6;
console.log(x += y); 
console.log(x -= y);
console.log(x /= y); 
console.log(x *= y);

//비교연산
console.log(10<6);
console.log(10<=6);
console.log(10>6);
console.log(10>=6);

//논리연산
const value1 = true;  //변수 선언
const value2 = 4 < 2
console.log(`or: ${value1 || value2 || check()}`);
console.log(`and: ${value1 && value2 && check()}`);

function check(){  //함수선언: check() 앞에있어도 되고 뒤에 있어도 상관없음
    for(let i=0; i<10; i++){
        console.log('wating ?');
    }
    return true;
}

console.log(!value1);

//equality(==, ===)
const stringfive = '5';
const numberfive = 5;
//== 약한비교
console.log(stringfive == numberfive); //숫자로 변환
console.log(stringfive != numberfive);

//=== 강한비교, 문자->숫자 변환 안된다
console.log(stringfive === numberfive);
console.log(stringfive !== numberfive);

//객체비교
const js1 = {name:'js'};  //객체 생성
const js2 = {name:'js'};
const js3 = js1;
console.log(js1 == js2); //참조값이라서 false가 나옴
console.log(js1 === js2);
console.log(js1 === js3);

console.log(0 == false);
console.log(0 === false);
console.log('' == false);
console.log('' === false);
console.log(null == undefined);
console.log(null === undefined);