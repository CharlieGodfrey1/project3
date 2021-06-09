package com.bae.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atmTransaction")
public class AtmTransaction {

	@Id
	private Long id;
	private LocalDateTime timestamp;
	private Long atmId;
	private Long bankCardNumber;
	private String type;
	private double amount;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "atmId", insertable = false, updatable = false)
	private AtmPoint atm;

	public AtmTransaction() {
		super();
	}

	public AtmTransaction(Long id, LocalDateTime timestamp, Long atmId, Long bankCardNumber, String type,
			double amount) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.atmId = atmId;
		this.bankCardNumber = bankCardNumber;
		this.type = type;
		this.amount = amount;
	}

	public AtmTransaction(Long id, LocalDateTime timestamp, Long atmId, Long bankCardNumber, String type, double amount,
			AtmPoint atm) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.atmId = atmId;
		this.bankCardNumber = bankCardNumber;
		this.type = type;
		this.amount = amount;
		this.atm = atm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Long getAtmId() {
		return atmId;
	}

	public void setAtmId(Long atmId) {
		this.atmId = atmId;
	}

	public Long getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(Long bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public AtmPoint getAtm() {
		return atm;
	}

	public void setAtm(AtmPoint atm) {
		this.atm = atm;
	}

	@Override
	public String toString() {
		return "AtmTransaction [id=" + id + ", timestamp=" + timestamp + ", atmId=" + atmId + ", bankCardNumber="
				+ bankCardNumber + ", type=" + type + ", amount=" + amount + ", atm=" + atm + "]";
	}

}
