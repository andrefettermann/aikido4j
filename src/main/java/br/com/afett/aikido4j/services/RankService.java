package br.com.afett.aikido4j.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.afett.aikido4j.entities.Rank;

public class RankService {
	
	private Map<String, Rank> ranks = new HashMap<>();
	
	private static RankService instance = new RankService();
	
	private RankService() {}
	
	public static RankService getInstance() {
		return instance;
	}
	
	public Rank find(String rankId) {
		return ranks.get(rankId);
	}
	
	public List<Rank> list() {
		return ranks.values().stream().collect(Collectors.toList());
	}
	
	private void validate(Rank newRank) throws Exception {
		if (newRank == null) {
			throw new Exception("The rank is mandatory!");
		} else {
			if (newRank.getRankName() == null || newRank.getRankName().isEmpty()) {
				throw new Exception("The rank name is mandatory!");
			}
			
			if (newRank.getRequiredTime() == null) {
				throw new Exception("The rank required time is mandatory!");
			}
			
			if (newRank.getNextRank() != null) {
				if (newRank.getRankName().equals(newRank.getNextRank())) {
					throw new Exception(
							"The next rank cant be the same as current rank!");
				}
			}
		}
	}
		
	public void insert(Rank newRank) throws Exception {
			validate(newRank);
			ranks.put(newRank.getRankName(), newRank);
	}
}
