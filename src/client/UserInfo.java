package client;

import Main.Index;
import service.UserServiceINFImpl;

public class UserInfo {

	private UserServiceINFImpl usii = UserServiceINFImpl.getInstance();

	public UserInfo() {
		userInfoMain();
	}

	public void userInfoMain() {
		while (true) {
			userInfoMenu();
			int selNum = Index.inputKeyNum();
			switch (selNum) {
			case 1:
				usii.changeNick();
				break;
			case 2:
				usii.withdrawal();
				return;
			case 3:
				usii.myPost();
				break;
			case 4:
				return;
			}
		}
	}

	public void userInfoMenu() {
		System.out.print("ID : " + UserServiceINFImpl.currentUser.getId());
		System.out.print(" / 닉네임 : " + UserServiceINFImpl.currentUser.getNick());
		System.out.println(" / 가입일자 : " + UserServiceINFImpl.currentUser.getB_date());
		System.out.println("1. 닉네임 변경 / 2. 회원 탈퇴 / 3. 내가 쓴 글 보기 / 4. 이전 메뉴");
	}

}
