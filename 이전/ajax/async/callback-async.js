class UserStorage {
    loginUser(id, password) {  //로그인 확인함수, 파라메터를 받아옴
        return new Promise((resolve,reject)=> {
        setTimeout(() => {
            if (
                (id === 'study' && password === 'aistudy') ||
                (id === 'coder' && password === 'academy')
            ) {
                resolve(id);
            } else {
                reject(new Error('not found'));
            }
         } ,2000); //콜백함수 사용(함수, 시간)
     });
 }
 
    getRoles(user){
            return new Promise((resolve, reject)=>{
            setTimeout(()=> {
                if(user ==='study'){
                    resolve({name:'study', role:'admin'});  //json 객체
                } else {
                    reject(new Error('no access'))
                }
            }, 1000); 
        });
    }

    async getUserWithRole(user, password) {
        const id = await this.loginUser(user, password);
        const role = await this.getRoles(id);
        return role;
    }
}



const userStorage = new UserStorage();
const id = prompt('enter your id');
const password = prompt('enter your password');
userStorage
.getUserWithRole(id, password)
.then(user => alert(`Hello ${user.name}, you have a ${user.role} role`))
.catch(console.log); //받는 값이 같으면 'err=>' 생략 가능