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
			System.out.println("���� ����");
		} else {
			System.out.println("���� ����");
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
			System.out.println("��밡���� ���̵�");
		} else {
			System.out.println("�Է��� ���̵� �ߺ���");
		}
		System.out.println("---------------------");
		System.out.println("test2");
		String testID_2 = "testID_2";
		boolean dupChk_2 = testDAO.selectOneById(testID_2);
		if (dupChk_2) {
			System.out.println("��밡���� ���̵�");
		} else {
			System.out.println("�Է��� ���̵� �ߺ���");
		}
	}

	@Test
	public void selectOneByIdPwdTest() {
		User testUser = testDAO.selectOneByIdPw("testID", "testPWD");
		if (testUser != null) {
			System.out.println("�α��� ����");
			testUser.prtUserInfo();
		} else {
			System.out.println("���̵� Ȥ�� ��й�ȣ�� Ȯ�����ּ���");
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
			System.out.println("�г��� ���� ����");
		} else {
			System.out.println("�г��� ���� ����");
		}
	}
}
