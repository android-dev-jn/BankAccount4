package com.dis.bankaccount4.business.service;

import com.dis.bankaccount4.data.dao.BankAcountDAO;
import com.dis.bankaccount4.data.entity.BankAccountDTO;

public class BankAccount {

	public static BankAcountDAO bankAccountDAO;

	public static BankAccountDTO openAccount(String accountNumber) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
		bankAccountDAO.save(bankAccountDTO);
		return bankAccountDTO;
	}

	public static BankAccountDTO getAccount(String accountNumber) {
		return bankAccountDAO.getAccount(accountNumber);
	}

	public static void deposit(String accountNumber, double amount,
			String description) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber, amount);
		bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
		bankAccountDAO.save(bankAccountDTO);
	}

}
