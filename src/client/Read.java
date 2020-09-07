package client;

import DTO.Board;

import Main.Index;
import service.UserServiceINFImpl;

public class Read {

	private UserServiceINFImpl usii = UserServiceINFImpl.getInstance();
	private Board readPost = null;

	public Read(Board readPost) {
		if (readPost != null) {
			this.readPost = readPost;
			readMain();
		} else {
			System.out.println("�ش� ���� �����ϴ�");
		}
	}

	public void readMain() {
		while (true) {
			postDetail(readPost);
			menu();
			int selNum = Index.inputKeyNum();
			switch (selNum) {
			case 1:
				usii.modifyPost(readPost);
				break;
			case 2:
				usii.deletePost(readPost);
				return;
			case 3:
				usii.boomUp(readPost.getNo());
				break;
			case 4:
				return;
			default:
				System.out.println("�ùٸ� ���ڸ� �Է����ּ���");
			}
		}
	}

	public void menu() {
		System.out.println("1. �� ���� / 2. �� ���� / 3. ��õ�ϱ� / 4. ��Ϻ���");
	}

	public void postDetail(Board readPost) {
		System.out.println("���� : " + readPost.getTitle() + " / �ۼ��� : " + readPost.getWriter());
		System.out.println("----------------------------------------");
		System.out.println(readPost.getContent());
		System.out.println("----------------------------------------");
		System.out.println("��ȸ�� : " + readPost.getB_view() + " / ��õ�� : " + readPost.getB_like());
	}
}
