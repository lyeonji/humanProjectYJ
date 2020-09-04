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
}
