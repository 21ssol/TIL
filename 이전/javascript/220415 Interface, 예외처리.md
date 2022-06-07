### 🌈 220415 TIL

## Interface, 예외처리

### [1] Interface

---

>객체를 쉽게 교체하여 다형성을 구현하기 위해 많이 사용

* 상수와 추상 메소드로 이루어져 있다. 메소드의 내용을 정의하는 {} 는 없다.
* public abstract 는 생략 가능
* 외부에 공개할 메소드를 등록하는 목적으로도 사용
* 객체를 만들 수 없다.

**[인터페이스의 다중 구현]**

```
interface USBMouseInterface {
 void mouseMove();
 void mouseClick();
}


interface RollMouseInterface {
 void roll();
}


public class MouseDriver implements RollMouseInterface , USBMouseInterface {
 void mouseMove() { .... }
 void mouseClick() { ... }
 void roll() { ... }

 // 추가적으로 다른 메소드를 작성할 수 있다.
 int getStatus() { ... }
 int getButton() { ... }
}

ColorMain.java 

interface Green { 
  //추상 메소드 
  public String greenColor(); 
} 


class GreenImpl implements Green{ 
  public String greenColor(){ 
    return "초록색입니다.★"; 
  } 
} 


interface Red { 
  //추상 메소드 
  public String redColor(); 
} 


class RedImpl implements Red{ 
  public String redColor(){ 
    return "빨간색입니다.★"; 
  } 
} 


class ColorImpl implements Green, Red{ 
  public String greenColor(){ 
    return "초록색입니다."; 
  } 

  public String redColor(){ 
    return "빨간색입니다."; 
  } 
} 


public class ColorMain{ 
  public static void main(String[] args) { 
    Green g = new GreenImpl(); 
    System.out.println(g.greenColor());   

​    Red r = new RedImpl(); 
​    System.out.println(r.redColor());  

​    ColorImpl c = new ColorImpl(); 
​    System.out.println(c.greenColor()); 
​    System.out.println(c.redColor()); 
  } 
} 
```



### [2] 예외처리(Exception)

---

> 컴파일 오류가 아닌 실행 시 발생하는 에러

* ArithmeticException : 정수를 0으로 나눌때 발생
* NullPointerException : Null 레퍼런스 참조할 때 발생
* ClassCastException : 변환할 수 없는 타입으로 객체를 변환할 때 발생
* OutOfMemoryException : 메모리가 부족할 경우 발생
* ArrayIndexOutOfBoundsException : 배열의 범위를 벗어났을 때 발생
* IllegalArgumentException : 잘못된 인자 전달 시 발생
* IOException : 입출력 동작 실패 or 인터럽트 시 발생
* NumberFormatException : 문자열이 나타나는 숫자와 일치하지 않은 타입의 숫자 변환 시 발생

##### `예외처리 예시`

```try{ 
try{
  에러가 발생할 소지가 있는 코드를 개발자가 선별하여 지정해야 하며 "IO, 
  DBMS, NETWORK"관련 코드가 대부분입니다. 

}catch(Exception e){ 
  예외처리 및 예외처리 원인 출력 
  System.out.println(e.toString()); 
}finally{ 
  무조건 실행되는 코드 블럭, 데이터베이스 연결 종료 등 
}
```



**메소드에 throws 절을 명시하면 try~catch 문을 사용하지 않아도 된다.** 

``` 
public void prn(ResultSer r) throws SQLException{ 
    \- 처리로직 구현 
} 
```



### [3] IO Stream - 표준 입출력, 파일 입출력

---

> 데이터를 보내고 받는 것.

* Stream 은 연속적인 데이터의 흐름을 나타낸다.

* 단방향 구조로 이루어져 있어서 한곳으로만 데이터가 전송된다. 
* 데이터가 많으면 지연된다.

***

1. 표준 입력 : 키보드로 입력하는 것
   * 입력관련 : System.in / Scanner / BufferReader
2. 표준 출력 : 화면으로 출력하는 것
   * 출력관련 : System.out / System.err

``` ※ 숫자 13은 Enter를 의미한다.
- System.in.read() != 13      // 선택이 Enter가 아니라는 의미 
