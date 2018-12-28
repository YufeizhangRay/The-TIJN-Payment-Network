package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.BankAccount;

public interface BankAccountDao extends BaseDao<BankAccount>{
	
	public List<BankAccount> getAllBankAccounts();
	
	public BankAccount loadBankAccountByAccountNumber(String accountNumber);
}
