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
			System.out.println("가입할 ID를 입력하세요");
			newUser.setId(in.nextLine());
			if (uDAO.selectOneById(newUser.getId())) {
				System.out.println("사용가능한 ID입니다");
				break;
			} else {
				System.out.println("이미 가입된 아이디입니다");
			}
		}
		System.out.println("비밀번호를 입력하세요");
		newUser.setPw(in.nextLine());
		System.out.println("닉네임을 입력하세요");
		newUser.setNick(in.nextLine());
		int n = uDAO.insertOne(newUser);
		if (n != 0) {
			System.out.println("회원가입이 완료되었습니다");
		} else {
			System.out.println("회원가입에 오류가 발생했습니다");
		}
	}

	@Override
	public void login() {
		System.out.println("ID를 입력하세요");
		String loginId = in.nextLine();
		System.out.println("비밀번호를 입력하세요");
		String loginPw = in.nextLine();
		currentUser = uDAO.selectOneByIdPw(loginId, loginPw);
		if (currentUser == null) {
			System.out.println("ID 또는 비밀번호를 확인해주세요");
		} else {
			System.out.println("로그인 되었습니다");
		}
	}

	@Override
	public void logout() {
		currentUser = null;
	}

	@Override
	public void changeNick() {
		System.out.println("비밀번호를 입력해주세요");
		String passWord = in.nextLine();
		if (currentUser.getPw().equals(passWord)) {
			System.out.println("변경할 닉네임을 입력해주세요");
			currentUser.setNick(in.nextLine());
			int n = uDAO.updateNick(currentUser);
			if (n != 0) {
				System.out.println("닉네임 변경이 완료되었습니다");
			} else {
				System.out.println("닉네임 변경에 오류가 발생했습니다");
			}
		} else {
			System.out.println("비밀번호를 다시 확인해주세요");
		}
	}

	@Override
	public void withdrawal() {
		System.out.println("비밀번호를 입력해주세요");
		String passWord = in.nextLine();
		if (currentUser.getPw().equals(passWord)) {
			int n = uDAO.deleteOneByUserNo(currentUser.getNo());
			if (n != 0) {
				System.out.println("회원 삭제가 완료되었습니다");
			} else {
				System.out.println("회원 삭제에 오류가 발생했습니다");
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
			System.out.println("회원님의 등록된 글이 없습니다");
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
			System.out.println("현재 등록된 글이 없습니다");
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
			System.out.println("작성자를 입력해주세요");
			newPost.setWriter(in.nextLine());
			System.out.println("글 비밀번호를 입력해주세요");
			newPost.setPw(in.nextLine());
		} else {
			newPost.setWriter(currentUser.getNick());
			newPost.setPw(currentUser.getPw());
		}
		System.out.println("글 제목을 입력해주세요");
		newPost.setTitle(in.nextLine());
		System.out.println("글 내용을 입력해주세요");
		newPost.setContent(in.nextLine());
		int k = bDAO.insertOne(newPost);
		if (k != 0) {
			System.out.println("글 등록이 완료되었습니다");
		} else {
			System.out.println("글 등록에 오류가 발생했습니다");
		}
	}

	@Override
	public void searchPost() {
		ArrayList<Board> searchList = null;
		System.out.println("1. 작성자 검색 / 2. 글 제목, 내용 검색");
		int selNum = Index.inputKeyNum();
		switch (selNum) {
		case 1:
			System.out.println("검색할 작성자를 입력해주세요");
			String writer = in.nextLine();
			searchList = bDAO.selectAllByWriter(writer);
			break;
		case 2:
			System.out.println("검색할 키워드를 입력해주세요");
			String keyword = in.nextLine();
			searchList = bDAO.selectAllByTitleContent(keyword);
			break;
		default:
			System.out.println("올바른 숫자를 입력해주세요");
		}

		if (searchList != null && searchList.size() > 0) {
			for (Board b : searchList) {
				b.prtBoard();
			}
		} else {
			System.out.println("검색 결과가 없습니다");
		}
	}

	@Override
	public void modifyPost(Board readPost) {
		if (currentUser != null && currentUser.getPw().equals(readPost.getPw())) {
			System.out.println("변경할 제목을 입력해주세요");
			System.out.println("현재 제목 : " + readPost.getTitle());
			readPost.setTitle(in.nextLine());
			System.out.println("변경할 내용을 입력해주세요");
			System.out.println("현재 내용 : " + readPost.getContent());
			readPost.setContent(in.nextLine());
			bDAO.updateOne(readPost);
		} else {
			System.out.println("글 비밀번호를 입력해주세요");
			String pw = in.nextLine();
			if (pw.equals(readPost.getPw())) {
				System.out.println("변경할 제목을 입력해주세요");
				System.out.println("현재 제목 : " + readPost.getTitle());
				readPost.setTitle(in.nextLine());
				System.out.println("변경할 내용을 입력해주세요");
				System.out.println("현재 내용 : " + readPost.getContent());
				readPost.setContent(in.nextLine());
				bDAO.updateOne(readPost);
			} else {
				System.out.println("글 비밀번호가 틀렸습니다");
			}
		}
	}

	@Override
	public void deletePost(Board readPost) {
		if (currentUser != null && currentUser.getPw().equals(readPost.getPw())) {
			bDAO.deleteOneByPostNo(readPost.getNo());
		} else {
			System.out.println("글 비밀번호를 입력해주세요");
			String pwd = in.nextLine();
			if (pwd.equals(readPost.getPw())) {
				bDAO.deleteOneByPostNo(readPost.getNo());
			} else {
				System.out.println("글 비밀번호가 틀렸습니다");
			}
		}
	}

	@Override
	public void boomUp(int postNo) {
		bDAO.updateBoomByPostNo(postNo);
	}
}
