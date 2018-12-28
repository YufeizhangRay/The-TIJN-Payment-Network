package cs631.venmo.dao;

import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;

public interface TotalCancelDao extends BaseDao<TotalCancel> {

	public TotalCancel loadUserByUsername(String username);

	public TotalCancel insertRecord(TotalCancel totalCancel, User loginUser, double value);

}
