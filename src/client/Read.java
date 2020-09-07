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
			System.out.println("해당 글이 없습니다");
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
				System.out.println("올바른 숫자를 입력해주세요");
			}
		}
	}

	public void menu() {
		System.out.println("1. 글 수정 / 2. 글 삭제 / 3. 추천하기 / 4. 목록보기");
	}

	public void postDetail(Board readPost) {
		System.out.println("제목 : " + readPost.getTitle() + " / 작성자 : " + readPost.getWriter());
		System.out.println("----------------------------------------");
		System.out.println(readPost.getContent());
		System.out.println("----------------------------------------");
		System.out.println("조회수 : " + readPost.getB_view() + " / 추천수 : " + readPost.getB_like());
	}
}
