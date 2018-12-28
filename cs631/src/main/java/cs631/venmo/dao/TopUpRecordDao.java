package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TopUpRecord;
import cs631.venmo.pojo.User;

public interface TopUpRecordDao extends BaseDao<TopUpRecord>{

	public List<TopUpRecord> getAllRecord();

	public List<TopUpRecord> getAllRecordByUserName(String username);
	
	public Pager<TopUpRecord> getPageRecordByUserName(String username);

	public TopUpRecord insertRecord(TopUpRecord topUpRecord, User loginUser, double value);
}
