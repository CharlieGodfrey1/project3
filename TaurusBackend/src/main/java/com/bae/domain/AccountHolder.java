package com.bae.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "peopleBankAccount")
public class AccountHolder {

	@Id
	private Long bankAccountId;
	private Long accountNumber;
	private String bank;
	private String forenames;
	private String surname;
	private String dateOfBirth;
	private String homeAddress;
	@OneToMany(mappedBy = "bankAccountId", targetEntity = BankCard.class)
	private Set<BankCard> cards;
//	@OneToMany(mappedBy = "payeeAccount", targetEntity = EposTransactions.class)
//	private Set<EposTransactions> transactions;

	public AccountHolder() {
		super();
	}

	public AccountHolder(Long accountId, Long accountNumber, String bank, String forenames, String surname,
			String dateOfBirth, String homeAddress) {
		super();
		this.bankAccountId = accountId;
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.forenames = forenames;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
	}

	public Long getAccountId() {
		return bankAccountId;
	}

	public void setAccountId(Long accountId) {
		this.bankAccountId = accountId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

}
