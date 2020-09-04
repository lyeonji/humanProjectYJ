package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
					tmpData.setNo(rs.getInt("no"));
					tmpData.setTitle(rs.getString("title"));
					tmpData.setUrl(rs.getString("url"));
					tmpData.setText(rs.getString("text"));
					tmpList.add(tmpData);
				}
			} catch (Exception e) {
				System.out.println("list 오류");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (Exception e) {
					System.out.println("close 오류");
				}
			}
		}
		return tmpList;
	}

	public void insert(Data newData) {
		String sql = "insert into data values(data_seq.nextVal, ?, ?, ?)";
		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, newData.getTitle());
				ppst.setString(2, newData.getUrl());
				ppst.setString(3, newData.getText());
				ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("insert 오류");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e) {
					System.out.println("close 오류");
				}
			}
		}
	}

	public void delete(Data delData) {
		String sql = "delete from data where title=?";
		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, delData.getTitle());
				ppst.executeQuery();
			} catch (Exception e) {
				System.out.println("delete 오류");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e2) {
					System.out.println("close 오류");
				}
			}
		}
	}

	public void update(String title, String url, String text) {
		String sql = "update data set url=?, text=? where title=?";
		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, url);
				ppst.setString(2, text);
				ppst.setString(3, title);
				ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("update 오류");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (ppst != null)
						ppst.close();
				} catch (Exception e) {
					System.out.println("close 실패");
				}
			}
		}
	}
}
