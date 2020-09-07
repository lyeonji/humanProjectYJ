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
		System.out.print(" / �г��� : " + UserServiceINFImpl.currentUser.getNick());
		System.out.println(" / �������� : " + UserServiceINFImpl.currentUser.getB_date());
		System.out.println("1. �г��� ���� / 2. ȸ�� Ż�� / 3. ���� �� �� ���� / 4. ���� �޴�");
	}

}
