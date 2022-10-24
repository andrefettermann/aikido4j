package br.com.afett.aikido4j.entities;

public class Dojo {

	private String name;
	private Address address;
	private String sensei;

	public final String getName() {
		return this.name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final Address getAddress() {
		return this.address;
	}

	public final void setAddress(final Address address) {
		this.address = address;
	}

	public final String getSensei() {
		return this.sensei;
	}

	public final void setSensei(final String sensei) {
		this.sensei = sensei;
	}

}
