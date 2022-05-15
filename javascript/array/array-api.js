// Q1. 배열을 하나의 문자열로 만들기
const fruits = ['apple', 'banana', 'orange'];
const result = fruits.join('/'); // ,기본값이라서 안써도 가능
console.log(`value: ${result}, type : ${typeof result}`);

//Q2. 문자열을 배열로
const fruits1 = '사과, 키위, 바나나, 체리';
const result1 = fruits1.split(',',2); // 2 리턴받을 사이즈 지정
console.log(result1);

//Q3. 배열을 반대로 출력
const array2 = [1,2,3,4,5];
array2.reverse();
console.log(array2);

//Q4. splice:n번째에서 m개를 지워라 slice: 잘라오기
const array3 = [1,2,3,4,5];
//const result3 = array3.splice(0,2);
result3 = array3.slice(2,5);  //2번째부터 5 -1번 전까지 잘라옴
console.log(result3);

//Q5. find 함수 >> 값이 있는지 찾아줌
// 클래스 선언
class Student {
    constructor(name, age, enrolled, score){
        this.name = name;
        this.age = age;
        this.enrolled = enrolled;
        this.score = score;
    }
}
const students = [
    new Student('A', 29, true, 45),  //배열선언
    new Student('B', 22, false, 90),
    new Student('C', 24, true, 80),
    new Student('D', 26, false, 60),
    new Student('E', 28, true, 100)
];

const result4 = students.find((student) => student.score ===90);
console.log(result4);

//Q6. 등록된 학생들 배열 만들기
{ //지역변수로 사용하기 위함(위랑 변수 명 겹침)
    const result = students.filter((student)=> student.enrolled);
    console.log(result);
}

//Q7. map : 검색한 결과를 변환해서 가져올때
{
    const result = students.map((student)=> student.score*2);
    console.log(result);
}

//Q8. some : 배열에서 하나라도 만족하면 true, 모든조건 만족하면 every
{
    const result = students.some((student)=> student.score<=50);
    console.log(result);
    const result1 =!students.every((student)=> student.score>=50);
    console.log(result1);
}
console.clear();

//Q9. reduce : 이전값, 현재값으로 전달
{
    const result = students.reduce((prev, curr)=> {
        console.log('------------------');
        console.log(prev);
        console.log(curr);
        return prev + curr.score;
    },0);
    console.log(result/students.length);
}
console.clear();

//Q10. map, filter, join
{
    const result = students
    .map((student)=> student.score)
    .filter((score)=> score>=50)
    .join();
    console.log(result);
}
//map, sort, join
{
    const result = students
    .map((student)=> student.score)
    .sort((a,b)=> b-a) //a-b오름차순 정렬, b-a 내림차순 정렬
    .join();
    console.log(result);
}