package cs631.venmo.service;

import java.util.List;

import cs631.venmo.pojo.BankAccount;

public interface BankAccountService extends BaseService<BankAccount>{

	public List<BankAccount> getAllBankAccounts();
	
	public BankAccount loadBankAccountByAccountNumber(String accountNumber);
}
