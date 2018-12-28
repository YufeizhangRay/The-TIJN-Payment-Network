package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.User;

public interface SendRecordDao extends BaseDao<SendRecord> {

	public List<SendRecord> getAllSendRecords();

	public Double getTotalSendByTime(String usertime, String begin, String end);
	
	public Double getTotalRecevieByTime(String info, String begin, String end);

	public Pager<SendRecord> getPageSendByUserName(String username);

	public SendRecord getSendRecordById(Integer id);

	public Pager<SendRecord> getPageCancelByUserName(String username);
	
	public Double loadTotalCancelByUserName(String username);

	public SendRecord insertRecord(User loginUser, SendRecord sendRecord, String info, String memo, Double value);

	public List<SendRecord> getAllUnProcessSendRecords();

	public Double getSendTotalInNov(String username);

	public Double getSendTotalInDec(String username);

	public Double getRecevieTotalInNov(String info);

	public Double getRecevieTotalInDec(String info);

}
