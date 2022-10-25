package br.com.afett.aikido4j.entities;

public class Rank {

	private String id;
	private String name;
	private Long requiredTime;
	private boolean testRequired;
	private String nextRankName;
	
	public Rank(String name, Long requiredTime, boolean testRequired) {
		this.name = name;
		this.requiredTime = requiredTime;
		this.testRequired = testRequired;
	}

	public final String getId() {
		return this.id;
	}
	
	public final void setId(String id) {
		this.id = id;
	}
	
	public final String getName() {
		return this.name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final Long getRequiredTime() {
		return this.requiredTime;
	}

	public final void setRequiredTime(final Long requiredTime) {
		this.requiredTime = requiredTime;
	}
	
	public final String getNextRankName() {
		return this.nextRankName;
	}

	public final void setNextRankName(final String nextRankName) {
		this.nextRankName = nextRankName;
	}
	
	public final boolean isTestRequired() {
		return this.testRequired;
	}
	
	public final void setTestRequired(final boolean testRequired) {
		this.testRequired = testRequired;
	}
	
	@Override
	public String toString() {
		return "Rank [rank="
				+ name 
				+ ", requiredTime=" + requiredTime
				+ ", nextRank=" + nextRankName
				+ ", isTestRequired=" + testRequired
				+ "]";
	}
}
