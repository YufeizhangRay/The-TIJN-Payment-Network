package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TopUpRecord;
import cs631.venmo.pojo.User;

@Repository("topUpRecordDao")
public class TopUpRecordDaoImpl extends BaseDaoImpl<TopUpRecord> implements TopUpRecordDao{

	@Override
	public List<TopUpRecord> getAllRecord() {
		String hql = "from TopUpRecord t order by t.topUpTime desc";
		return super.list(hql, null, null);
	}

	@Override
	public List<TopUpRecord> getAllRecordByUserName(String username) {
		String hql = "select t from TopUpRecord t where t.username=? order by t.topUpTime desc";
		return super.list(hql, new Object[] {username}, null);
	}
	
	@Override
	public TopUpRecord insertRecord(TopUpRecord topUpRecord,User loginUser,double value) {
		topUpRecord.setUsername(loginUser.getName());
		topUpRecord.setAmount(value);
		topUpRecord.setAccount(loginUser.getPrimaryAccount().getBankAccount());
		topUpRecord.setBankName(loginUser.getPrimaryAccount().getBank().getBankName());
		topUpRecord.setTopUpTime(new Timestamp(new Date().getTime()));
		return super.add(topUpRecord);
	}

	@Override
	public Pager<TopUpRecord> getPageRecordByUserName(String username) {
		String hql = "select t from TopUpRecord t where t.username=? order by t.topUpTime desc";
		return super.find(hql, new Object[] {username}, null);
	}

}
