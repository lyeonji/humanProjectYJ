package DTO;

import java.sql.Timestamp;

//	DB table¸í: b_user
public class User {
	private int no;
	private String id;
	private String pw;
	private String nick;
	private Timestamp b_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Timestamp getB_date() {
		return b_date;
	}
	public void setB_date(Timestamp b_date) {
		this.b_date = b_date;
	}
	
	public void prtUserInfo() {
		System.out.print("User No : " + no);
		System.out.print(" / ID : " + id);
		System.out.print(" / Password : " + pw);
		System.out.print(" / Nick : " + nick);
		System.out.println(" / Join Date : " + b_date);
	}
}
