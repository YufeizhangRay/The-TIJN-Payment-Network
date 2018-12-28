package cs631.venmo.pojo;

public class Bank {

	private int id;
	private String bankName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", bankName=" + bankName + "]";
	}

}
