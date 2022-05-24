package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Test {

	public static void main(String[] args) {
		Connection con = DBOpen.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql=" SELECT COUNT(*) cnt FROM information_schema.tables WHERE table_schema = 'webtest' ";
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()==true) {
				System.out.println("현재 webtest DB에 생성된 테이블 갯수 : " + rs.getInt("cnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
	}

}
