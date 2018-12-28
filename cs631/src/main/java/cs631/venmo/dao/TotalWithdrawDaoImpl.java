package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.TotalWithdraw;
import cs631.venmo.pojo.User;

@Repository("totalWithdrawDao")
public class TotalWithdrawDaoImpl extends BaseDaoImpl<TotalWithdraw> implements TotalWithdrawDao{

	@Override
	public TotalWithdraw loadByUsername(String username) {
		String hql = "select t from TotalWithdraw t where t.username=?";
		return (TotalWithdraw) super.queryByHql(hql, new Object[] {username}, null);
	}

	@Override
	public TotalWithdraw insertRecord(TotalWithdraw withdrawRecord, User loginUser, double value) {
		withdrawRecord.setUsername(loginUser.getUsername());
		withdrawRecord.setTotal(value);
		withdrawRecord.setFristWithdrawTime(new Timestamp(new Date().getTime()));
		return super.add(withdrawRecord);
	}

}
