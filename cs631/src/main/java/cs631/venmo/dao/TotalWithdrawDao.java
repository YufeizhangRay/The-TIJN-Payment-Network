package cs631.venmo.dao;

import cs631.venmo.pojo.TotalWithdraw;
import cs631.venmo.pojo.User;

public interface TotalWithdrawDao extends BaseDao<TotalWithdraw>{

	public TotalWithdraw loadByUsername(String username);
	
	public TotalWithdraw insertRecord(TotalWithdraw withdrawRecord, User loginUser, double value);
}
