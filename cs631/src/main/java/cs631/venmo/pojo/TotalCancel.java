package cs631.venmo.pojo;

import java.sql.Timestamp;

public class TotalCancel {

	private int id;
	private String username;
	private Double total;
	private Timestamp fristTotalTime;

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

	public Timestamp getFristTotalTime() {
		return fristTotalTime;
	}

	public void setFristTotalTime(Timestamp fristTotalTime) {
		this.fristTotalTime = fristTotalTime;
	}

	@Override
	public String toString() {
		return "TotalCancel [id=" + id + ", username=" + username + ", total=" + total + ", fristTotalTime="
				+ fristTotalTime + "]";
	}

}
