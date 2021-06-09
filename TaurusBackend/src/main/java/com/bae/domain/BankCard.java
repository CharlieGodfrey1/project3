package com.bae.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bankCard")
public class BankCard {

	@Id
	private Long bankCardId;
	private Long cardNumber;
	private String sortCode;
	private Long bankAccountId;
	private Long accountNumber;
	private String bank;

	@OneToMany(mappedBy = "bankCardNumber", targetEntity = EposTransaction.class)
	private Set<EposTransaction> transactions;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "bankAccountId", referencedColumnName = "bankAccountId", insertable = false, updatable = false)
	private AccountHolder person;

	public BankCard() {
		super();
	}

	public BankCard(Long cardId, Long cardNumber, String sortCode, Long accountId, Long accountNumber, String bank) {
		super();
		this.bankCardId = cardId;
		this.cardNumber = cardNumber;
		this.sortCode = sortCode;
		this.bankAccountId = accountId;
		this.accountNumber = accountNumber;
		this.bank = bank;
	}

	public BankCard(Long cardId, Long cardNumber, String sortCode, Long accountId, Long accountNumber, String bank,
			Set<EposTransaction> transactions, AccountHolder person) {
		super();
		this.bankCardId = cardId;
		this.cardNumber = cardNumber;
		this.sortCode = sortCode;
		this.bankAccountId = accountId;
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.transactions = transactions;
		this.person = person;
	}

	public Long getCardId() {
		return bankCardId;
	}

	public void setCardId(Long cardId) {
		this.bankCardId = cardId;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
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

	public Set<EposTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<EposTransaction> transactions) {
		this.transactions = transactions;
	}

	public Long getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(Long bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public AccountHolder getPerson() {
		return person;
	}

	public void setPerson(AccountHolder person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "BankCard [bankCardId=" + bankCardId + ", cardNumber=" + cardNumber + ", sortCode=" + sortCode
				+ ", bankAccountId=" + bankAccountId + ", accountNumber=" + accountNumber + ", bank=" + bank
				+ ", transactions=" + transactions + ", person=" + person + "]";
	}

}
