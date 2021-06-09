package com.bae.DTO;

import java.time.LocalDate;
import java.util.Set;

public class SuspectDTO {

	private String forenames;
	private String surname;
	private String address;
	private String driverLicenceId;
	private LocalDate dateOfBirth;
	private Set<VehicleDTO> vehicles;

	public SuspectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuspectDTO(String forenames, String surname, String address, String driverLicenceId, LocalDate dateOfBirth,
			Set<VehicleDTO> vehicles) {
		super();
		this.forenames = forenames;
		this.surname = surname;
		this.address = address;
		this.driverLicenceId = driverLicenceId;
		this.dateOfBirth = dateOfBirth;
		this.vehicles = vehicles;
	}

	public String getForenames() {
		return forenames;
	}

	public void setForenames(String forenames) {
		this.forenames = forenames;
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

	public String getDriverLicenceId() {
		return driverLicenceId;
	}

	public void setDriverLicenceId(String driverLicenceId) {
		this.driverLicenceId = driverLicenceId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<VehicleDTO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<VehicleDTO> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((driverLicenceId == null) ? 0 : driverLicenceId.hashCode());
		result = prime * result + ((forenames == null) ? 0 : forenames.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((vehicles == null) ? 0 : vehicles.hashCode());
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
		SuspectDTO other = (SuspectDTO) obj;
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
		if (driverLicenceId == null) {
			if (other.driverLicenceId != null)
				return false;
		} else if (!driverLicenceId.equals(other.driverLicenceId))
			return false;
		if (forenames == null) {
			if (other.forenames != null)
				return false;
		} else if (!forenames.equals(other.forenames))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (vehicles == null) {
			if (other.vehicles != null)
				return false;
		} else if (!vehicles.equals(other.vehicles))
			return false;
		return true;
	}

}
