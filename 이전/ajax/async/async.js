//async & await

// 1. async:코드블록이 자동으로 promise로 변환된다.
async function fetchUser(){  //일반함수에 aync 선언하면 resolve, reject와 같은코드
    //do network request in 10 secs...
    return 'study';
}

const user = fetchUser();
user.then(console.log);  //study값을 전달
console.log(`user: ${user}`);   //user 출력 user 자체가 promise를 출력한다.

// 2. await : async 안에서만 선언되어져야 한다.
function delay(ms){
    return new Promise((resolve)=> setTimeout(resolve, ms));
}

async function getApple(){
    await delay(2000);  //delay의 함수호출이 끝날때까지 기다린다.
    //throw 'error'  //임의로 err발생시킴, 리턴이 되기 전 error가 먼저 뜬다
    return 'apple';
}

async function getBanana(){
    await delay(1000); //await 끝날때까지 기다린 다음에 바나나 리턴
    return 'banana';
}

// function pickFruits(){
//     return getApple().then((apple)=>{
//         return getBanana().then((banana) => `${apple} + ${banana}`);
//     });
// }

//개선된 코드(29~33줄)
// async function pickFruits(){
//     try {
//         const apple = await getApple();  //async안에서만, 처리가 완료될때까지 기다림
//         const banana = await getBanana();
//         return `${apple} + ${banana}`;
//     } catch (error) {
//         console.log(new Error('error'));  //Error 객체를 통해 getApple의 에러처리
//     }
// }

//병렬처리로 좀 더 개선된 코드 구현(동시 진행)
// async function pickFruits(){
//         try {
//             const applePromise =  getApple(); //promise로 리턴되어 바로실행
//             const bananaPromise =  getBanana();
//             const apple = await applePromise;  //순차 처리, 병렬적으로 처리
//             const banana = await bananaPromise;
//             return `${apple} + ${banana}`;
//         } catch (error) {
//             console.log(new Error('error')); 
//         }
//     }


//배열을 이용하여 API 사용
//Promise APIs all() 병렬처리 코드를 좀더 개선
// function pickFruits(){
//     return Promise.all([getApple(), getBanana()])
//     .then((fruits)=>fruits.join(' + '));  //fruits가 배열이라 join사용 가능
// }
//pickFruits().then(console.log);


//Promise APIs race() 둘 중 먼저 수행되는 것 하나만 처리결과를 가져옴
function pickOnlyOne(){
    return Promise.race([getApple(), getBanana()]);
}

pickOnlyOne().then(console.log);