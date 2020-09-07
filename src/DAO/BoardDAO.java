package DAO;

import java.util.ArrayList;

import DTO.Board;

public class BoardDAO extends DAOBase {
	private BoardDAO() {
	}

	private static BoardDAO bdao = new BoardDAO();

	public static BoardDAO getInstance() {
		return bdao;
	}

	public ArrayList<Board> selectAll() {
		String sql = "select * from board order by b_date desc";
		ArrayList<Board> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Board tempboard = new Board();
					tempboard.setNo(rs.getInt("no"));
					tempboard.setTitle(rs.getString("title"));
					tempboard.setWriter(rs.getString("writer"));
					tempboard.setPw(rs.getString("pwd"));
					tempboard.setContent(rs.getString("content"));
					tempboard.setB_view(rs.getInt("b_view"));
					tempboard.setB_like(rs.getInt("b_like"));
					tempboard.setUp_date(rs.getTimestamp("up_date"));
					tempList.add(tempboard);
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return tempList;
	}
	
	public ArrayList<Board> selectAllByTitleContent(String keyword) {
		String sql = "select * from board where title like ? or content like ? order by postdate desc";
		ArrayList<Board> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, "%" + keyword + "%");
				ppst.setString(2, "%" + keyword + "%");
				rs = ppst.executeQuery();
				while (rs.next()) {
					Board tempboard = new Board();
					tempboard.setNo(rs.getInt("no"));
					tempboard.setTitle(rs.getString("title"));
					tempboard.setWriter(rs.getString("writer"));
					tempboard.setPw(rs.getString("pw"));
					tempboard.setContent(rs.getString("content"));
					tempboard.setB_view(rs.getInt("b_view"));
					tempboard.setB_like(rs.getInt("b_like"));
					tempboard.setUp_date(rs.getTimestamp("up_date"));
					tempList.add(tempboard);
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return tempList;
	}

	public ArrayList<Board> selectAllByWriter(String writer) {
		String sql = "select * from board where writer = ? order by postdate desc";
		ArrayList<Board> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, writer);
				rs = ppst.executeQuery();
				while (rs.next()) {
					Board tempPost = new Board();
					tempPost.setNo(rs.getInt("no"));
					tempPost.setTitle(rs.getString("title"));
					tempPost.setWriter(rs.getString("writer"));
					tempPost.setPw(rs.getString("pwd"));
					tempPost.setContent(rs.getString("content"));
					tempPost.setB_view(rs.getInt("b_views"));
					tempPost.setB_like(rs.getInt("b_like"));
					tempPost.setUp_date(rs.getTimestamp("up_date"));
					tempList.add(tempPost);
				}
				return tempList;
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return null;
	}
	
	public Board selectOneByPostNo(int no) {
		String sql = "select * from board where no = ? order by postdate desc";
		Board tempPost = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				rs = ppst.executeQuery();
				if(rs.next()) {
					tempPost = new Board();
					tempPost.setNo(rs.getInt("no"));
					tempPost.setTitle(rs.getString("title"));
					tempPost.setWriter(rs.getString("writer"));
					tempPost.setPw(rs.getString("pw"));
					tempPost.setContent(rs.getString("content"));
					tempPost.setB_view(rs.getInt("b_view"));
					tempPost.setB_like(rs.getInt("b_like"));
					tempPost.setUp_date(rs.getTimestamp("up_date"));
				}
			} catch (Exception e) {
				System.out.println("Fail to Select");
			}
		}	
		return tempPost;
	}
	
	public int insertOne(Board newPost) {
		String sql = "insert into board values(board_seq.nextVal, ?, ?, ?, ?, default, default, default)";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, newPost.getTitle());
				ppst.setString(2, newPost.getWriter());
				ppst.setString(3, newPost.getPw());
				ppst.setString(4, newPost.getContent());
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Insert");
			} finally {
				disconnect();
			}
		}
		return n;
	}
	
	
	public int updateOne(Board updatePost) {
		String sql = "update board set title = ?, content = ? where no = ?";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, updatePost.getTitle());
				ppst.setString(2, updatePost.getContent());
				ppst.setInt(3, updatePost.getNo());
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Update");
			} finally {
				disconnect();
			}
		}
		return n;
	}

	public int deleteOneByPostNo(int no) {
		String sql = "delete from board where no = ?";
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

	public int UpdateViewsByPostNo(int no) {
		String sql = "update board set b_views = b_views + 1 where no = ?";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Update");
			} finally {
				disconnect();
			}
		}
		return n;
	}

	public int updateBoomByPostNo(int no) {
		String sql = "update board set b_like = b_like + 1 where no = ?";
		int n = 0;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				n = ppst.executeUpdate();
			} catch (Exception e) {
				System.out.println("Fail to Update");
			} finally {
				disconnect();
			}
		}
		return n;
	}

	public ArrayList<Board> selectAllByView(int b_view) {
		String sql = "select * from board where b_view >= ?";
		ArrayList<Board> postList = new ArrayList<>();
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, b_view);
				rs = ppst.executeQuery();
				while (rs.next()) {
					Board newPost = new Board();
					newPost.setNo(rs.getInt("no"));
					newPost.setTitle(rs.getString("title"));
					newPost.setWriter(rs.getString("writer"));
					newPost.setPw(rs.getString("pw"));
					newPost.setContent(rs.getString("content"));
					newPost.setB_view(rs.getInt("b_view"));
					newPost.setB_like(rs.getInt("b_like"));
					newPost.setUp_date(rs.getTimestamp("up_date"));
					postList.add(newPost);
				}
				return postList;
			} catch (Exception e) {
				System.out.println("Fail to Select");
			} finally {
				disconnect();
			}
		}
		return null;
	}
}
