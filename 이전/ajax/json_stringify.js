//JSON(Javascript Object Notation)
// 1. Objext -> JSON
//stringify(obj) : 직렬화

//변수선언
let json = JSON.stringify(true);  //논리형을 문자형으로
console.log(`json: ${json}, type: ${typeof json}`);

console.log(`true: ${true}, type: ${typeof true}`);

json = JSON.stringify(['apple', 'banana']); //배열을 문자형으로
console.log(`json: ${json}, type: ${typeof json}`);
console.log(`array: ${['apple', 'banana']}, type: ${typeof ['apple', 'banana']}`);


const rabbit = {  //객체 -> 문자열, 메서드는 JSON으로 변환되지 않는다!
    name : 'tori',
    color: 'white',
    size : null,
    birthday: new Date(),
    jump:()=> {  // jump는 출력 안됨
        console.log(`${name} can jump!`);
        console.log(`object: ${rabbit}, type: ${typeof rabbit}`);
    },
};

json = JSON.stringify(rabbit);
console.log(`json: ${json}, typeof : ${typeof json}`);

json = JSON.stringify(rabbit, ['name', 'color']);
console.log(json);

//함수 사용
json = JSON.stringify(rabbit, (key, value) => {
    console.log(`key: ${key}, value: ${value}`);  //실제값
//name를 rab으로 변경하고 없으면 그냥 value(삼항연산자)
    return key === 'name' ? 'rab' : value  //이때 직렬화가 완성
});
console.log(json);