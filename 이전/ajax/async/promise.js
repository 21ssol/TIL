// 1. Producer : 제공자

const promise = new Promise((resolve, reject) => { //promise 만들고 객체 생성
    console.log(`doing something...`);
    setTimeout(() => {  //서버가 처리되는 시간
        resolve('study');
        //reject(new Error('no network'));
    }, 2000) //2초후에 함수 실행
});


//2. Consumer : 사용자, then, catch, finally로 pormise 사용
promise
    .then((value) =>{  //callback 함수의 매개변수로 받아온다.
        console.log(value);
    })
    .catch(error => {
        console.log(error);
    })
    .finally(()=>{   //무조건 실행되는 곳(성공 or 실패)
        console.log('finally');
    })