package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;

public interface UserDao extends BaseDao<User>{

	/**
	 * 获取所有的用户 不支持分页
	 */
	public List<User> getAllUsers();
	
	/**
	 * 获取所有的用户 支持分页和模糊查询
	 */
	public Pager<User> getAllPagerUsers(User user);

	public User loadUserByUsername(String username);
	
	public User loadUserByEmailOrPhone(String info);
	
	public List<User> getUserByTime();
	
	public long getCountByName(String username);
}
