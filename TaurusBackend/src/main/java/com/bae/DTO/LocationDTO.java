package com.bae.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocationDTO {

	private String streetName;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;
	private String forename;
	private String surname;
	private String address;
	private LocalDate dateOfBirth;
	private String vehicleRegNo;

//	
//	private VehicleRegistration vehicleProfile;

	public LocationDTO() {
		super();
	}

	public LocationDTO(String streetName, double latitude, double longitude, LocalDateTime timestamp, String forename,
			String surname, String address, LocalDate dateOfBirth, String vehicleRegNo) {
		super();
		this.streetName = streetName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.forename = forename;
		this.surname = surname;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.vehicleRegNo = vehicleRegNo;
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

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((vehicleRegNo == null) ? 0 : vehicleRegNo.hashCode());
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
		LocationDTO other = (LocationDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (vehicleRegNo == null) {
			if (other.vehicleRegNo != null)
				return false;
		} else if (!vehicleRegNo.equals(other.vehicleRegNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationDTO [streetName=" + streetName + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timestamp=" + timestamp + ", forename=" + forename + ", surname=" + surname + ", address="
				+ address + ", dateOfBirth=" + dateOfBirth + ", vehicleRegNo=" + vehicleRegNo + "]";
	}

}
