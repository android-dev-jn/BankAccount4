package com.dis.bankaccount4.data.entity;

public class BankAccountDTO {

	private String accountNumber;
	private double balance;

	public BankAccountDTO(String accountNumber2) {
		this.accountNumber = accountNumber2;
		this.balance = 0;
	}

	public BankAccountDTO(String accountNumber2, double newBalance) {
		this.accountNumber = accountNumber2;
		this.balance = newBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
