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
				System.out.println("�ùٸ� ���ڸ� �Է����ּ���");
			}
		}
	}

	public void menu() {
		if (UserServiceINFImpl.currentUser == null) {
			System.out.print("1. �α��� / 2. ȸ������");
		} else {
			System.out.print("1. �α׾ƿ� / 2. ȸ������");
		}
		System.out.println(" / 3. �Խ��� ���� / 4. ������");
	}
}
