//JSON(Javascript Object Notation)
// 2. JSON(string) -> Object
//parse(json) 역직렬화
console.clear();

console.log(rabbit); //객체
json = JSON.stringify(rabbit);  //직렬화, 문자열로 나옴
console.log(`${typeof json}`);
const obj = JSON.parse(json);
console.log(obj); //다시 객체

rabbit.jump();
//obj.jump(); obj에는 jump가 없음, json 변환에서 점프가 없기 때문에

console.log(rabbit.birthday.getDate());
//console.log(obj.birthday.getDate());  birthday는 문자열이라서 오류

//세밀하게 변경하고 싶을 때 ? 쓴다
const obj2 = JSON.parse(json,(key, value)=>{
    console.log(`key: ${key}, value: ${value}`);
    return key === 'birthday' ? new Date(value) : value;
});
//obj2를 통해 bithday가 가지고 있는 getDate를 가지고 올 수 있다
console.log(obj2.birthday.getDate());
