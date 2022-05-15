const obj1 = {};  //비어있는 객체, 중괄호안에서 직접 값을 넣어서 선언
const obj2 = new Object();  //생성자 호출

function print(person){
    console.log(person.name); //person 을 .을 찍어서 객체 선언
    console.log(person.age);
}

const baby = {name: 'hong', age: 4};
print(baby);

baby.hasJob = true;
console.log(baby.hasJob);
delete baby.hasJob;
console.log(baby.hasJob);

console.log(baby.name);
console.log(baby['name']);
baby['hasJob'] = true;
console.log(baby.hasJob);

function printValue(obj, key) {
   // console.log(obj.key); 인식 못함
    console.log('===============');
    console.log(obj[key]);
  }
printValue(baby, 'name');
printValue(baby, 'age');

const person1 = {name:'bob', age:12};
const person2 = {name:'steve', age:3};
const person3 = {name:'dave', age:4};
const person4 = new Person('baby', 30); //생성자 함수
console.log(person4);

function Person(name, age){
  this.name = name;
  this.age = age;
}

console.log('name' in baby); //in 은 속성이 개체안에 있는지 없는지 체크
console.log('age' in baby);
console.log('random' in baby);
console.log(baby.random);

for(let key in baby) {
  console.log(key);
}

const array = [1,2,4,5];
for(let value of array){
  console.log(value);
}

//복사
const user = {name: 'bob', age: '20'};
const user2 = user;
console.log(user);
user2.name = 'coder';
console.log(user);

const user3 = {};
for(let key in user){
  user3[key] = user[key];
}
console.clear();
console.log(user3);
user.name = 'bob'
console.log(user);
//new way 복사
console.clear();
const user4 = Object.assign({}, user);
console.log(user4);
user.name = 'dev';
console.log(user4);
console.log(user);

const fruit1 = {color:'red'};
const fruit2 = {color:'blue', size : 'big'};
const mixed = Object.assign({}, fruit1, fruit2);  //fruit 1과2를 합침
console.log(mixed.color);
console.log(mixed.size);
console.log(mixed);