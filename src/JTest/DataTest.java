package JTest;

import java.util.ArrayList;

import org.junit.Test;

import DAO.DataDAO;
import DTO.Data;

public class DataTest {
	DataDAO ddao = DataDAO.getInstance();

	@Test
	public void list() {
		ArrayList<Data> testList = ddao.list();

		for (Data d : testList) {
			System.out.println("No : " + d.getNo() + " / Title : " + d.getTitle() + " / URL : " + d.getUrl()
					+ " / Text : " + d.getText());
		}
		System.out.println();
	}

//	@Test
//	public void insertTest() {
//		Data tmpData = new Data();
//		tmpData.setTitle("검색어");
//		tmpData.setUrl("www.naver.com");
//		tmpData.setText("설명란입니다");
//		ddao.insert(tmpData);
//	}

	@Test
	public void insertTest2() {
		Data tmpData = new Data();
		tmpData.setNo(2);
		tmpData.setTitle("검색어2");
		tmpData.setUrl("www.naver.com");
		tmpData.setText("설명란입니다");
		ddao.insert(tmpData);
	}

	@Test
	public void delTest() {
		Data delData = new Data();
		delData.setTitle("검색어");
		ddao.delete(delData);
	}

	@Test
	public void updateTest() {
		String url = "url 수정";
		String text = "text 수정";
		String title = "검색어";
		ddao.update(title, url, text);
	}
}
