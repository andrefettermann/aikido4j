package br.com.afett.aikido4j.entities;

public class Address {

	private String streetAddress;
	private String number;
	private String neighborhood;
	private String city;
	private String state;
	private String country;

	public final String getStreetAddress() {
		return this.streetAddress;
	}

	public final void setStreetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public final String getNumber() {
		return this.number;
	}

	public final void setNumber(final String number) {
		this.number = number;
	}

	public final String getNeighborhood() {
		return this.neighborhood;
	}

	public final void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public final String getCity() {
		return this.city;
	}

	public final void setCity(final String city) {
		this.city = city;
	}

	public final String getState() {
		return this.state;
	}

	public final void setState(final String state) {
		this.state = state;
	}

	public final String getCountry() {
		return this.country;
	}

	public final void setCountry(final String country) {
		this.country = country;
	}

}
