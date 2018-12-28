package cs631.venmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.TotalCancelDao;
import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;

@Service("totalCancelService")
public class TotalCancelServiceImpl extends BaseServiceImpl<TotalCancel> implements TotalCancelService{

	@Autowired
	private TotalCancelDao totalCancelDao;
	
	@Override
	public TotalCancel loadUserByUsername(String username) {
		return totalCancelDao.loadUserByUsername(username);
	}

	@Override
	public TotalCancel insertRecord(TotalCancel totalCancel, User loginUser, double value) {
		return totalCancelDao.insertRecord(totalCancel, loginUser, value);
	}

}
