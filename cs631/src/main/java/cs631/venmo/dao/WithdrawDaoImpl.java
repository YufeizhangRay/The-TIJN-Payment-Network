package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TotalWithdraw;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.WithdrawRecord;

@Repository("withdrawDao")
public class WithdrawDaoImpl extends BaseDaoImpl<WithdrawRecord> implements WithdrawDao{

	private static long ONE_WEEK = 1000 * 60 * 60 * 24 * 7;
	private static long DELAY = 1000;
	
	@Override
	public List<WithdrawRecord> getAllRecord() {
		String hql = "from WithdrawRecord w order by w.withdrawTime desc";
		return super.list(hql, null, null);
	}

	@Override
	public List<WithdrawRecord> getAllRecordByUserName(String username) {
		String hql = "select w from WithdrawRecord w where w.username=? order by w.withdrawTime desc";
		return super.list(hql, new Object[] {username}, null);
	}

	@Override
	public Pager<WithdrawRecord> getPageRecordByUserName(String username) {
		String hql = "select w from WithdrawRecord w where w.username=? order by w.withdrawTime desc";
		return super.find(hql, new Object[] {username}, null);
	}

	@Override
	public WithdrawRecord insertRecord(WithdrawRecord withdrawRecord, User loginUser, double value) {
		withdrawRecord.setUsername(loginUser.getName());
		withdrawRecord.setAmount(value);
		withdrawRecord.setAccount(loginUser.getPrimaryAccount().getBankAccount());
		withdrawRecord.setBankName(loginUser.getPrimaryAccount().getBank().getBankName());
		withdrawRecord.setWithdrawTime(new Timestamp(new Date().getTime()));
		return super.add(withdrawRecord);
	}

	@Override
	public Double loadTotalByUsername(String username) {
		String hql = "select sum(w.amount) from WithdrawRecord w where w.username=? "
				+ "and w.withdrawTime between :startTime and :endTime";
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", new Timestamp(new Date().getTime()-ONE_WEEK));
		map.put("endTime", new Timestamp(new Date().getTime()+DELAY));
		return (Double) super.queryByHql(hql, new Object[] {username}, map);
	}

	@Override
	public void clear() {
		getSession().clear();
	}

}
