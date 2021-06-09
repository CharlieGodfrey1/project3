package com.bae.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@Column(name = "vehicleRegistrationNo")
	private String vehicleRegistrationNumber;
	private String make;
	private String model;
	private String colour;
	@OneToMany(mappedBy = "vehicleRegistrationNo", targetEntity = VehicleRegistration.class)
	private Set<VehicleRegistration> vehicleRegs;
	@OneToMany(mappedBy = "vehicleRegistrationNumber", targetEntity = Observations.class)
	private Set<Observations> observations;

	public Vehicle(String vehicleRegistrationNumber, String make, String model, String colour) {
		super();
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.make = make;
		this.model = model;
		this.colour = colour;
	}

	public Vehicle(String vehicleRegistrationNumber, String make, String model, String colour,
			Set<VehicleRegistration> vehicleRegs, Set<Observations> observations) {
		super();
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.vehicleRegs = vehicleRegs;
		this.observations = observations;
	}

	public Vehicle() {
		super();
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

	public Set<VehicleRegistration> getVehicleRegs() {
		return vehicleRegs;
	}

	public void setVehicleRegs(Set<VehicleRegistration> vehicleRegs) {
		this.vehicleRegs = vehicleRegs;
	}

	public Set<Observations> getObservations() {
		return observations;
	}

	public void setObservations(Set<Observations> observations) {
		this.observations = observations;
	}

}
