//배열선언 방법 2가지
const arr1 = new Array();
const arr2 = [1,2];

const fruits = ['사과', '바나나'];
console.log(fruits);
console.log(fruits.length);
console.log(fruits[0]);
console.log(fruits[1]);
console.log(fruits[2]);
console.log(fruits[fruits.length-1]);
console.log('------------------------');

for(let i=0; i<fruits.length; i++){
    console.log(fruits[i]);
}

for(let apple of fruits){
    console.log(fruits);
}
fruits.forEach((apple)=>{console.log(apple);})
console.clear();

//push 추가, pop 뒤에있는것부터 꺼내오기 
fruits.push('딸기', '복숭아');
console.log(fruits);

const poped = fruits.pop();
console.log(poped);
fruits.pop();
console.log(fruits);

//unshift 앞에 추가, shift 앞에서 꺼내오기
fruits.unshift('딸기','레몬');
console.log(fruits);

fruits.shift();
console.log(fruits);
fruits.shift();
console.log(fruits);

fruits.push('딸기','복숭아','레몬');
console.log(fruits);
fruits.splice(1,1);
console.log(fruits);
fruits.splice(1,1,'배','수박');
console.log(fruits);

const fruits2 = ['메론','배'];
const newFruits = fruits.concat(fruits2);  //새로운 배열이 하나 만들어짐

//indexOf 인덱스의 위치를 찾음
console.log(fruits.indexOf('사과'));
console.log(fruits.indexOf("수박"));
console.log(fruits.indexOf("코코넛"));  //값 -1 : 없는 값

console.log(fruits.includes('수박'));
console.log(fruits.includes("코코넛"));

console.clear();

//lastIndexOf 뒤에서 부터 위치값을 찾기
fruits.push('사과');  //같은값이 2개 있을 때
console.log(fruits);
console.log(fruits.indexOf('사과'));
console.log(fruits.lastIndexOf('사과')); //같은 값이 있을 때 뒤에서부터 찾음
console.log(fruits.lastIndexOf('코코넛'));  //값 -1: 없는 값

console.clear();

//const array = new Array(12,20);
const array = [12,20]
console.log(array[0]);
console.log(array[1]);

//const array2 = new Array(12); unddfined >> 하나만 선언해서안됨, 다른방식권장
const array2 = [12];
console.log(array2[0]);