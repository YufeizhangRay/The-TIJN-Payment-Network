package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.RequestRecord;
import cs631.venmo.pojo.User;

public interface RequestRecordDao extends BaseDao<RequestRecord> {

	public List<RequestRecord> getAllRequestRecords();

	public List<RequestRecord> getRequestRecordByTime();

	public Pager<RequestRecord> getPageRequestByUserName(String username);

	public Pager<RequestRecord> getPageRequestByInfo(String info);

	public RequestRecord getRequestRecordById(Integer id);

	public Pager<RequestRecord> getPageRefuseByUserName(String username);

	public RequestRecord insertRecord(User loginUser, RequestRecord requestRecord, String info, String memo,
			Double value);

}
