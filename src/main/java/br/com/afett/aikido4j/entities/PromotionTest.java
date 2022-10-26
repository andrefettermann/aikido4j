package br.com.afett.aikido4j.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionTest {

	private String id;
	private String name;
	private String venue;
	private Address address;
	private List<Person> sensei = new ArrayList<>();
	private LocalDate date;
	private String remarks;
	private List<Rank> ranks = new ArrayList<>();

	public PromotionTest(String name) {
		this.name = name;
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

	public final String getVenue() {
		return this.venue;
	}

	public final void setVenue(final String venue) {
		this.venue = venue;
	}

	public final Address getAddress() {
		return this.address;
	}

	public final void setAddress(final Address address) {
		this.address = address;
	}

	public final List<Person> getSensei() {
		return this.sensei;
	}

	public final void setSensei(final List<Person> sensei) {
		this.sensei = sensei;
	}

	public final void addSensei(final Person sensei) {
		this.sensei.add(sensei);
	}

	public final LocalDate getDate() {
		return this.date;
	}

	public final void setDate(final LocalDate date) {
		this.date = date;
	}

	public final String getRemarks() {
		return this.remarks;
	}

	public final void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	public final List<Rank> getRanks() {
		return this.ranks;
	}

	public final void setRanks(final List<Rank> ranks) {
		this.ranks = ranks;
	}

	public final void addRank(final Rank rank) {
		this.ranks.add(rank);
	}

	@Override
	public String toString() {
		return "PromotionTest [id=" + id 
				+ ", name=" + name 
				+ ", venue=" + venue 
				+ ", address=" + address 
				+ ", sensei=" + sensei 
				+ ", date=" + date 
				+ ", remarks=" + remarks 
				+ "]";
	}

}
