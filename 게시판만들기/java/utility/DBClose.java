package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClose {
  
  public static void close(Connection con) { //메소드 오버로딩
    try {
      if (con != null)
        con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void close(PreparedStatement pstmt, Connection con) {
    try {
      if (pstmt != null)
        pstmt.close();
    } catch (SQLException e) {
    }
    try {
      if (con != null)
        con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {

    try {
      if (rs != null)
        rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      if (pstmt != null)
        pstmt.close();
    } catch (SQLException e) {
    }
    try {
      if (con != null)
        con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
