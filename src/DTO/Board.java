package DTO;

import java.sql.Timestamp;

//	DB table¸í: board
public class Board {

	private int no;
	private String title;
	private String pw;
	private String writer;
	private String content;
	private int b_view;
	private int b_like;
	private Timestamp up_date;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getB_view() {
		return b_view;
	}

	public void setB_view(int b_view) {
		this.b_view = b_view;
	}

	public int getB_like() {
		return b_like;
	}

	public void setB_like(int b_like) {
		this.b_like = b_like;
	}

	public Timestamp getUp_date() {
		return up_date;
	}

	public void setUp_date(Timestamp up_date) {
		this.up_date = up_date;
	}

	public void prtBoard() {
		System.out.println(no + "\t" + title + "\t" + pw + "\t" + writer + "\t" + content + "\t" + b_view + "\t"
				+ b_like + "\t" + up_date);
	}
}
