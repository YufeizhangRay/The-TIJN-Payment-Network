package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Bank;

@Repository("bankDao")
public class BankDaoImpl extends BaseDaoImpl<Bank> implements BankDao {

	@Override
	public List<Bank> getAllBank() {
		String hql = "from User";
		return super.list(hql, null, null);
	}

}
