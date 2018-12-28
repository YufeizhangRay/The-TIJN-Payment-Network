package cs631.venmo.pojo;

public class BankAccount {

	private int id;
	private String bankAccount;
	private Bank bank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", bankAccount=" + bankAccount + ", bank=" + bank + "]";
	}

}
