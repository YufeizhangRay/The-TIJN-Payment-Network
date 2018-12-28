package cs631.venmo.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;

@Repository("totalCancelDao")
public class TotalCancelDaoImpl extends BaseDaoImpl<TotalCancel> implements TotalCancelDao{

	@Override
	public TotalCancel loadUserByUsername(String username) {
		String hql = "select t from TotalCancel t where t.username=?";
		return (TotalCancel) super.queryByHql(hql, new Object[] {username}, null);
	}

	@Override
	public TotalCancel insertRecord(TotalCancel totalCancel, User loginUser, double value) {
		totalCancel.setUsername(loginUser.getUsername());
		totalCancel.setTotal(value);
		totalCancel.setFristTotalTime(new Timestamp(new Date().getTime()));
		return super.add(totalCancel);
	}

}
