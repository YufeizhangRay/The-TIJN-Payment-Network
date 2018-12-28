package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Bank;

public interface BankDao extends BaseDao<Bank>{

	public List<Bank> getAllBank();
}
