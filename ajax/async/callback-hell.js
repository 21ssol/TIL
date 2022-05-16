class UserStorage {
    loginUser(id, password, onSuccess, onError) {  //로그인 확인함수, 파라메터를 받아옴
        setTimeout(() => {
            if (
                (id === 'study' && password === 'aistudy') ||
                (id === 'coder' && password === 'academy')
            ) {
                onSuccess(id);
            } else {
                onError(new Error('not found'))
            }
         } ,2000); //콜백함수 사용(함수, 시간)
        }
    
    getRoles(user, onSuccess, onError){
        setTimeout(()=> {
            if(user ==='study'){
                onSuccess({name:'study', role:'admin'});  //json 객체
            } else {
                onError(new Error('no access'))
            }
        }, 1000); 
    }
}

const userStorage = new UserStorage();
const id = prompt('enter your id');
const password = prompt('enter your password');
userStorage.loginUser(
    id,
    password,
    user => {  //onSuccess
        userStorage.getRoles(user, userWithRole => {
            alert(`hello ${userWithRole.name}, you have a ${userWithRole.role} role`);
        },error =>{
            console.log(error)
        });
    }, error => {  //onError
        console.log(error);  //errpr처리
    }
);  //loginUser() 호출