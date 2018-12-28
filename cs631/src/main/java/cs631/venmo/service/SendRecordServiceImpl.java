package cs631.venmo.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.SendRecordDao;
import cs631.venmo.dao.TotalCancelDao;
import cs631.venmo.dao.UserDao;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;

@Service("sendRecordService")
public class SendRecordServiceImpl extends BaseServiceImpl<SendRecord> implements SendRecordService {

	@Autowired
	private SendRecordDao sendRecordDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TotalCancelDao totalCancelDao;

	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public List<SendRecord> getAllSendRecords() {
		return sendRecordDao.getAllSendRecords();
	}

	@Override
	public Double getTotalSendByTime(String usertime, String begin, String end) {
		return sendRecordDao.getTotalSendByTime(usertime, begin, end);
	}

	@Override
	public Pager<SendRecord> getPageSendByUserName(String username) {
		return sendRecordDao.getPageSendByUserName(username);
	}

	@Override
	public Pager<SendRecord> getPageCancelByUserName(String username) {
		return sendRecordDao.getPageCancelByUserName(username);
	}

	@Override
	public SendRecord sendMoney(User loginUser, String info, String memo, Double value, HttpSession session) {
		SendRecord sendRecord = new SendRecord();
		Double balance = loginUser.getBalance();
		if (value <= balance) {
			System.out.println("当前余额："+loginUser.getBalance());
			System.out.println("汇款金额："+value);
			loginUser.setBalance(Double.valueOf(df.format(balance - value)));
			System.out.println("当前余额："+loginUser.getBalance());
			userDao.update(loginUser);// 支付用户立即扣钱
			session.setAttribute("loginUser", loginUser);
			sendRecord.setResource("balance");
		} else {
			sendRecord.setResource(loginUser.getPrimaryAccount().getBankAccount());
		}
		User user = userDao.loadUserByEmailOrPhone(info);
		if (user == null) {// 用户未注册
			sendRecord.setState(0);// 正在受理
			sendRecord.setIsExit(0);
		} else {
			sendRecord.setState(0);// 正在受理
			sendRecord.setIsExit(1);
		}
		return sendRecordDao.insertRecord(loginUser, sendRecord, info, memo, value);
	}

	@Override
	public void cancel(SendRecord sendRecord, User loginUser, HttpSession session) {
		if (sendRecord.getState() == 1) {
			return;// 若为已受理则直接返回
		}
		Double totalNow = sendRecordDao.loadTotalCancelByUserName(loginUser.getUsername());
		if(totalNow==null) {
			totalNow = 0.0;
		}
		System.out.println("totalCancelNow==========:"+totalNow);
		Double cancelAmount = sendRecord.getAmount();
		if (Double.valueOf(df.format(totalNow + cancelAmount)) <= 299.99) {
			loginUser.setBalance(Double.valueOf(df.format(loginUser.getBalance() + cancelAmount)));
			session.setAttribute("loginUser", loginUser);
			sendRecord.setState(2);
			sendRecordDao.update(sendRecord);
			userDao.update(loginUser);
		}
	}

	@Override
	public SendRecord getSendRecordById(Integer id) {
		return sendRecordDao.getSendRecordById(id);
	}

	@Override
	public List<SendRecord> getAllUnProcessSendRecords() {
		return sendRecordDao.getAllUnProcessSendRecords();
	}

	@Override
	public void processSend(SendRecord sendRecord) {
		sendRecord.setState(1);
		sendRecordDao.update(sendRecord);
		User user = userDao.loadUserByEmailOrPhone(sendRecord.getInfo());
		user.setBalance(Double.valueOf(df.format(user.getBalance() + sendRecord.getAmount())));
		userDao.update(user);
	}

	@Override
	public void cancelSend(SendRecord sendRecord) {
		sendRecord.setState(2);
		sendRecordDao.update(sendRecord);
		User user = userDao.loadUserByUsername(sendRecord.getUsername());
		user.setBalance(Double.valueOf(df.format(user.getBalance() + sendRecord.getAmount())));
		userDao.update(user);
	}

	@Override
	public List<SendRecord> unProcessSendRecordsAndUpdate() {
		List<SendRecord> sendRecords = sendRecordDao.getAllUnProcessSendRecords();
		List<User> users = userDao.getAllUsers();
		for (SendRecord sendRecord : sendRecords) {
			for (User user : users) {
				if (user.getEmail().equals(sendRecord.getInfo()) && sendRecord.getIsExit() == 0
						|| user.getPhone().equals(sendRecord.getInfo()) && sendRecord.getIsExit() == 0) {
					// 若未处理的汇款记录的目标用户不存在
					// 且其汇款路径与用户的邮箱或者电话任一相同
					// 则说明用户已经注册或绑定了邮箱或电话
					sendRecord.setIsExit(1);// 汇款目标用户置为存在
					System.out.println("用户更新！！！");
					sendRecordDao.update(sendRecord);
				}
			}
		}
		// 再一次获取刚更新好的列表
		List<SendRecord> records = sendRecordDao.getAllUnProcessSendRecords();
		return records;
	}

	@Override
	public Double getSendTotalInNov(String username) {
		return sendRecordDao.getSendTotalInNov(username);
	}

	@Override
	public Double getSendTotalInDec(String username) {
		return sendRecordDao.getSendTotalInDec(username);
	}

	@Override
	public Double getRecevieTotalInNov(User loginUser) {
		if (loginUser.getEmail().equals(loginUser.getPhone())) {
			return sendRecordDao.getRecevieTotalInNov(loginUser.getEmail());
		} else {
			Double sum1 = sendRecordDao.getRecevieTotalInNov(loginUser.getEmail());
			Double sum2 = sendRecordDao.getRecevieTotalInNov(loginUser.getPhone());
			if (sum1 == null) {
				sum1 = 0.0;
			}
			if (sum2 == null) {
				sum2 = 0.0;
			}
			return sum1 + sum2;
		}
	}

	@Override
	public Double getRecevieTotalInDec(User loginUser) {
		if (loginUser.getEmail().equals(loginUser.getPhone())) {
			return sendRecordDao.getRecevieTotalInDec(loginUser.getEmail());
		} else {

			Double sum1 = sendRecordDao.getRecevieTotalInDec(loginUser.getEmail());
			Double sum2 = sendRecordDao.getRecevieTotalInDec(loginUser.getPhone());
			if (sum1 == null) {
				sum1 = 0.0;
			}
			if (sum2 == null) {
				sum2 = 0.0;
			}
			return sum1 + sum2;
		}
	}

	@Override
	public Double getTotalRecevieByTime(User loginUser, String begin, String end) {
		if (loginUser.getEmail().equals(loginUser.getPhone())) {
			System.out.println("相同");
			return sendRecordDao.getTotalRecevieByTime(loginUser.getEmail(), begin, end);
		} else {
			Double sum1 = sendRecordDao.getTotalRecevieByTime(loginUser.getEmail(), begin, end);
			Double sum2 = sendRecordDao.getTotalRecevieByTime(loginUser.getPhone(), begin, end);
			if (sum1 == null) {
				sum1 = 0.0;
			}
			if (sum2 == null) {
				sum2 = 0.0;
			}
			return sum1 + sum2;
		}
	}

	@Override
	public Double loadTotalCancelByUserName(String username) {
		return sendRecordDao.loadTotalCancelByUserName(username);
	}

}
