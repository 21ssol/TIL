# Java 과제

> 객체 지향 및 캡슐화 적용



## 🥨BookDTO 클래스에 toString()을 오버라이딩하여 내용 출력하기

- BookDTO 에서 객체 생성하여 BookTest에서 출력되도록 작성

#### BookDTO class

``` java
package homework0;

public class BookDTO {
  
 int isbn;
 String title;
 String author;
 String publisher;
 int price;
 String desc;
 
 
public BookDTO() {
  super();
  // TODO Auto-generated constructor stub
}


public BookDTO(int isbn, String title, String author, String publisher, int price, String desc) {
  super();
  this.isbn = isbn;
  this.title = title;
  this.author = author;
  this.publisher = publisher;
  this.price = price;
  this.desc = desc;
}


@Override
public String toString() {
  return "|" + isbn + "\t|" + title + "\t|" + author + "\t|" + publisher + "\t|"
      + price + "\t|" + desc ;
}


public int getIsbn() {
  return isbn;
}


public void setIsbn(int isbn) {
  this.isbn = isbn;
}


public String getTitle() {
  return title;
}


public void setTitle(String title) {
  this.title = title;
}


public String getAuthor() {
  return author;
}


public void setAuthor(String author) {
  this.author = author;
}


public String getPublisher() {
  return publisher;
}


public void setPublisher(String publisher) {
  this.publisher = publisher;
}


public int getPrice() {
  return price;
}


public void setPrice(int price) {
  this.price = price;
}


public String getDesc() {
  return desc;
}


public void setDesc(String desc) {
  this.desc = desc;
}
 
 
}
```



#### BookTest class

``` java
package homework0;

public class BookTest {

  public static void main(String[] args) {

    BookDTO dto1 = new BookDTO(21424, "Java Basic", "김하나", "Jaen.kr", 15000, "Java 기본 문법");
    BookDTO dto2 = new BookDTO(33455, "JDBC Pro", "김철수", "Jaen.kr", 23000,"");
    BookDTO dto3 = new BookDTO(55355, "Servlet/JSP", "박자바", "Jaen.kr", 41000, "Medel2 기반");
    BookDTO dto4 = new BookDTO(35332, "Android App", "홍길동", "Jaen.kr", 25000, "Lightweight FrameWork");
    BookDTO dto5 = new BookDTO(35355, "OOAD 분석, 설계", "소나무", "Jaen.kr", 30000, "");
    
    System.out.println("***************************** 도서 목록 *****************************");
    System.out.println(dto1.toString());
    System.out.println(dto2.toString());
    System.out.println(dto3.toString());
    System.out.println(dto4.toString());
    System.out.println(dto5.toString());
  }

}
```

