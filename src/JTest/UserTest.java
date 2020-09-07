package JTest;

import java.util.ArrayList;

import org.junit.Test;

import DAO.UserDAO;
import DTO.User;


public class UserTest {

	UserDAO testDAO = UserDAO.getInstance();

	@Test
	public void insertOneTest() {
		User testUser = new User();
		testUser.setId("testID");
		testUser.setPw("testPWD");
		testUser.setNick("testNick");
		int n = testDAO.insertOne(testUser);
		if (n != 0) {
			System.out.println("가입 성공");
		} else {
			System.out.println("가입 오류");
		}
	}

	@Test
	public void selectAllTest() {
		ArrayList<User> testList = testDAO.selectAll();
		for (User u : testList) {
			u.prtUserInfo();
		}
	}

	@Test
	public void selectOneById() {
		System.out.println("test1");
		String testID_1 = "testID";
		boolean dupChk_1 = testDAO.selectOneById(testID_1);
		if (dupChk_1) {
			System.out.println("사용가능한 아이디");
		} else {
			System.out.println("입력한 아이디가 중복됨");
		}
		System.out.println("---------------------");
		System.out.println("test2");
		String testID_2 = "testID_2";
		boolean dupChk_2 = testDAO.selectOneById(testID_2);
		if (dupChk_2) {
			System.out.println("사용가능한 아이디");
		} else {
			System.out.println("입력한 아이디가 중복됨");
		}
	}

	@Test
	public void selectOneByIdPwdTest() {
		User testUser = testDAO.selectOneByIdPw("testID", "testPWD");
		if (testUser != null) {
			System.out.println("로그인 성공");
			testUser.prtUserInfo();
		} else {
			System.out.println("아이디 혹은 비밀번호를 확인해주세요");
		}
	}

	@Test
	public void updateNickTest() {
		User testUser = new User();
		testUser.setId("testID");
		testUser.setPw("testPWD");
		testUser.setNick("newTestNick");
		int k = testDAO.updateNick(testUser);
		if (k != 0) {
			System.out.println("닉네임 변경 성공");
		} else {
			System.out.println("닉네임 변경 오류");
		}
	}
}
