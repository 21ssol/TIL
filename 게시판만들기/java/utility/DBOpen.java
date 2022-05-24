package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {

  //정적 변수 static
  private static Connection con; //자동초기화
  
  public static Connection getConnection() {
    
    //드라이버 올리는 역할
    try {
      Class.forName(Constant.DRIVER);
      con = DriverManager.getConnection(Constant.URL,Constant.USER , 
          Constant.PASSWD);
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return con;
  }
}
