const name = 'js';
if(name === 'js'){
    console.log('Welcome, js!');
}else if (name ==='coder'){
    console.log("coder ~");
}else{
    console.log('unknown');
}

console.log(name === 'js' ? 'yes' : 'no'); //삼항연산자

console.clear(); //콘솔창 깨끗하게 정리

const brower = 'IE';
switch (brower){
    case 'IE' : 
    console.log("이제 없어진다.");
    break;
    case 'Chrome':
    case 'FireFox':
        console.log("많이 사용");
        break;
    default : 
    console.log('모두 브라우저');
    break;
}

let i = 3;
while (i>0){
    console.log(`while: ${i}`);
    i--;
}

let j = 3;
do {
    console.log(`do while: ${j}`);
    j--;
} while (i>0){
    console.log(`do while: ${j}`);
}

for(let i = 3; i>0; i--){
    console.log(`for: ${i}`);
}

for(let i = 0; i<10; i++){
    for(let j=0; j<10; j++){
        console.log(`i: ${i}, j: ${j}`);
    }
}

console.clear();

//continue, break;
for (let i=0; i < 11; i++){
    if(i % 2 !== 0){ //홀수라면
        continue; //continue 대신  i값 출력해도 된다.
    }
    console.log(`i: ${i} `)
}
 
for(let i = 0; i <  11; i++){
    if(i > 8){
        break;
    }
    console.log(`i: ${i}`);
}