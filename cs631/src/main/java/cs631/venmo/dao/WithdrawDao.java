package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.WithdrawRecord;

public interface WithdrawDao extends BaseDao<WithdrawRecord> {

	public List<WithdrawRecord> getAllRecord();

	public List<WithdrawRecord> getAllRecordByUserName(String username);
	
	public Double loadTotalByUsername(String username);
	
	public Pager<WithdrawRecord> getPageRecordByUserName(String username);

	public WithdrawRecord insertRecord(WithdrawRecord withdrawRecord, User loginUser, double value);
	
	public void clear();
}
