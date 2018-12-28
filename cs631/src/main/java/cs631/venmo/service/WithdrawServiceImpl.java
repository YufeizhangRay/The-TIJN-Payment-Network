package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.WithdrawDao;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.WithdrawRecord;

@Service("withdrawService")
public class WithdrawServiceImpl extends BaseServiceImpl<WithdrawRecord> implements WithdrawService{

	@Autowired
	private WithdrawDao withdrawDao;
	
	@Override
	public List<WithdrawRecord> getAllRecord() {
		return withdrawDao.getAllRecord();
	}

	@Override
	public List<WithdrawRecord> getAllRecordByUserName(String username) {
		return withdrawDao.getAllRecordByUserName(username);
	}

	@Override
	public Pager<WithdrawRecord> getPageRecordByUserName(String username) {
		return withdrawDao.getPageRecordByUserName(username);
	}

	@Override
	public WithdrawRecord insertRecord(WithdrawRecord withdrawRecord, User loginUser, Double value) {
		return withdrawDao.insertRecord(withdrawRecord, loginUser, value);
	}

	@Override
	public Double loadTotalByUsername(String username) {
		return withdrawDao.loadTotalByUsername(username);
	}

	@Override
	public void clear() {
		withdrawDao.clear();
	}
	
	

}
