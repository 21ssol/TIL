class Person{  //클래스 선언
    constructor(name, age){
        this.name = name;  //필드
        this.age = age;
    }
    //메소드 
    speak(){
        console.log(`${this.name}: hello🖐`);
    }
}

const hong = new Person("hong길동", 20); //생성자 호출
console.log(hong.name);
console.log(hong.age);
hong.speak();