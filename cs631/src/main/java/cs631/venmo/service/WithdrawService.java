package cs631.venmo.service;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.WithdrawRecord;

public interface WithdrawService extends BaseService<WithdrawRecord>{

	public List<WithdrawRecord> getAllRecord();

	public List<WithdrawRecord> getAllRecordByUserName(String username);
	
	public Double loadTotalByUsername(String username);
	
	public Pager<WithdrawRecord> getPageRecordByUserName(String username);

	public WithdrawRecord insertRecord(WithdrawRecord withdrawRecord, User loginUser, Double value);
	
	public void clear();
}
