package cs631.venmo.service;

import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;

public interface TotalCancelService extends BaseService<TotalCancel>{

	public TotalCancel loadUserByUsername(String username);

	public TotalCancel insertRecord(TotalCancel totalCancel, User loginUser, double value);
}
