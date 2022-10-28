package br.com.afett.aikido4j.entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
	
	private List<PracticeTime> practiceTimes = new ArrayList<>();

	public Student(String name, Rank rank, List<PersonType> personTypes) {
		super(name, rank, personTypes);
	}
	
	public void addPracticeTime(PracticeTime practiceTime) {
		this.practiceTimes.add(practiceTime);
	}

	public List<PracticeTime> getPracticeTimes() {
		return this.practiceTimes;
	}
	
	@Override
	public String toString() {
		return "Student: id=" + getId()
				+ ", name=" + getName();
	}
}
