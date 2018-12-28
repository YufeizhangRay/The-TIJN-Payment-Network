package cs631.venmo.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.BankAccountDao;
import cs631.venmo.dao.FamiliesDao;
import cs631.venmo.dao.FriendsDao;
import cs631.venmo.dao.TopUpRecordDao;
import cs631.venmo.dao.TotalWithdrawDao;
import cs631.venmo.dao.UserDao;
import cs631.venmo.dao.VerificationDao;
import cs631.venmo.dao.WithdrawDao;
import cs631.venmo.pojo.Families;
import cs631.venmo.pojo.Friends;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TopUpRecord;
import cs631.venmo.pojo.TotalWithdraw;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.Verification;
import cs631.venmo.pojo.WithdrawRecord;
import cs631.venmo.utils.SecurityUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VerificationDao verificationDao;

	@Autowired
	private TopUpRecordDao topUpRecordDao;

	@Autowired
	private WithdrawDao withdrawDao;

	@Autowired
	private FriendsDao friendsDao;
	
	@Autowired
	private FamiliesDao familiesDao;
	
	@Autowired
	private BankAccountDao bankAccountDao;
	
	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Pager<User> getAllPagerUsers(User user) {
		return userDao.getAllPagerUsers(user);
	}

	@Override
	public void updateUser(User user, String string, HttpSession session) {
		if (!user.getPassword().equals(string)) {
			String newPasswrod = SecurityUtil.md5(user.getUsername(), user.getPassword());
			user.setPassword(newPasswrod);
			session.setAttribute("oldPassword", newPasswrod);
		}
		session.setAttribute("loginUser", user);
		System.out.println("更新后的loginUser" + user.getPrimaryAccount());
		userDao.update(user);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.loadUserByUsername(username);
		if (user == null)
			throw new RuntimeException("用户名或密码有误！");
		if (!SecurityUtil.md5(username, password).equals(user.getPassword())) {
			throw new RuntimeException("用户名或密码有误！");
		}
		if (user.getState() != 1)
			throw new RuntimeException("用户已被禁用！");

		return user;
	}

	@Override
	public User addUser(User user) {
		// 新增用户设置初始值
		user.setBalance(0.0);
		user.setEmailState(0);
		user.setPhoneState(0);
		user.setState(1);
		user.setTimestamp(new Timestamp(new Date().getTime()));
		user.setPrimaryAccount(bankAccountDao.load(5));
		return userDao.add(user);
	}

	@Override
	public List<User> getUserByTime() {
		return userDao.getUserByTime();
	}

	public long getCountByName(String username) {
		return userDao.getCountByName(username);
	}

	@Override
	public void topUp(User loginUser, Double value, HttpSession session) {
		TopUpRecord topUpRecord = new TopUpRecord();
		List<Verification> verifications = verificationDao.loadVerificationsByUsername(loginUser.getUsername());
		for (Verification ver : verifications) {
			if (ver.getBankAccountNum().equals(loginUser.getPrimaryAccount().getBankAccount())) {
				if (ver.getState() == 0) {
					ver.setState(1);
					verificationDao.update(ver);
				}
				break;
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		loginUser.setBalance(Double.valueOf(df.format(loginUser.getBalance() + value)));
		session.setAttribute("loginUser", loginUser);
		userDao.update(loginUser);
		topUpRecordDao.insertRecord(topUpRecord, loginUser, value);
	}

	@Override
	public void withdraw(User loginUser, Double value, HttpSession session){
		System.out.println("提现金额："+value);
		Double totalWithdraw  = withdrawDao.loadTotalByUsername(loginUser.getUsername());
		if(totalWithdraw==null) {
			totalWithdraw = 0.0;
		}
		System.out.println("开始判断");
		if (loginUser.getBalance() < value) {
			System.out.println("余额不足！");
			
			return;
		} else if (loginUser.getEmailState() == 1 || loginUser.getPhoneState() == 1) {
			if (value > 9999.99) {
				System.out.println("超过单笔最大限额！");
				return;
			} else if (Double.valueOf(df.format(value + totalWithdraw)) > 19999.99) {
				System.out.println("超过本周最大限额！");
				return;
			}
		} else if (loginUser.getEmailState() != 1 && loginUser.getPhoneState() != 1) {
			if (value > 499.99) {
				System.out.println("超过单笔最大限额");
				return;
			} else if (Double.valueOf(df.format(value + totalWithdraw)) > 999.99) {
				System.out.println("超过本周最大限额！");
				return;
			}
		}
		List<Verification> verifications = verificationDao.loadVerificationsByUsername(loginUser.getUsername());
		boolean isVerified = false;
		for (Verification ver : verifications) {
			if (ver.getBankAccountNum().equals(loginUser.getPrimaryAccount().getBankAccount())) {
				if(ver.getState()==1) {
					isVerified = true;
				}
				break;
			}
		}
		if (isVerified) {
			DecimalFormat df = new DecimalFormat("0.00");
			loginUser.setBalance(Double.valueOf(df.format(loginUser.getBalance() - value)));
			WithdrawRecord withdrawRecord = new WithdrawRecord();
			withdrawDao.insertRecord(withdrawRecord, loginUser, value);
			totalWithdraw = Double.valueOf(df.format(totalWithdraw + value));
			userDao.update(loginUser);
			session.setAttribute("loginUser", loginUser);
		} else {
			System.out.println("账户未验证!");
			return;
		}
	}

	@Override
	public void addAccount(User loginUser, String bankAccount, HttpSession session) {
		Verification verification = new Verification();
		List<Verification> verifications = verificationDao.loadVerificationsByUsername(loginUser.getUsername());
		boolean isExsit = false;
		for (Verification ver : verifications) {
			if (ver.getBankAccountNum().equals(bankAccount)) {
				System.out.println(ver+"===== "+bankAccount);
				isExsit = true;
				break;
			}
		}
		if (!isExsit) {
			verification.setUsername(loginUser.getUsername());
			verification.setState(0);
			verification.setBankAccountNum(bankAccount);
			verificationDao.insertVerificatin(verification);
			session.setAttribute("userVers", verificationDao.loadVerificationsByUsername(loginUser.getUsername()));
		}
		userDao.update(loginUser);
	}

	@Override
	public void deleteAccount(User loginUser, String bankAccount, HttpSession session) {
		List<Verification> verifications = verificationDao.loadVerificationsByUsername(loginUser.getUsername());
		boolean isExsit = false;
		int verificationId = 0;
		for (Verification ver : verifications) {
			if (ver.getBankAccountNum().equals(bankAccount)) {
				System.out.println("验证记录存在！！！！！！");
				isExsit = true;
				verificationId = ver.getId();
				break;
			}
		}
		if (isExsit) {
			verificationDao.delete(verificationId);
			System.out.println("删除验证");
			session.setAttribute("userVers", verificationDao.loadVerificationsByUsername(loginUser.getUsername()));
		}
		if(loginUser.getPrimaryAccount().getId()!=5) {
			if(loginUser.getPrimaryAccount().getBankAccount().equals(bankAccount)) {
				System.out.println("删除主账户");
				loginUser.setPrimaryAccount(bankAccountDao.load(5));
			}
		}
		session.setAttribute("loginUser", loginUser);
		userDao.update(loginUser);
	}

	@Override
	public User loadUserByEmailOrPhone(String info) {
		return userDao.loadUserByEmailOrPhone(info);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userDao.loadUserByUsername(username);
	}

	@Override
	public void addFriend(User loginUser,User user,HttpSession session) {
		Friends friend = new Friends();
		friend.setUsername(user.getUsername());
		friend.setEmail(user.getEmail());
		friend.setPhone(user.getPhone());;
		friendsDao.insertFriend(friend);
		loginUser.getFriends().add(friend);
		session.setAttribute("loginUser", loginUser);
		userDao.update(loginUser);
	}

	@Override
	public void addFamily(User loginUser, User user,HttpSession session) {
		Families family = new Families();
		family.setUsername(user.getUsername());
		family.setEmail(user.getEmail());
		family.setPhone(user.getPhone());
		familiesDao.insertFriend(family);
		loginUser.getFamilies().add(family);
		session.setAttribute("loginUser", loginUser);
		userDao.update(loginUser);
	}

	@Override
	public void deleteFriend(User loginUser, HttpSession session,Integer id) {
		friendsDao.delete(id);
		userDao.update(loginUser);
		session.setAttribute("loginUser", loginUser);
		
	}

	@Override
	public void deleteFamily(User loginUser, HttpSession session,Integer id) {
		familiesDao.delete(id);
		userDao.update(loginUser);
		session.setAttribute("loginUser", loginUser);
	}

}
