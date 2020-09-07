package DAO;

import java.util.ArrayList;

import DTO.User;

public class UserDAO extends DAOBase {
	private UserDAO() {
	}

	private static UserDAO udao = new UserDAO();

	public static UserDAO getInstance() {
		return udao;
	}

	public ArrayList<User> selectAll() {
		String sql = "select * from b_user";
		ArrayList<User> userList = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					User user = new User();
					user.setNo(rs.getInt("no"));
					user.setId(rs.getString("id"));
					user.setPw(rs.getString("pwd"));
					user.setNick(rs.getString("nick"));
					user.setB_date(rs.getTimestamp("b_date"));
					userList.add(user);
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return userList;
	}

	public int insertOne(User newUser) {
		String sql = "insert into b_user values(user_seq.nextVal, ?, ?, ?, sysdate)";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, newUser.getId());
				ppst.setString(2, newUser.getPw());
				ppst.setString(3, newUser.getNick());
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Insert");
			} finally {
				disconnect();
			}
		}
		return n;
	}
	
	public boolean selectOneById(String ID) {
		String sql = "select * from b_user where id = ?";
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, ID);
				rs = ppst.executeQuery();
				if (rs.next()) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return true;
	}
	
	public User selectOneByIdPw(String id, String pw) {
		String sql = "select * from b_user where id = ? and pw = ?";
		User login = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, id);
				ppst.setString(2, pw);
				rs = ppst.executeQuery();
				if (rs.next()) {
					login = new User();
					login.setNo(rs.getInt("no"));
					login.setId(rs.getString("id"));
					login.setPw(rs.getString("pw"));
					login.setNick(rs.getString("nick"));
					login.setB_date(rs.getTimestamp("b_date"));
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return login;
	}
	
	
	public int updateNick(User uUser) {
		String sql = "update b_user set nick = ? where id = ? and pw = ?";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, uUser.getNick());
				ppst.setString(2, uUser.getId());
				ppst.setString(3, uUser.getPw());
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Update");
			} finally {
				disconnect();
			}
		}
		return n;
	}
	
	public int deleteOneByUserNo(int no) {
		String sql = "delete from b_user where no = ?";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Delete");
			} finally {
				disconnect();
			}
		}
		return n;
	}
}
