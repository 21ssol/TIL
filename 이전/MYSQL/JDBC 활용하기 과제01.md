# JDBC 활용하기

> JDBC 이해하기



## 🥨 SQL과 JAVA를 연동하여 프로그램 만들기

* BookDAO 클래스에 5개의 메소드 작성 (create, read, update, delete, list)
* BookTest 클래스에서 BookDAO 객체 생성 후 호출 -> 성공/실패 출력하기
* BookDTO, BookDAO, BookTest 및 book.sql의 DDL문도 함께 작성하기



#### BookDTO class

``` java
package homework;

public class BookDTO {
  
  private int isbn;
  private String title;
  private String author;
  private String publisher;
  private int price;
  private String memo;
  private String publish_date;
  
  
  public BookDTO() {
    super();
    // TODO Auto-generated constructor stub
  }


  public BookDTO(int isbn, String title, String author, String publisher, int price, String memo, String publish_date) {
    super();
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.price = price;
    this.memo = memo;
    this.publish_date = publish_date;
  }


  @Override
  public String toString() {
    return "BookDTO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", price="
        + price + ", memo=" + memo + ", publish_date=" + publish_date + ", getClass()=" + getClass() + ", hashCode()="
        + hashCode() + ", toString()=" + super.toString() + "]";
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


  public String getMemo() {
    return memo;
  }


  public void setMemo(String memo) {
    this.memo = memo;
  }


  public String getPublish_date() {
    return publish_date;
  }


  public void setPublish_date(String publish_date) {
    this.publish_date = publish_date;
  }
  
  
  
}
```



#### BookDAO class

``` java
package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.DBClose;
import utility.DBOpen;

public class BookDAO {
  
  //delete
  public boolean delete(int isbn) {
    boolean flag = false;
    
    Connection con = DBOpen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    
    sql.append(" delete from test1 ");
    sql.append(" where isbn = ? ");
    
    try {
      pstmt = con.prepareStatement(sql.toString());
      
      pstmt.setInt(1, isbn);
      
      int cnt = pstmt.executeUpdate();
      if(cnt>0) flag = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBClose.close(pstmt, con);
    }
    
    return flag;
  }
  
  //create
  public boolean create(BookDTO dto) {
    boolean flag = false;
    
    Connection con = DBOpen.getConnection();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    
    sql.append(" insert into test1 (isbn, title, author, publisher, price, memo, publish_date) ");
    sql.append(" values (?, ?, ?, ?, ?, ?, ?) ");
    
    try {
      pstmt = con.prepareStatement(sql.toString());
      
      pstmt.setInt(1, dto.getIsbn());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getAuthor());
      pstmt.setString(4, dto.getPublisher());
      pstmt.setInt(5, dto.getPrice());
      pstmt.setString(6, dto.getMemo());
      pstmt.setString(7, dto.getPublish_date());
      
      int cnt = pstmt.executeUpdate();
      if(cnt>0) flag = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBClose.close(pstmt, con);
    }
    return flag;
  }
  
  
  //update
  public boolean update(BookDTO dto) {
    boolean flag = false;
    Connection con = DBOpen.getConnection();
    PreparedStatement pstmt = null;
    
    StringBuffer sql = new StringBuffer();
    sql.append(" update test1 ");
    sql.append(" set title = ?, memo = ? ");
    sql.append(" where isbn = ? ");
    
    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getTitle());
      pstmt.setString(2, dto.getMemo());
      pstmt.setInt(3, dto.getIsbn());
      
      int cnt = pstmt.executeUpdate();
      if(cnt>0) flag = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      DBClose.close(pstmt, con);
    }
    
    return flag;
  }
  
  //read
  public BookDTO read(int isbn) {
    BookDTO dto = null;
    Connection con = DBOpen.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    StringBuffer sql = new StringBuffer();
    sql.append("  select * ");
    sql.append("  from test1 ");
    sql.append("  where isbn = ? ");
    
    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, 2);
      rs = pstmt.executeQuery();
      
      if(rs.next()) {
        dto = new BookDTO();
        dto.setIsbn(rs.getInt("isbn"));
        dto.setTitle(rs.getString("title"));
        dto.setAuthor(rs.getString("author"));
        dto.setPublisher(rs.getString("publisher"));
        dto.setPrice(rs.getInt("price"));
        dto.setMemo(rs.getString("memo"));
        dto.setPublish_date(rs.getString("publish_date"));
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBClose.close(rs, pstmt, con);
    }
    
    return dto;
  }

  //list
  public List<BookDTO> list(){
    List<BookDTO> list = new ArrayList<BookDTO>();
    Connection con = DBOpen.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    StringBuffer sql = new StringBuffer();
    sql.append(" select isbn, title, author, publisher, price, memo, publish_date ");
    sql.append(" from test1 ");
    
    try {
      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        BookDTO dto = new BookDTO();
        
        dto.setIsbn(rs.getInt("isbn"));
        dto.setTitle(rs.getString("title"));
        dto.setAuthor(rs.getString("author"));
        dto.setPublisher(rs.getString("publisher"));
        dto.setPrice(rs.getInt("price"));
        dto.setMemo(rs.getString("memo"));
        dto.setPublish_date(rs.getString("publish_date"));
        
        list.add(dto);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      DBClose.close(rs, pstmt, con);
    }
    
    return list;
  }
}
```



