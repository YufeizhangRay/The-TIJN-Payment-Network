package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.BankDao;
import cs631.venmo.pojo.Bank;

@Service("bankService")
public class BankServiceImpl extends BaseServiceImpl<Bank> implements BankService{

	@Autowired
	private BankDao bankDao;

	@Override
	public List<Bank> getAllBank() {
		return bankDao.getAllBank();
	}
}
