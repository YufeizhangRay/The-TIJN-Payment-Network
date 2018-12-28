package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.User;

@Repository("sendRecordDao")
public class SendRecordDaoImpl extends BaseDaoImpl<SendRecord> implements SendRecordDao {

	private static long ONE_WEEK = 1000 * 60 * 60 * 24 * 7;
	private static long DELAY = 1000;
	
	@Override
	public List<SendRecord> getAllSendRecords() {
		String hql = "from SendRecord s order by s.time desc";
		return super.list(hql, null, null);
	}

	@Override
	public Pager<SendRecord> getPageSendByUserName(String username) {
		String hql = "select s from SendRecord s where s.username=? and s.state in (0,1) order by s.time desc";
		return super.find(hql, new Object[] { username }, null);
	}

	@Override
	public SendRecord insertRecord(User loginUser, SendRecord sendRecord, String info, String memo, Double value) {
		sendRecord.setUsername(loginUser.getUsername());
		sendRecord.setInfo(info);
		sendRecord.setAmount(value);
		sendRecord.setMemo(memo);
		sendRecord.setTime(new Timestamp(new Date().getTime()));
		return super.add(sendRecord);
	}

	@Override
	public Pager<SendRecord> getPageCancelByUserName(String username) {
		String hql = "select s from SendRecord s where s.username=? and s.state=2 order by s.time desc";
		return super.find(hql, new Object[] { username }, null);
	}

	@Override
	public SendRecord getSendRecordById(Integer id) {
		return super.load(id);
	}

	@Override
	public List<SendRecord> getAllUnProcessSendRecords() {
		String hql = "select s from SendRecord s where s.state=0 order by s.time desc";
		return super.list(hql, null, null);
	}

	@Override
	public Double getTotalSendByTime(String username, String begin, String end) {
		String hql = "select sum(s.amount) from SendRecord s where s.username = ? " + "and s.time between '" + begin
				+ "' and '" + end + "' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { username }, null);
	}

	@Override
	public Double getTotalRecevieByTime(String info, String begin, String end) {
		String hql = "select sum(s.amount) from SendRecord s where s.info = ? " + "and s.time between '" + begin
				+ "' and '" + end + "' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { info }, null);
	}

	@Override
	public Double getSendTotalInNov(String username) {
		String hql = "select sum(s.amount) from SendRecord s where s.username = ? "
				+ "and s.time between '2018-11-01' and '2018-11-30' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { username }, null);
	}

	@Override
	public Double getSendTotalInDec(String username) {
		String hql = "select sum(s.amount) from SendRecord s where s.username = ? "
				+ "and s.time between '2018-12-01' and '2018-12-31' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { username }, null);
	}

	@Override
	public Double getRecevieTotalInNov(String info) {
		String hql = "select sum(s.amount) from SendRecord s where s.info = ? "
				+ "and s.time between '2018-11-01' and '2018-11-30' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { info }, null);
	}

	@Override
	public Double getRecevieTotalInDec(String info) {
		String hql = "select sum(s.amount) from SendRecord s where s.info = ? "
				+ "and s.time between '2018-12-01' and '2018-12-31' and s.state = 1";
		return (Double) super.queryByHql(hql, new Object[] { info }, null);
	}

	@Override
	public Double loadTotalCancelByUserName(String username) {
		String hql = "select sum(s.amount) from SendRecord s where s.username=? "
				+ "and s.state=2 "
				+ "and s.time between :startTime and :endTime";
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", new Timestamp(new Date().getTime()-ONE_WEEK));
		map.put("endTime", new Timestamp(new Date().getTime()+DELAY));
		return (Double) super.queryByHql(hql, new Object[] {username}, map);
	}

}
