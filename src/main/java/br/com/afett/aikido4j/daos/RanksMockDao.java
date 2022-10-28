package br.com.afett.aikido4j.daos;

import java.util.ArrayList;
import java.util.List;

import br.com.afett.aikido4j.entities.Rank;

public class RanksMockDao implements Dao<Rank>{
	
	private List<Rank> ranks = new ArrayList<>();
	
	private Rank setBean(String name, Long requiredTime, boolean testRequired
			, String nextRankId
			, String belt) {
		
		Rank rank = new Rank(name, requiredTime, testRequired);
		rank.setId(rank.getName());
		rank.setNextRankId(nextRankId);
		rank.setBelt(belt);
		
		return rank;
	}
	
	public RanksMockDao() {
		ranks.add(this.setBean("6th Kyu", 0L, false, "5th Kyu", "White"));
		ranks.add(this.setBean("5th Kyu", 60L, true, "4th Kyu", "Yellow"));
		ranks.add(this.setBean("4th Kyu", 75L, true, "3rd Kyu", "Purple"));
		ranks.add(this.setBean("3rd Kyu", 90L, true, "2nd Kyu", "Green"));
		ranks.add(this.setBean("2nd Kyu", 100L, true, "1st Kyu", "Blue"));
		ranks.add(this.setBean("1st Kyu", 110L, true, "Shodan", "Brown"));
		ranks.add(this.setBean("Shodan", 120L, true, "Nidan", "Black"));
		ranks.add(this.setBean("Nidan", 120L, true, "Sandan", "Black"));
		ranks.add(this.setBean("Sandan", 120L, true, "Yondan", "Black"));
		ranks.add(this.setBean("Yondan", 120L, true, "Godan", "Black"));
		ranks.add(this.setBean("Godan", 120L, true, "Rokudan", "Black"));
		ranks.add(this.setBean("Rokudan", 120L, true, "", "Black"));
	}

	@Override
	public Rank get(String id) {
		Rank rank = null;
		for (Rank r:ranks) {
			if (r.getId().equals(id)) rank = r;
		}
		return rank;
	}

	@Override
	public List<Rank> getAll() {
		return ranks;
	}

	@Override
	public void save(Rank r) {
		r.setId(r.getName());
		ranks.add(r);
	}

	@Override
	public void update(Rank t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Rank t) {
		// TODO Auto-generated method stub
		
	}

}
