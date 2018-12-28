package cs631.venmo.service;

import java.util.List;

import cs631.venmo.pojo.Bank;

public interface BankService extends BaseService<Bank>{

	public List<Bank> getAllBank();
}
