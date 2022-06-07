// 3. promise chaning 추가

const fetchNumber = new Promise((resolve, reject)=>{//promise생성
    setTimeout(()=>resolve(1), 1000);
}); 

fetchNumber
    .then(num =>num*2)   //promise 사용(then도 callback 함수, resolve에 있던 1 전달)
    .then(num=>num*3)  //위에서 전달받은 num의 2의 값 전달받음
    .then(num =>{  //이제 6을 넘긴다.
        return new Promise((resolve, reject)=>{ 
            setTimeout(()=>resolve(num-1), 1000); //1초후에 값을 전달, 이제 num은 5
        });
    })
    .then(num=>console.log(num));


// 4. Error Handling
// {} 사용시 return 키워드 반드시 사용!!
const getHen = () => {  //화살표 앞은 인자값
    return new Promise((resolve, reject) => {
        setTimeout(() => resolve('hen'), 1000);
    });
}

const getEgg = hen =>
    new Promise((resolve, reject)=> {
       // setTimeout(()=> resolve(`${hen} => egg`), 1000);
        setTimeout(()=>reject(new Error(`error! ${hen}=>egg`)), 1000);
    });

const cook = egg =>
    new Promise((resolve, reject)=>{
        setTimeout(()=> resolve(`${egg} => brunch`),1000);
    });

// getHen()
//     .then(hen => getEgg(hen))  //파라메터를 메소드에 전달하고 있다.
//     .then(egg=>cook(egg))
//     .then(meal =>console.log(meal))  //meal은 지역변수
//     .catch(err => console.log(err)); //getEgg에서 에러처리된다.(.then 은 못받는다)

// 37~41 코드 간단하게 구현
// getHen() 
//     .then(getEgg)
//     .then(cook)
//     .then(console.log)  
//     .catch(console.log);

//중간오류 처리하기
getHen() 
    .then(getEgg) 
    .catch(error => {
        return 'bread'; //에러 시 egg를 'bread'로 리턴한다
    })
    .then(cook) 
    .then(console.log)
    .catch(console.log);
