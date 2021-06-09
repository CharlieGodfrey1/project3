package com.bae.DTO;

import java.util.HashSet;
import java.util.Set;

public class FrontendDTO {

	private Set<LocationDTO> vehicle = new HashSet<>();
	private Set<PersonDTO> transaction = new HashSet<>();

	public FrontendDTO() {
		super();
	}

	public FrontendDTO(Set<LocationDTO> vehicle, Set<PersonDTO> transaction) {
		super();
		this.vehicle = vehicle;
		this.transaction = transaction;
	}

	public Set<LocationDTO> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<LocationDTO> vehicle) {
		this.vehicle = vehicle;
	}

	public Set<PersonDTO> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<PersonDTO> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "FrontendDTO [vehicle=" + vehicle + ", transaction=" + transaction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		FrontendDTO other = (FrontendDTO) obj;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

}
