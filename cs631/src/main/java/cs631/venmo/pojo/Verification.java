package cs631.venmo.pojo;

public class Verification {

	private int id;
	private String username;
	private Integer state;
	private String bankAccountNum;

	public String getBankAccountNum() {
		return bankAccountNum;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Verification [id=" + id + ", username=" + username + ", state=" + state + ", bankAccountNum="
				+ bankAccountNum + "]";
	}

}
