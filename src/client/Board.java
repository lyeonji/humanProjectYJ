package client;

import Main.Index;
import service.UserServiceINFImpl;

public class Board {

	private UserServiceINFImpl usii = UserServiceINFImpl.getInstance();

	public Board() {
		boardMain();
	}

	public void boardMain() {
		while (true) {
			menu();
			int selNum = Index.inputKeyNum();
			switch (selNum) {
			case 1:
				usii.postList();
				break;
			case 2:
				read();
				break;
			case 3:
				usii.posting();
				break;
			case 4:
				usii.searchPost();
				break;
			case 5:
				return;
			default:
				System.out.println("�ùٸ� ���ڸ� �Է����ּ���");
			}
		}
	}

	public void menu() {
		System.out.println("1. �� ��� / 2. �� �б� / 3. �� ���� / 4. �� �˻� / 5. �����޴�");
	}

	public void read() {
		System.out.println("���� �� ��ȣ�� �Է����ּ���");
		int readNum = Index.inputKeyNum();
		new Read(usii.readPost(readNum));
	}
}
