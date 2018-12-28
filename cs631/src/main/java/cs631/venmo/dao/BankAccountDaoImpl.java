package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.BankAccount;

@Repository("bankAccountDao")
public class BankAccountDaoImpl extends BaseDaoImpl<BankAccount>implements BankAccountDao{

	@Override
	public List<BankAccount> getAllBankAccounts() {
		String hql = "from BankAccount";
		return super.list(hql, null, null);
	}

	@Override
	public BankAccount loadBankAccountByAccountNumber(String accountNumber) {
		String hql = "select ba from BankAccount ba where ba.bankAccount=?";
		return (BankAccount) super.queryByHql(hql, new Object[] {accountNumber}, null);
	}

}
