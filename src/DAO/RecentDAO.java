package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Recent;

public class RecentDAO extends DAOBase {
	private static RecentDAO rdao = new RecentDAO();

	private RecentDAO() {
	}

	public static RecentDAO getInstance() {
		return rdao;
	}

	public ArrayList<Recent> list() {
		String sql = "select * from recent";
		Statement st = null;
		ArrayList<Recent> tmpList = new ArrayList<>();

		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				while (rs.next()) {
					Recent tmprList = new Recent();
					tmprList.setNo(rs.getInt("no"));
					tmprList.setWord(rs.getString("word"));
					tmprList.setCnt(rs.getInt("cnt"));
					tmpList.add(tmprList);
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

	public void insertOne(Recent recent) {
		String sql = "insert into recent values(recent_seq.nextVal,?,1)";
		PreparedStatement ppst = null;

		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, recent.getWord());
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

	public void delete(Recent delRecent) {
		String sql="delete from recent where title=?";
	}

}
