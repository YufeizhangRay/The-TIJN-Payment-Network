package cs631.venmo.pojo;

import java.sql.Timestamp;

public class TotalWithdraw {

	private int id;
	private String username;
	private Double total;
	private Timestamp fristWithdrawTime;

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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Timestamp getFristWithdrawTime() {
		return fristWithdrawTime;
	}

	public void setFristWithdrawTime(Timestamp fristWithdrawTime) {
		this.fristWithdrawTime = fristWithdrawTime;
	}

	@Override
	public String toString() {
		return "TotalWithdrow [id=" + id + ", username=" + username + ", total=" + total + ", fristWithdrawTime="
				+ fristWithdrawTime + "]";
	}

}
