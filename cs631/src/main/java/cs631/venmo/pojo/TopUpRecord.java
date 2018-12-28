package cs631.venmo.pojo;

import java.sql.Timestamp;

public class TopUpRecord {

	private int id;
	private String username;
	private Double amount;
	private String account;
	private String bankName;
	private Timestamp topUpTime;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getTopUpTime() {
		return topUpTime;
	}

	public void setTopUpTime(Timestamp topUpTime) {
		this.topUpTime = topUpTime;
	}

}
