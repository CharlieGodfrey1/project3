package com.bae.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicleObservations")
//@IdClass(ObservationsID.class)
public class Observations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private Long anprPointId;

	private LocalDateTime timestamp;

	private String vehicleRegistrationNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "anprPointId", insertable = false, updatable = false)
	private AnprLocation anprLocal;

	public Observations() {
		super();
	}

	public Observations(Long anprPointId, LocalDateTime timestamp, String vehicleRegistrationNumber, Long Id) {
		super();
		this.anprPointId = anprPointId;
		this.timestamp = timestamp;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.Id = Id;
	}

	public Observations(Long anprPointId, LocalDateTime timestamp, String vehicleRegistrationNumber,
			AnprLocation anprLocal) {
		super();
		this.Id = anprPointId;
		this.timestamp = timestamp;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.anprLocal = anprLocal;
	}

	public Long getAnprPointId() {
		return Id;
	}

	public void setAnprPointId(Long anprPointId) {
		this.Id = anprPointId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public AnprLocation getAnprLocal() {
		return anprLocal;
	}

	public void setAnprLocal(AnprLocation anprLocal) {
		this.anprLocal = anprLocal;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

//	@Override
//	public String toString() {
//		return "Observations [ID=" + ", anprPointId=" + anprPointId + ", timestamp=" + timestamp
//				+ ", vehicleRegistrationNumber=" + vehicleRegistrationNumber + "]";
//	}

}
