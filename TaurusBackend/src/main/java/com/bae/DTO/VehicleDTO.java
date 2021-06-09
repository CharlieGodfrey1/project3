package com.bae.DTO;

public class VehicleDTO {

	private String vehicleRegistrationNumber;
	private String make;
	private String model;
	private String colour;

	public VehicleDTO() {
		super();
	}

	public VehicleDTO(String vehicleRegistrationNumber, String make, String model, String colour) {
		super();
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.make = make;
		this.model = model;
		this.colour = colour;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		VehicleDTO other = (VehicleDTO) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (vehicleRegistrationNumber == null) {
			if (other.vehicleRegistrationNumber != null)
				return false;
		} else if (!vehicleRegistrationNumber.equals(other.vehicleRegistrationNumber))
			return false;
		return true;
	}

}
