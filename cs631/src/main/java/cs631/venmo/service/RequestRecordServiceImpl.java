package cs631.venmo.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.RequestRecordDao;
import cs631.venmo.dao.SendRecordDao;
import cs631.venmo.dao.UserDao;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.RequestRecord;
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.User;

@Service("requestRecordService")
public class RequestRecordServiceImpl extends BaseServiceImpl<RequestRecord> implements RequestRecordService {

	@Autowired
	private RequestRecordDao requestRecordDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SendRecordDao sendRecordDao;

	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public List<RequestRecord> getAllRequestRecords() {
		return requestRecordDao.getAllRequestRecords();
	}

	@Override
	public List<RequestRecord> getRequestRecordByTime() {
		return requestRecordDao.getRequestRecordByTime();
	}

	@Override
	public Pager<RequestRecord> getPageRequestByUserName(String username) {
		return requestRecordDao.getPageRequestByUserName(username);
	}

	@Override
	public RequestRecord getRequestRecordById(Integer id) {
		return requestRecordDao.getRequestRecordById(id);
	}

	@Override
	public Pager<RequestRecord> getPageRefuseByUserName(String username) {
		return requestRecordDao.getPageRefuseByUserName(username);
	}

	@Override
	public RequestRecord insertRecord(User loginUser, RequestRecord requestRecord, String info, String memo,
			Double value) {
		return null;
	}

	@Override
	public Pager<RequestRecord> getPageRequestByInfo(String info) {
		return requestRecordDao.getPageRequestByInfo(info);
	}

	@Override
	public void refuse(RequestRecord requestRecord) {
		requestRecord.setState(2);

		requestRecordDao.update(requestRecord);
	}

	@Override
	public void accept(RequestRecord requestRecord, User loginUser, HttpSession session) {
		Double value = Double.valueOf(requestRecord.getAmount());
		User user = userDao.loadUserByUsername(requestRecord.getUsername());
		SendRecord sendRecord = new SendRecord();
		String info = null;
		if (user.getEmail() != null) {
			info = user.getEmail();
		} else if (user.getPhone() != null) {
			info = user.getPhone();
		}
		Double balance = loginUser.getBalance();
		if (value <= balance) {
			loginUser.setBalance(Double.valueOf(df.format(balance - value)));
			userDao.update(loginUser);// 支付用户立即扣钱
			session.setAttribute("loginUser", loginUser);
			sendRecord.setResource("balance");
		} else {
			sendRecord.setResource(loginUser.getPrimaryAccount().getBankAccount());
		}
		sendRecord.setState(1);//立即处理
		sendRecord.setIsExit(1);
		sendRecordDao.insertRecord(loginUser, sendRecord, info, "", value);
		//立即更新
		user.setBalance(Double.valueOf(df.format(user.getBalance() + value)));
		userDao.update(user);
		requestRecord.setState(1);
		requestRecordDao.update(requestRecord);
	}

	@Override
	public void requsetMoney(User loginUser, String info, String memo, Double value) {
		RequestRecord requestRecord = new RequestRecord();
		User user = userDao.loadUserByEmailOrPhone(info);
		if (user == null) {
			requestRecord.setIsExit(0);
		} else {
			requestRecord.setIsExit(1);
		}
		requestRecordDao.insertRecord(loginUser, requestRecord, info, memo, value);
	}

	@Override
	public void requestMoneySplit(User loginUser, String info1, String info2, Double percent, String memo1,
			String memo2, Double value) {

		Double per = percent / 100;
		Double amount1 = Double.valueOf(df.format(per * value));

		RequestRecord requestRecord1 = new RequestRecord();
		User user1 = userDao.loadUserByEmailOrPhone(info1);
		if (user1 == null) {
			requestRecord1.setIsExit(0);
		} else {
			requestRecord1.setIsExit(1);
		}

		RequestRecord requestRecord2 = new RequestRecord();
		Double amount2 = Double.valueOf(df.format(value - amount1));
		User user2 = userDao.loadUserByEmailOrPhone(info2);
		if (user2 == null) {
			requestRecord2.setIsExit(0);
		} else {
			requestRecord2.setIsExit(1);
		}

		requestRecordDao.insertRecord(loginUser, requestRecord1, info1, memo1, amount1);
		requestRecordDao.insertRecord(loginUser, requestRecord2, info2, memo2, amount2);
	}

	@Override
	public void cancelRequset(RequestRecord requestRecord, Integer id) {
		if (requestRecord.getState() == 1) {
			return;
		}
		requestRecordDao.delete(id);
	}

}
