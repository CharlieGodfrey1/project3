package com.bae.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atmpoint")
public class AtmPoint {

	@Id
	private Long atmId;
	private String operator;
	private String streetName;
	private String postcode;
	private double latitude;
	private double longitude;

	@OneToMany(mappedBy = "atm", targetEntity = AtmTransaction.class)
	private Set<AtmTransaction> withdrawals;

	public AtmPoint() {
		super();
	}

	public AtmPoint(Long id, String operator, String streetName, String postcode, double latitude, double longitude) {
		super();
		this.atmId = id;
		this.operator = operator;
		this.streetName = streetName;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return atmId;
	}

	public void setId(Long id) {
		this.atmId = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	@Override
	public String toString() {
		return "AtmPoint [atmId=" + atmId + ", operator=" + operator + ", streetName=" + streetName + ", postcode="
				+ postcode + ", latitude=" + latitude + ", longitude=" + longitude + ", withdrawals=" + withdrawals
				+ "]";
	}

}
