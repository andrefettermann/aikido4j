package br.com.afett.aikido4j.daos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.Address;
import br.com.afett.aikido4j.entities.Person;
import br.com.afett.aikido4j.entities.PromotionTest;
import br.com.afett.aikido4j.entities.Rank;

public class PromotionTestsMockDao implements Dao<PromotionTest>{
	
	private List<PromotionTest> data = new ArrayList<>();
	
	private PromotionTest setBean(String name, Address address, LocalDate date
			, List<Rank> ranks, String remarks, List<Person> sensei
			, String venue) {

		PromotionTest promotionTest = new PromotionTest(name);
		promotionTest.setAddress(address);
		promotionTest.setDate(date);
		promotionTest.setId(name);
		promotionTest.setRanks(ranks);
		promotionTest.setSensei(sensei);
		promotionTest.setRemarks(remarks);
		promotionTest.setVenue(venue);
		
		return promotionTest;
	}
	
	public PromotionTestsMockDao() {
		data.add(this.setBean(
				"1st Exam", null, LocalDate.now(), null, null, null, null));
		
		List<Rank> ranks = new ArrayList<>();
		Rank rank = new Rank("1st Kyu", 60L, true);
		rank.setBelt("Brown");
		rank.setNextRankId("Shodan");
		data.add(this.setBean(
				"2nd Exam", null, LocalDate.now(), ranks, null, null, null));
		
	}

	@Override
	public PromotionTest get(String id) {
		PromotionTest promotionTest = null;
		for (PromotionTest p:data) {
			if (p.getId().equals(id)) promotionTest = p;
		}
		return promotionTest;
	}

	@Override
	public List<PromotionTest> getAll() {
		return data;
	}

	@Override
	public void save(PromotionTest p) {
		p.setId(p.getName());
		data.add(p);
	}

	@Override
	public void update(PromotionTest p, String[] params) {
	}

	@Override
	public void delete(PromotionTest p) {
	}

}
