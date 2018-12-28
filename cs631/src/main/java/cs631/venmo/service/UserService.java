package cs631.venmo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;

public interface UserService extends BaseService<User> {

	/**
	 * 获取所有的用户 不支持分页
	 */
	public List<User> getAllUsers();

	/**
	 * 获取所有的用户 支持分页
	 */
	public Pager<User> getAllPagerUsers(User user);

	public void updateUser(User user, String string, HttpSession session);

	/**
	 * 判断填写的用户名和密码是否正确
	 */
	public User login(String username, String password);

	/**
	 * 添加用户的专用方法
	 */
	public User addUser(User user);

	public List<User> getUserByTime();

	public long getCountByName(String username);

	public void topUp(User loginUser, Double value, HttpSession session);

	public void withdraw(User loginUser, Double value, HttpSession session);

	public void addAccount(User loginUser, String bankAccount, HttpSession session);

	public void deleteAccount(User loginUser, String bankAccount, HttpSession session);

	public User loadUserByEmailOrPhone(String info);

	public User loadUserByUsername(String username);

	public void addFriend(User loginUser, User user, HttpSession session);

	public void deleteFriend(User loginUser, HttpSession session, Integer id);

	public void addFamily(User loginUser, User user, HttpSession session);

	public void deleteFamily(User loginUser, HttpSession session, Integer id);
}
