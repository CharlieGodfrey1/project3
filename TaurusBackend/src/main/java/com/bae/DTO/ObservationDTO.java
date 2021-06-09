package com.bae.DTO;

import java.time.LocalDateTime;

public class ObservationDTO {

	private String vehicleRegistrationNumber;
	private String streetName;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;

	public ObservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObservationDTO(String streetName, double latitude, double longitude, LocalDateTime timestamp,
			String vehReg) {
		super();
		this.streetName = streetName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.vehicleRegistrationNumber = vehReg;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((vehicleRegistrationNumber == null) ? 0 : vehicleRegistrationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObservationDTO other = (ObservationDTO) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (vehicleRegistrationNumber == null) {
			if (other.vehicleRegistrationNumber != null)
				return false;
		} else if (!vehicleRegistrationNumber.equals(other.vehicleRegistrationNumber))
			return false;
		return true;
	}

}
