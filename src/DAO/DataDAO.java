package DAO;

import java.sql.Statement;
import java.util.ArrayList;

import DTO.Data;

public class DataDAO extends DAOBase {
	private static DataDAO ddao = new DataDAO();

	private DataDAO() {
	}

	public static DataDAO getInstance() {
		return ddao;
	}

	public ArrayList<Data> list() {
		String sql = "select * from data";
		Statement st = null;
		ArrayList<Data> tmpList = new ArrayList<>();

		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				while (rs.next()) {
					Data tmpData = new Data();
					tmpData.setNo("no");
					tmpData.setTitle("title");
					tmpData.setUrl("url");
					tmpData.setText("text");
					tmpData.add(tmpData);
				}
			} catch (Exception e) {
				System.out.println("list 오류");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (rs != null)
						rs.close();
					if (st != null)
						st.close();
				} catch (Exception e) {
					System.out.println("close 오류");
				}
			}
		}
		return tmpList;
	}

	public void insert() {
		String sql="insert into data values(?, ?, ?, ?)"
	}
}
