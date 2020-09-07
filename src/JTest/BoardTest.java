package JTest;

import java.util.ArrayList;

import org.junit.Test;

import DAO.BoardDAO;
import DTO.Board;

public class BoardTest {
	BoardDAO testDAO = BoardDAO.getInstance();

	@Test
	public void insertOneTest() {
		Board testPost = new Board();
		testPost.setTitle("testTitle");
		testPost.setWriter("testWriter");
		testPost.setPw("testPWD");
		testPost.setContent("testContent");

		int n = testDAO.insertOne(testPost);

		if (n != 0) {
			System.out.println("�۾��� ����");
		} else {
			System.out.println("�۾��� ����");
		}
	}

	@Test
	public void selectAllTest() {
		ArrayList<Board> testList = testDAO.selectAll();
		for (Board b : testList) {
			b.prtBoard();
		}
	}

	@Test
	public void selectAllByTitleContentTest() {
		String keyWord = "apple";
		ArrayList<Board> testList = testDAO.selectAllByTitleContent(keyWord);
		for (Board b : testList) {
			b.prtBoard();
		}
	}

	@Test
	public void selectAllByWriterTest() {
		String writer = "lee";
		ArrayList<Board> testList = testDAO.selectAllByWriter(writer);
		for (Board b : testList) {
			b.prtBoard();
		}
	}

	@Test
	public void selectOneByPostNoTest() {
		int testNo = 5;
		Board testPost = testDAO.selectOneByPostNo(testNo);
		if (testPost != null) {
			testPost.prtBoard();
			System.out.println(testPost.getContent());
		} else {
			System.out.println("�ش� no�� board�� �����ϴ�");
		}
	}

	@Test
	public void updateOneTest() {
		Board testPost = new Board();
		testPost.setNo(5);
		testPost.setTitle("applel");
		testPost.setContent("applt");
		int n = testDAO.updateOne(testPost);
		if (n != 0) {
			System.out.println("�� �� �� ��");
		} else {
			System.out.println("�� �� �� ��");
		}
	}

	@Test
	public void deleteOneByPostNoTest() {
		int no = 3;
		int n = testDAO.deleteOneByPostNo(no);
		if (n != 0) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("���� ����");
		}
	}

	@Test
	public void UpdateViewsByPostNoTest() {
		int no = 5;
		int n = testDAO.UpdateViewsByPostNo(no);
		if (n != 0) {
			System.out.println("��ȸ �Ϸ�");
		} else {
			System.out.println("��ȸ ����");
		}
	}
	
	@Test
	public void UpdateBoomByPostNoTest() {
		int no = 5;
		int n = testDAO.updateBoomByPostNo(no);
		if (n != 0) {
			System.out.println("��õ �Ϸ�");
		} else {
			System.out.println("��õ ����");
		}
	}
	
	@Test
	public void selectAllByViewsTest() {
		ArrayList<Board> testList = testDAO.selectAllByView(2);
		for (Board b : testList) {
			b.prtBoard();
		}
	}
}
