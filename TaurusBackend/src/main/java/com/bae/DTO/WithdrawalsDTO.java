package com.bae.DTO;

import java.time.LocalDateTime;

public class WithdrawalsDTO {

	private String streetName;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;
	private Long bankCardNumber;

	public WithdrawalsDTO() {
		super();
	}

	public WithdrawalsDTO(String streetName, double latitude, double longitude, LocalDateTime timestamp,
			Long bankCardNumber) {
		super();
		this.streetName = streetName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.bankCardNumber = bankCardNumber;
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

	public Long getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(Long bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	@Override
	public String toString() {
		return "WithdrawalsDTO [streetName=" + streetName + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timestamp=" + timestamp + ", bankCardNumber=" + bankCardNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankCardNumber == null) ? 0 : bankCardNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		WithdrawalsDTO other = (WithdrawalsDTO) obj;
		if (bankCardNumber == null) {
			if (other.bankCardNumber != null)
				return false;
		} else if (!bankCardNumber.equals(other.bankCardNumber))
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
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
