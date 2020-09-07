package Main;

import java.util.Scanner;

import client.Home;

public class Index {
	public static Scanner in=new Scanner(System.in);
	
	public static void main(String[] args) {
		new Home();
	}
	
	public static int inputKeyNum() {
		int n = 0;
		try {
			n = in.nextInt();
		} catch (Exception e) {
			System.out.println("숫자만 입력해주세요");
		}
		in.nextLine();
		return n;
	}
}
