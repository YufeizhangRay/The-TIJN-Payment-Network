package cs631.venmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.TotalWithdrawDao;
import cs631.venmo.pojo.TotalWithdraw;

@Service("totalWithdrawService")
public class TotalWithdrawServiceImpl extends BaseServiceImpl<TotalWithdraw> implements TotalWithdrawService{

	@Autowired
	private TotalWithdrawDao totalWithdrawDao;
	
	@Override
	public TotalWithdraw loadByUsername(String username) {
		return totalWithdrawDao.loadByUsername(username);
	}

}
