package cs631.venmo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.RequestRecord;
import cs631.venmo.pojo.User;

public interface RequestRecordService extends BaseService<RequestRecord> {

	public List<RequestRecord> getAllRequestRecords();

	public List<RequestRecord> getRequestRecordByTime();

	public Pager<RequestRecord> getPageRequestByUserName(String username);

	public Pager<RequestRecord> getPageRequestByInfo(String info);

	public RequestRecord getRequestRecordById(Integer id);

	public Pager<RequestRecord> getPageRefuseByUserName(String username);

	public RequestRecord insertRecord(User loginUser, RequestRecord requestRecord, String info, String memo,
			Double value);

	public void refuse(RequestRecord requestRecord);

	public void accept(RequestRecord requestRecord, User loginUser, HttpSession session);

	public void requsetMoney(User loginUser, String info, String memo, Double value);

	public void requestMoneySplit(User loginUser, String info1, String info2, Double percent, String memo1,
			String memo2, Double value);

	public void cancelRequset(RequestRecord requestRecord,Integer id);
}
