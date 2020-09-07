package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOBase {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:orcl";
	private static String user = "system";
	private static String pw = "1104";
	protected Connection conn = null;
	protected ResultSet rs = null;
	protected PreparedStatement ppst = null;
	protected Statement st;

	public Connection conn() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("DB 로딩 실패");
		}
		try {
			conn = DriverManager.getConnection(url, user, pw);
			return conn;
		} catch (SQLException e) {
			System.out.println("Connect 실패");
		}
		return null;
	}

	public void disconnect() {
		try {
			if (conn != null)
				conn.close();
			if (ppst != null)
				ppst.close();
			if (st != null)
				st.close();
		} catch (Exception e) {
			System.out.println("Close 실패");
		}
	}
}