#### BookTest class

``` java
package homework;

import java.util.List;

public class BookTest {

  public static void main(String[] args) {
    
    BookDAO dao = new BookDAO();

    //delete(dao);
    //create(dao);
    //update(dao);
    list(dao);
    //read(dao);
    
  }

  private static void delete(BookDAO dao) {
    
    int isbn = 6;
    if(dao.delete(isbn)) {
      p("성공");
    }else {
      p("실패");
    }
    
  }

  private static void create(BookDAO dao) {
    BookDTO dto = new BookDTO(0, "회계학원론", "김경제", "회계출판", 20000,
        "create입니다.", "2022-05-04");
    if(dao.create(dto)) {
      p("성공");
    }else {
      p("실패");
    }
  }

  private static void update(BookDAO dao) {
    BookDTO dto = dao.read(5);
    dto.setTitle("MY SQL");
    dto.setMemo("수정합니다.");
    
    if(dao.update(dto)) {
      p("성공");
      p("-----------");
      dto = dao.read(5);
      p(dto);
    }else {
      p("실패");
    }
  }

  private static void read(BookDAO dao) {
    int isbn = 2;
    BookDTO dto = dao.read(isbn);
    p(dto);
    
  }

  private static void list(BookDAO dao) {

    List<BookDTO> list = dao.list();
    for(int i=0; i<list.size(); i++) {
      BookDTO dto = list.get(i);
      p(dto);
      p("-----------------");
    }
  }
  private static void p(String string) {
    System.out.println(string);
  }

  private static void p(BookDTO dto) {
    p("도서 번호: " + dto.getIsbn());
    p("도서 명: " + dto.getTitle());
    p("저자: " + dto.getAuthor());
    p("출판사: " + dto.getPublisher());
    p("가격: " + dto.getPrice());
    p("상세: " + dto.getMemo());
    p("발행일: " + dto.getPublish_date());

    
  }


}
```



#### book.sql

``` sql

-- 과제 
use javadb;
create table test1(
isbn			int primary key not null auto_increment,
title			varchar(60) not null,
author			varchar(260) not null,
publisher		varchar(60) not null,
price			int not null,
memo			varchar(200),
publish_date	date
);

insert into test1 (title, author, publisher, price, memo, publish_date)
values('햇님달님', '홍길동', '문화출판', '5000', '동화책', sysdate());
insert into test1 (title, author, publisher, price, memo, publish_date)
values('콩쥐팥쥐', '유재석', '우리출판', '7000', '동화책', sysdate());
insert into test1 (title, author, publisher, price, memo)
values('겁쟁이', '강호동', '연예출판', '10000', '소설책');
insert into test1 (title, author, publisher, price, memo, publish_date)
values('화폐전쟁', '강이슬', '머니출판', '20000', '경제책', sysdate());
insert into test1 (title, author, publisher, price, memo, publish_date)
values('달러구트', '송지효', '스마일출판', '12000', '베스트셀러', sysdate());

select isbn 도서번호, title 도서명, author 저자, publisher 출판사, price 가격, memo 상세, publish_date 발행일
 from test1;
 
 -- list
 select isbn 도서번호, title 도서명, author 저자, price 가격
 from test1;
 
 -- read
 select isbn 도서번호, title 도서명, author 저자, price 가격
 from test1
 where isbn = 2;
 
 -- create
insert into test1 (title, author, publisher, price, memo)
values('모모', '전현무', '아랍출판', '8000', '소설책');

-- update
update test1
set title ='MY SQL이다', memo = '수정입니다.'
where title = '콩쥐팥쥐';

-- delete
delete from test1
where isbn = 5;
```