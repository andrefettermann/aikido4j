package br.com.afett.aikido4j.entities;

public class Rank {

	private String id;
	private String rankName;
	private Long requiredTime;
	private boolean testRequired;
	private String nextRank;
	
	public Rank(String rankName, Long requiredTime, boolean testRequired) {
		this.rankName = rankName;
		this.requiredTime = requiredTime;
		this.testRequired = testRequired;
	}

	public final String getId() {
		return this.id;
	}
	
	public final void setId(String id) {
		this.id = id;
	}
	
	public final String getRankName() {
		return this.rankName;
	}

	public final void setRankName(final String rankName) {
		this.rankName = rankName;
	}

	public final Long getRequiredTime() {
		return this.requiredTime;
	}

	public final void setRequiredTime(final Long requiredTime) {
		this.requiredTime = requiredTime;
	}
	
	public final String getNextRank() {
		return this.nextRank;
	}

	public void setNextRank(String nextRank) {
		this.nextRank = nextRank;
	}
	
	public boolean isTestRequired() {
		return this.testRequired;
	}
	
	public void setTestRequired(boolean testRequired) {
		this.testRequired = testRequired;
	}
	
	@Override
	public String toString() {
		return "Rank [rank="
				+ rankName 
				+ ", requiredTime=" + requiredTime
				+ ", nextRank=" + nextRank
				+ ", isTestRequired=" + testRequired
				+ "]";
	}
	
	
}
