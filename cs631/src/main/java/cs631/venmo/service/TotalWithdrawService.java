package cs631.venmo.service;

import cs631.venmo.pojo.TotalWithdraw;

public interface TotalWithdrawService extends BaseService<TotalWithdraw>{

	public TotalWithdraw loadByUsername(String username);
}
