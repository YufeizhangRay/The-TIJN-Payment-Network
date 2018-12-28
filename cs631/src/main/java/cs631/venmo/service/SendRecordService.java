package cs631.venmo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.User;

public interface SendRecordService extends BaseService<SendRecord> {

	public List<SendRecord> getAllSendRecords();

	public Double getTotalSendByTime(String usertime, String begin, String end);
	
	public Double getTotalRecevieByTime(User loginUser, String begin, String end);
	
	public Double loadTotalCancelByUserName(String username);

	public Pager<SendRecord> getPageSendByUserName(String username);

	public Pager<SendRecord> getPageCancelByUserName(String username);

	public SendRecord getSendRecordById(Integer id);

	public SendRecord sendMoney(User loginUser, String info, String memo, Double value, HttpSession session);

	public void cancel(SendRecord sendRecord, User loginUser, HttpSession session);

	public List<SendRecord> getAllUnProcessSendRecords();

	public List<SendRecord> unProcessSendRecordsAndUpdate();

	public void processSend(SendRecord sendRecord);

	public void cancelSend(SendRecord sendRecord);

	public Double getSendTotalInNov(String username);

	public Double getSendTotalInDec(String username);
	
	public Double getRecevieTotalInNov(User loginUser);

	public Double getRecevieTotalInDec(User loginUser);
}
