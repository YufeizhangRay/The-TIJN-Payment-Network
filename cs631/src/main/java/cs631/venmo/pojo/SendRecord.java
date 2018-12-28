package cs631.venmo.pojo;

import java.sql.Timestamp;

public class SendRecord {

	private int id;
	private String username;
	private String resource;
	private String info;
	private Double amount;
	private String memo;
	private Timestamp time;// 交易时间
	private Integer state;// 0为正在处理，1为处理完毕，2为已经取消
	private Integer isExit;//0为未注册用户 1为已注册用户

	public Integer getIsExit() {
		return isExit;
	}

	public void setIsExit(Integer isExit) {
		this.isExit = isExit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "SendRecord [id=" + id + ", username=" + username + ", resource=" + resource + ", info=" + info
				+ ", amount=" + amount + ", memo=" + memo + ", time=" + time + ", state=" + state + ", isExit=" + isExit
				+ "]";
	}

}
