### π 220415 TIL

## Interface, μμΈμ²λ¦¬

### [1] Interface

---

>κ°μ²΄λ₯Ό μ½κ² κ΅μ²΄νμ¬ λ€νμ±μ κ΅¬ννκΈ° μν΄ λ§μ΄ μ¬μ©

* μμμ μΆμ λ©μλλ‘ μ΄λ£¨μ΄μ Έ μλ€. λ©μλμ λ΄μ©μ μ μνλ {} λ μλ€.
* public abstract λ μλ΅ κ°λ₯
* μΈλΆμ κ³΅κ°ν  λ©μλλ₯Ό λ±λ‘νλ λͺ©μ μΌλ‘λ μ¬μ©
* κ°μ²΄λ₯Ό λ§λ€ μ μλ€.

**[μΈν°νμ΄μ€μ λ€μ€ κ΅¬ν]**

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

 // μΆκ°μ μΌλ‘ λ€λ₯Έ λ©μλλ₯Ό μμ±ν  μ μλ€.
 int getStatus() { ... }
 int getButton() { ... }
}

ColorMain.java 

interface Green { 
  //μΆμ λ©μλ 
  public String greenColor(); 
} 


class GreenImpl implements Green{ 
  public String greenColor(){ 
    return "μ΄λ‘μμλλ€.β"; 
  } 
} 


interface Red { 
  //μΆμ λ©μλ 
  public String redColor(); 
} 


class RedImpl implements Red{ 
  public String redColor(){ 
    return "λΉ¨κ°μμλλ€.β"; 
  } 
} 


class ColorImpl implements Green, Red{ 
  public String greenColor(){ 
    return "μ΄λ‘μμλλ€."; 
  } 

  public String redColor(){ 
    return "λΉ¨κ°μμλλ€."; 
  } 
} 


public class ColorMain{ 
  public static void main(String[] args) { 
    Green g = new GreenImpl(); 
    System.out.println(g.greenColor());   

β    Red r = new RedImpl(); 
β    System.out.println(r.redColor());  

β    ColorImpl c = new ColorImpl(); 
β    System.out.println(c.greenColor()); 
β    System.out.println(c.redColor()); 
  } 
} 
```



### [2] μμΈμ²λ¦¬(Exception)

---

> μ»΄νμΌ μ€λ₯κ° μλ μ€ν μ λ°μνλ μλ¬

* ArithmeticException : μ μλ₯Ό 0μΌλ‘ λλλ λ°μ
* NullPointerException : Null λ νΌλ°μ€ μ°Έμ‘°ν  λ λ°μ
* ClassCastException : λ³νν  μ μλ νμμΌλ‘ κ°μ²΄λ₯Ό λ³νν  λ λ°μ
* OutOfMemoryException : λ©λͺ¨λ¦¬κ° λΆμ‘±ν  κ²½μ° λ°μ
* ArrayIndexOutOfBoundsException : λ°°μ΄μ λ²μλ₯Ό λ²μ΄λ¬μ λ λ°μ
* IllegalArgumentException : μλͺ»λ μΈμ μ λ¬ μ λ°μ
* IOException : μμΆλ ₯ λμ μ€ν¨ or μΈν°λ½νΈ μ λ°μ
* NumberFormatException : λ¬Έμμ΄μ΄ λνλλ μ«μμ μΌμΉνμ§ μμ νμμ μ«μ λ³ν μ λ°μ

##### `μμΈμ²λ¦¬ μμ`

```try{ 
try{
  μλ¬κ° λ°μν  μμ§κ° μλ μ½λλ₯Ό κ°λ°μκ° μ λ³νμ¬ μ§μ ν΄μΌ νλ©° "IO, 
  DBMS, NETWORK"κ΄λ ¨ μ½λκ° λλΆλΆμλλ€. 

}catch(Exception e){ 
  μμΈμ²λ¦¬ λ° μμΈμ²λ¦¬ μμΈ μΆλ ₯ 
  System.out.println(e.toString()); 
}finally{ 
  λ¬΄μ‘°κ±΄ μ€νλλ μ½λ λΈλ­, λ°μ΄ν°λ² μ΄μ€ μ°κ²° μ’λ£ λ± 
}
```



**λ©μλμ throws μ μ λͺμνλ©΄ try~catch λ¬Έμ μ¬μ©νμ§ μμλ λλ€.** 

``` 
public void prn(ResultSer r) throws SQLException{ 
    \- μ²λ¦¬λ‘μ§ κ΅¬ν 
} 
```



### [3] IO Stream - νμ€ μμΆλ ₯, νμΌ μμΆλ ₯

---

> λ°μ΄ν°λ₯Ό λ³΄λ΄κ³  λ°λ κ².

* Stream μ μ°μμ μΈ λ°μ΄ν°μ νλ¦μ λνλΈλ€.

* λ¨λ°©ν₯ κ΅¬μ‘°λ‘ μ΄λ£¨μ΄μ Έ μμ΄μ νκ³³μΌλ‘λ§ λ°μ΄ν°κ° μ μ‘λλ€. 
* λ°μ΄ν°κ° λ§μΌλ©΄ μ§μ°λλ€.

***

1. νμ€ μλ ₯ : ν€λ³΄λλ‘ μλ ₯νλ κ²
   * μλ ₯κ΄λ ¨ : System.in / Scanner / BufferReader
2. νμ€ μΆλ ₯ : νλ©΄μΌλ‘ μΆλ ₯νλ κ²
   * μΆλ ₯κ΄λ ¨ : System.out / System.err

``` β» μ«μ 13μ Enterλ₯Ό μλ―Ένλ€.
- System.in.read() != 13      // μ νμ΄ Enterκ° μλλΌλ μλ―Έ 
