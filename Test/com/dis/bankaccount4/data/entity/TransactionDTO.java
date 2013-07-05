package com.dis.bankaccount4.data.entity;

public class TransactionDTO {

	private String accountNumber;
	private long timestamp;
	private double amount;
	private String description;

	public TransactionDTO(String accountNumber, long timestamp, double amount,
			String description) {
		this.accountNumber = accountNumber;
		this.timestamp = timestamp;
		this.amount = amount;
		this.description = description;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
