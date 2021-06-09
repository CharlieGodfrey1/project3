package com.bae.domain;

import java.io.Serializable;

public class ObservationsID implements Serializable {

	private Long anprPointId;

	private String vehicleRegistrationNumber;

	public ObservationsID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObservationsID(Long anprPointID, String vehicleRegistrationNumber) {
		this.anprPointId = anprPointID;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public Long getAnprPointId() {
		return anprPointId;
	}

	public void setAnprPointId(Long anprPointId) {
		this.anprPointId = anprPointId;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

}
