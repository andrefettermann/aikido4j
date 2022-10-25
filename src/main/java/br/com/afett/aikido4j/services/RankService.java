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
	
	private void validateName(String rankName) throws Exception {
		if (rankName == null || rankName.isEmpty()) {
			throw new Exception("The rank name is mandatory!");
		}
	}
	
	private void validateRequiredTime(Long requiredTime) throws Exception {
		if (requiredTime == null) {
			throw new Exception("The rank required time is mandatory!");
		}	
	}
	
	private void validateNextRankName(String rankName, String nextRankName) 
			throws Exception {
		if (nextRankName != null) {
			if (rankName.equals(nextRankName)) {
				throw new Exception(
						"The next rank cant be the same as current rank!");
			}
		}	
	}
	
	private void validate(Rank newRank) throws Exception {
		if (newRank == null) throw new Exception("The rank is mandatory!");

		validateName(newRank.getName());
		validateRequiredTime(newRank.getRequiredTime());
		validateNextRankName(newRank.getName(), newRank.getNextRankName());
	}
		
	public void insert(Rank newRank) throws Exception {
			validate(newRank);
			ranks.put(newRank.getName(), newRank);
	}
}
