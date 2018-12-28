package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.RequestRecord;
import cs631.venmo.pojo.User;

@Repository("requestRecordDao")
public class RequestRecordDaoImpl extends BaseDaoImpl<RequestRecord> implements RequestRecordDao {

	@Override
	public List<RequestRecord> getAllRequestRecords() {
		String hql = "from RequestRecord r order by r.time desc";
		return super.list(hql, null, null);
	}

	@Override
	public List<RequestRecord> getRequestRecordByTime() {
		return null;
	}

	@Override
	public Pager<RequestRecord> getPageRequestByUserName(String username) {
		String hql = "select r from RequestRecord r where r.username=? and r.state in (0,1) order by r.time desc";
		return super.find(hql, new Object[] { username }, null);
	}

	@Override
	public RequestRecord getRequestRecordById(Integer id) {
		return super.load(id);
	}

	@Override
	public Pager<RequestRecord> getPageRefuseByUserName(String username) {
		String hql = "select r from RequestRecord r where r.username=? and r.state = 2 order by r.time desc";
		return super.find(hql, new Object[] { username }, null);
	}

	@Override
	public RequestRecord insertRecord(User loginUser, RequestRecord requestRecord, String info, String memo,
			Double value) {
		requestRecord.setUsername(loginUser.getUsername());
		requestRecord.setInfo(info);
		requestRecord.setAmount(value);
		requestRecord.setMemo(memo);
		requestRecord.setTime(new Timestamp(new Date().getTime()));
		requestRecord.setState(0);
		return super.add(requestRecord);
	}

	@Override
	public Pager<RequestRecord> getPageRequestByInfo(String info) {
		String hql = "select r from RequestRecord r where r.info=? and r.state = 0 order by r.time desc";
		return super.find(hql, new Object[] { info }, null);
	}

}
