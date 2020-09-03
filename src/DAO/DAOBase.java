package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DAOBase {
	protected Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@175.204.61.164:1521:orcl";
	private String user = "system";
	private String pwd = "1104";
	protected ResultSet rs = null;

	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			return conn;
		} catch (Exception e) {
			System.out.println("DB 연결 실패 했습니다");
		}
		return null;
	}
}
