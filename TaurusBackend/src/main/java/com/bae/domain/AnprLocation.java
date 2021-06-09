package com.bae.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "anprCamera")
public class AnprLocation {

	@Id
	private Long anprId;
	private String streetName;
	private double latitude;
	private double longitude;

	@OneToMany(mappedBy = "anprPointId", targetEntity = Observations.class)
	private Set<Observations> observations;

	public AnprLocation() {
		super();
	}

	public AnprLocation(Long anprId, String streetName, double latitude, double longitude) {
		super();
		this.anprId = anprId;
		this.streetName = streetName;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getAnprId() {
		return anprId;
	}

	public void setAnprId(Long anprId) {
		this.anprId = anprId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public Set<Observations> getObservations() {
		return observations;
	}

	public void setObservations(Set<Observations> observations) {
		this.observations = observations;
	}

}
