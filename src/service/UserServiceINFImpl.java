package service;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.BoardDAO;
import DAO.UserDAO;
import DTO.Board;
import DTO.User;
import Main.Index;

public class UserServiceINFImpl implements UserServiceINF {

	public static User currentUser = null;
	private Scanner in = Index.in;
	private UserDAO uDAO = UserDAO.getInstance();
	private BoardDAO bDAO = BoardDAO.getInstance();

	private static UserServiceINFImpl usii = new UserServiceINFImpl();
	private UserServiceINFImpl() {
	}
	public static UserServiceINFImpl getInstance() {
		return usii;
	}

	@Override
	public void join() {
		User newUser = new User();
		while (true) {
			System.out.println("������ ID�� �Է��ϼ���");
			newUser.setId(in.nextLine());
			if (uDAO.selectOneById(newUser.getId())) {
				System.out.println("��밡���� ID�Դϴ�");
				break;
			} else {
				System.out.println("�̹� ���Ե� ���̵��Դϴ�");
			}
		}
		System.out.println("��й�ȣ�� �Է��ϼ���");
		newUser.setPw(in.nextLine());
		System.out.println("�г����� �Է��ϼ���");
		newUser.setNick(in.nextLine());
		int n = uDAO.insertOne(newUser);
		if (n != 0) {
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�");
		} else {
			System.out.println("ȸ�����Կ� ������ �߻��߽��ϴ�");
		}
	}

	@Override
	public void login() {
		System.out.println("ID�� �Է��ϼ���");
		String loginId = in.nextLine();
		System.out.println("��й�ȣ�� �Է��ϼ���");
		String loginPw = in.nextLine();
		currentUser = uDAO.selectOneByIdPw(loginId, loginPw);
		if (currentUser == null) {
			System.out.println("ID �Ǵ� ��й�ȣ�� Ȯ�����ּ���");
		} else {
			System.out.println("�α��� �Ǿ����ϴ�");
		}
	}

	@Override
	public void logout() {
		currentUser = null;
	}

	@Override
	public void changeNick() {
		System.out.println("��й�ȣ�� �Է����ּ���");
		String passWord = in.nextLine();
		if (currentUser.getPw().equals(passWord)) {
			System.out.println("������ �г����� �Է����ּ���");
			currentUser.setNick(in.nextLine());
			int n = uDAO.updateNick(currentUser);
			if (n != 0) {
				System.out.println("�г��� ������ �Ϸ�Ǿ����ϴ�");
			} else {
				System.out.println("�г��� ���濡 ������ �߻��߽��ϴ�");
			}
		} else {
			System.out.println("��й�ȣ�� �ٽ� Ȯ�����ּ���");
		}
	}

	@Override
	public void withdrawal() {
		System.out.println("��й�ȣ�� �Է����ּ���");
		String passWord = in.nextLine();
		if (currentUser.getPw().equals(passWord)) {
			int n = uDAO.deleteOneByUserNo(currentUser.getNo());
			if (n != 0) {
				System.out.println("ȸ�� ������ �Ϸ�Ǿ����ϴ�");
			} else {
				System.out.println("ȸ�� ������ ������ �߻��߽��ϴ�");
			}
		}
		logout();
	}

	@Override
	public void myPost() {
		ArrayList<Board> myList = bDAO.selectAllByWriter(currentUser.getNick());
		if (myList.size() > 0) {
			for (Board b : myList) {
				b.prtBoard();
			}
		} else {
			System.out.println("ȸ������ ��ϵ� ���� �����ϴ�");
		}
	}

	@Override
	public void postList() {
		ArrayList<Board> postList = bDAO.selectAll();
		if (postList.size() > 0) {
			for (Board b : postList) {
				b.prtBoard();
			}
		} else {
			System.out.println("���� ��ϵ� ���� �����ϴ�");
		}
	}

	@Override
	public Board readPost(int readNum) {
		bDAO.UpdateViewsByPostNo(readNum);
		return bDAO.selectOneByPostNo(readNum);
	}

	@Override
	public void posting() {
		Board newPost = new Board();
		if (currentUser == null) {
			System.out.println("�ۼ��ڸ� �Է����ּ���");
			newPost.setWriter(in.nextLine());
			System.out.println("�� ��й�ȣ�� �Է����ּ���");
			newPost.setPw(in.nextLine());
		} else {
			newPost.setWriter(currentUser.getNick());
			newPost.setPw(currentUser.getPw());
		}
		System.out.println("�� ������ �Է����ּ���");
		newPost.setTitle(in.nextLine());
		System.out.println("�� ������ �Է����ּ���");
		newPost.setContent(in.nextLine());
		int k = bDAO.insertOne(newPost);
		if (k != 0) {
			System.out.println("�� ����� �Ϸ�Ǿ����ϴ�");
		} else {
			System.out.println("�� ��Ͽ� ������ �߻��߽��ϴ�");
		}
	}

	@Override
	public void searchPost() {
		ArrayList<Board> searchList = null;
		System.out.println("1. �ۼ��� �˻� / 2. �� ����, ���� �˻�");
		int selNum = Index.inputKeyNum();
		switch (selNum) {
		case 1:
			System.out.println("�˻��� �ۼ��ڸ� �Է����ּ���");
			String writer = in.nextLine();
			searchList = bDAO.selectAllByWriter(writer);
			break;
		case 2:
			System.out.println("�˻��� Ű���带 �Է����ּ���");
			String keyword = in.nextLine();
			searchList = bDAO.selectAllByTitleContent(keyword);
			break;
		default:
			System.out.println("�ùٸ� ���ڸ� �Է����ּ���");
		}

		if (searchList != null && searchList.size() > 0) {
			for (Board b : searchList) {
				b.prtBoard();
			}
		} else {
			System.out.println("�˻� ����� �����ϴ�");
		}
	}

	@Override
	public void modifyPost(Board readPost) {
		if (currentUser != null && currentUser.getPw().equals(readPost.getPw())) {
			System.out.println("������ ������ �Է����ּ���");
			System.out.println("���� ���� : " + readPost.getTitle());
			readPost.setTitle(in.nextLine());
			System.out.println("������ ������ �Է����ּ���");
			System.out.println("���� ���� : " + readPost.getContent());
			readPost.setContent(in.nextLine());
			bDAO.updateOne(readPost);
		} else {
			System.out.println("�� ��й�ȣ�� �Է����ּ���");
			String pw = in.nextLine();
			if (pw.equals(readPost.getPw())) {
				System.out.println("������ ������ �Է����ּ���");
				System.out.println("���� ���� : " + readPost.getTitle());
				readPost.setTitle(in.nextLine());
				System.out.println("������ ������ �Է����ּ���");
				System.out.println("���� ���� : " + readPost.getContent());
				readPost.setContent(in.nextLine());
				bDAO.updateOne(readPost);
			} else {
				System.out.println("�� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
			}
		}
	}

	@Override
	public void deletePost(Board readPost) {
		if (currentUser != null && currentUser.getPw().equals(readPost.getPw())) {
			bDAO.deleteOneByPostNo(readPost.getNo());
		} else {
			System.out.println("�� ��й�ȣ�� �Է����ּ���");
			String pwd = in.nextLine();
			if (pwd.equals(readPost.getPw())) {
				bDAO.deleteOneByPostNo(readPost.getNo());
			} else {
				System.out.println("�� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
			}
		}
	}

	@Override
	public void boomUp(int postNo) {
		bDAO.updateBoomByPostNo(postNo);
	}
}
