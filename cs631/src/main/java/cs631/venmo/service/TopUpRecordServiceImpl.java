package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.TopUpRecordDao;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TopUpRecord;
import cs631.venmo.pojo.User;

@Service("topUpRecordService")
public class TopUpRecordServiceImpl extends BaseServiceImpl<TopUpRecord> implements TopUpRecordService{

	@Autowired
	private TopUpRecordDao topUpRecordDao;
	
	@Override
	public List<TopUpRecord> getAllRecord() {
		return topUpRecordDao.getAllRecord();
	}

	@Override
	public List<TopUpRecord> getAllRecordByUserName(String username) {
		return topUpRecordDao.getAllRecordByUserName(username);
	}

	@Override
	public TopUpRecord insertRecord(TopUpRecord topUpRecord,User loginUser,double value) {
		return  topUpRecordDao.insertRecord(topUpRecord,loginUser,value);
	}

	@Override
	public Pager<TopUpRecord> getPageRecordByUserName(String username) {
		return topUpRecordDao.getPageRecordByUserName(username);
	}

}
