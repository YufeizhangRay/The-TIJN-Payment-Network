package cs631.venmo.pojo;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

	private int id;
	@NotEmpty(message = "username can not empty")
	private String username;
	@NotEmpty(message = "password can not empty")
	private String password;
	private String name;
	private String ssn;
	private String email;
	private Integer emailState;// 0为未验证
	private String phone;
	private Integer phoneState;// 1为已验证
	private Double balance;
	private Integer state;// 是否被封号
	private Timestamp timestamp;// 用户注册时间
	private BankAccount primaryAccount;
	private Set<BankAccount> bankAccounts;
	private Set<Friends> friends;
	private Set<Families> families;

	public Set<Friends> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friends> friends) {
		this.friends = friends;
	}

	public Set<Families> getFamilies() {
		return families;
	}

	public void setFamilies(Set<Families> families) {
		this.families = families;
	}

	public BankAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(BankAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailState() {
		return emailState;
	}

	public void setEmailState(Integer emailState) {
		this.emailState = emailState;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(Integer phoneState) {
		this.phoneState = phoneState;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", ssn="
				+ ssn + ", email=" + email + ", emailState=" + emailState + ", phone=" + phone + ", phoneState="
				+ phoneState + ", balance=" + balance + ", state=" + state + "]";
	}

}
