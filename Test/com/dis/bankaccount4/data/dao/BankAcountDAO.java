package com.dis.bankaccount4.data.dao;

import com.dis.bankaccount4.data.entity.BankAccountDTO;

public class BankAcountDAO {

	public void save(BankAccountDTO bankAccountDTO) {

	}

	public BankAccountDTO getAccount(String accountNumber) {
		return new BankAccountDTO(accountNumber);
	}

}
