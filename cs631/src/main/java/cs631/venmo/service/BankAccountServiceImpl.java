package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.BankAccountDao;
import cs631.venmo.pojo.BankAccount;

@Service("bankAccountService")
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount> implements BankAccountService{

	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Override
	public List<BankAccount> getAllBankAccounts() {
		return bankAccountDao.getAllBankAccounts();
	}

	@Override
	public BankAccount loadBankAccountByAccountNumber(String accountNumber) {
		return bankAccountDao.loadBankAccountByAccountNumber(accountNumber);
	}

}
