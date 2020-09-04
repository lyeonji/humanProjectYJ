package JTest;

import java.util.ArrayList;

import org.junit.Test;

import DAO.RecentDAO;
import DTO.Recent;

public class RecentTest {
	RecentDAO rdao = RecentDAO.getInstance();

	@Test
	public void r_list() {
		ArrayList<Recent> rlist = rdao.list();

		for (Recent r : rlist) {
			System.out.println(r.getNo() + " " + r.getWord() + " " + r.getCnt());
		}
	}

	@Test
	public void insertOne() {
		Recent recent = new Recent();
		recent.setWord("°Ë»ö¾î");
		rdao.insertOne(recent);
	}
}
