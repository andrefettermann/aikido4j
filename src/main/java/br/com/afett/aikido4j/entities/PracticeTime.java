package br.com.afett.aikido4j.entities;

import java.time.LocalDate;

public class PracticeTime {

	private LocalDate classDate;
	private String rankId;
	private Long classDuration;

	public PracticeTime(LocalDate classDate, String rankId, Long classDuration) {
		this.classDate = classDate;
		this.rankId = rankId;
		this.classDuration = classDuration;
	}

	public final LocalDate getClassDate() {
		return this.classDate;
	}

	public final void setClassdate(final LocalDate classDate) {
		this.classDate = classDate;
	}

	public final String getRankId() {
		return this.rankId;
	}

	public final void setRankId(final String rankId) {
		this.rankId = rankId;
	}

	public Long getClassDuration() {
		return this.classDuration;
	}

	public void setClassDuration(final Long classDuration) {
		this.classDuration = classDuration;
	}

	@Override
	public String toString() {
		return "PracticeTime [classDate=" + classDate 
				+ ", rankId=" + rankId 
				+ ", classDuration=" + classDuration 
				+ "]";
	}

}
