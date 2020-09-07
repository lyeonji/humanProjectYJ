package client;

import Main.Index;
import service.UserServiceINFImpl;

public class Home {
	private UserServiceINFImpl usii = UserServiceINFImpl.getInstance();

	public Home() {
		homeMain();
	}

	public void homeMain() {
		while (true) {
			menu();
			int selNum = Index.inputKeyNum();
			switch (selNum) {
			case 1:
				if (UserServiceINFImpl.currentUser == null) {
					usii.login();
				} else {
					usii.logout();
				}
				break;
			case 2:
				if (UserServiceINFImpl.currentUser == null) {
					usii.join();
				} else {
					new UserInfo();
				}
				break;
			case 3:
				new Board();
				break;
			case 4:
				return;
			default:
				System.out.println("올바른 숫자를 입력해주세요");
			}
		}
	}

	public void menu() {
		if (UserServiceINFImpl.currentUser == null) {
			System.out.print("1. 로그인 / 2. 회원가입");
		} else {
			System.out.print("1. 로그아웃 / 2. 회원정보");
		}
		System.out.println(" / 3. 게시판 입장 / 4. 끝내기");
	}
}
