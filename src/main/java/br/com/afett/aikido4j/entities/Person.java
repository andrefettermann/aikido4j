package br.com.afett.aikido4j.entities;

import java.util.List;

public class Person {

	private String name;
	private Rank rank;
	private List<PersonType> personTypes;
	
	public Person(String name, Rank rank, List<PersonType> personTypes) {
		this.name = name;
		this.rank = rank;
		this.personTypes = personTypes;
	}

	public final String getName() {
		return this.name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final Rank getRank() {
		return this.rank;
	}

	public final void setRank(final Rank rank) {
		this.rank = rank;
	}

	public List<PersonType> getPersonTypes() {
		return this.personTypes;
	}

	public void setPersonTypes(final List<PersonType> personTypes) {
		this.personTypes = personTypes;
	}
	
	public void addPersonType(final PersonType personType) {
		this.personTypes.add(personType);
	}

}
