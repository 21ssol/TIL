class UserStorage {
    constructor(){  //생성자
        if (window.XMLHttpRequest){
            this.xhr = new XMLHttpRequest(); //생성자에서는 this사용 가능, userStorage의 멤버변수
        } else {
            // code for IE6, IE5 버전이 낮을 때
            this.xhr = ActiveXObject('Microsoft.XHTTP');
        }
        console.log(this.xhr);
    }
    loginUser(id, passwd){
        return new Promise((resolve, reject) => {
            this.xhr.onload = () => {
                resolve(this.xhr.responseText);
            };
            this.xhr.onerror = () => {
                //오류 시
                reject(new Error(this.xhr.status));
            };
            //url을 쓸 때는 띄어쓰기 금지!
            this.xhr.open('GET', `pay.jsp?id=${id}&passwd=${passwd}`, true);
            this.xhr.send();
        });
    }
}