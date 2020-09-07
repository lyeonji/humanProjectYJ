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
				System.out.println("올바른 숫자를 입력해주세요");
			}
		}
	}

	public void menu() {
		System.out.println("1. 글 목록 / 2. 글 읽기 / 3. 글 쓰기 / 4. 글 검색 / 5. 이전메뉴");
	}

	public void read() {
		System.out.println("읽을 글 번호를 입력해주세요");
		int readNum = Index.inputKeyNum();
		new Read(usii.readPost(readNum));
	}
}
