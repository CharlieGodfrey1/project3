package com.bae.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eposTerminal")
public class EposTerminal {

	@Id
	private Long id;
	private String vendor;
	private String streetName;
	private String postcode;
	private double latitude;
	private double longitude;

	@OneToMany(mappedBy = "eposTerminal", fetch = FetchType.EAGER, targetEntity = EposTransaction.class)
	private Set<EposTransaction> transactions;

	public EposTerminal() {
		super();
	}

	public EposTerminal(Long id, String vendor, String streetName, String postcode, double latitude, double longitude) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.streetName = streetName;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Set<EposTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<EposTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "EposTerminal [id=" + id + ", vendor=" + vendor + ", streetName=" + streetName + ", postcode=" + postcode
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", transactions=" + "]";
	}

}
