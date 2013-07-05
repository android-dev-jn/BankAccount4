package com.dis.bankaccount4;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import com.dis.bankaccount4.business.service.BankAccount;
import com.dis.bankaccount4.data.dao.BankAcountDAO;
import com.dis.bankaccount4.data.dao.TransactionDAO;
import com.dis.bankaccount4.data.entity.BankAccountDTO;
import com.dis.bankaccount4.data.entity.TransactionDTO;

public class BankAccountTest extends TestCase {

	private String accountNumber = "1234567890";
	private BankAcountDAO mockBankAccountDAO = mock(BankAcountDAO.class);
	private TransactionDAO mockTransactionDAO = mock(TransactionDAO.class);
	private Calendar mockCalendar = mock(Calendar.class);

	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reset(mockBankAccountDAO);
		reset(mockCalendar);
		reset(mockTransactionDAO);
		BankAccount.bankAccountDAO = this.mockBankAccountDAO;
		BankAccount.transactionDAO = this.mockTransactionDAO;
		BankAccount.calendar = this.mockCalendar;
	}

	// 1
	public void testOpenAccount() {
		BankAccountDTO bankAccountDTO = BankAccount.openAccount(accountNumber);
		ArgumentCaptor<BankAccountDTO> argumentCaptor = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDAO, times(1)).save(argumentCaptor.capture());

		assertEquals(accountNumber, argumentCaptor.getValue()
				.getAccountNumber());
		assertTrue(0 == argumentCaptor.getValue().getBalance());
	}

	// 2
	public void testGetAccountByAccountNumber() {
		ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor
				.forClass(String.class);
		BankAccountDTO bankAccountDTO = BankAccount.getAccount(accountNumber);
		verify(mockBankAccountDAO, times(1)).getAccount(
				accountNumberCaptor.capture());

		assertEquals(accountNumber, accountNumberCaptor.getValue());
	}

	// 3
	public void testDeposit() {
		double amount = 100, DELTA = 1e-2;
		String description = "deposit 100";

		BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber, 50);
		when(mockBankAccountDAO.getAccount(bankAccountDTO.getAccountNumber()))
				.thenReturn(bankAccountDTO);
		BankAccount.deposit(accountNumber, amount, description);

		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDAO, times(1)).save(argument.capture());
		assertEquals(150, argument.getValue().getBalance(), DELTA);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

	// 4
	public void testTimeStampDeposit() {
		double amount = 100;
		long timestamp = 1000;
		String description = "deposit 100";

		BankAccountDTO bankAccount = new BankAccountDTO(accountNumber, 50);
		when(mockBankAccountDAO.getAccount(bankAccount.getAccountNumber()))
				.thenReturn(bankAccount);
		when(mockCalendar.getTimeInMillis()).thenReturn(timestamp);
		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				timestamp, amount, description);

		BankAccount.deposit(accountNumber, amount, description);
		ArgumentCaptor<TransactionDTO> argumentCaptor = ArgumentCaptor
				.forClass(TransactionDTO.class);

		verify(mockTransactionDAO).createTransaction(argumentCaptor.capture());
		assertEquals(timestamp, argumentCaptor.getValue().getTimestamp());
	}

}
