package com.bae.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@IdClass(EposTransactionsID.class)
@Table(name = "eposTransactions")
public class EposTransaction {

	@Id
	private Long id;
	private LocalDateTime timestamp;
	private Long eposId;
	private Long bankCardNumber;
	private Long payeeAccount;
	private double amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eposId", insertable = false, updatable = false)
	private EposTerminal eposTerminal;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "payeeAccount", insertable = false, updatable = false)
//	private AccountHolder person;

	public EposTransaction() {
		super();
	}

	public EposTransaction(LocalDateTime timestamp, Long eposId, Long bankCardNumber, Long payeeAccount,
			double amount) {
		super();
		this.timestamp = timestamp;
		this.eposId = eposId;
		this.bankCardNumber = bankCardNumber;
		this.payeeAccount = payeeAccount;
		this.amount = amount;
	}

	public EposTransaction(LocalDateTime timestamp, Long eposId, Long cardNumber, Long payeeAccount, double amount,
			EposTerminal eposTerminal) {
		super();
		this.timestamp = timestamp;
		this.eposId = eposId;
		this.bankCardNumber = cardNumber;
		this.payeeAccount = payeeAccount;
		this.amount = amount;
		this.eposTerminal = eposTerminal;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Long getEposId() {
		return eposId;
	}

	public void setEposId(Long eposId) {
		this.eposId = eposId;
	}

	public Long getCardNumber() {
		return bankCardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.bankCardNumber = cardNumber;
	}

	public Long getAccountNumber() {
		return payeeAccount;
	}

	public void setAccountNumber(Long accountNumber) {
		this.payeeAccount = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public EposTerminal getEposTerminal() {
		return eposTerminal;
	}

	public void setEposTerminal(EposTerminal eposTerminal) {
		this.eposTerminal = eposTerminal;
	}

	public Long getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(Long bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public Long getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(Long payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	@Override
	public String toString() {
		return "EposTransaction [id=" + id + ", timestamp=" + timestamp + ", eposId=" + eposId + ", bankCardNumber="
				+ bankCardNumber + ", payeeAccount=" + payeeAccount + ", amount=" + amount + ", eposTerminal="
				+ eposTerminal + "]";
	}

}
