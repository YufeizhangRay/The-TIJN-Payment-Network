package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> getAllUsers() {
		String hql = "from User";
		return super.list(hql, null, null);
	}
	
	@Override
	public Pager<User> getAllPagerUsers(User user) {
		String hql = "from User u where 1=1";
		if(user.getUsername()!=null&&!user.getUsername().equals("")) {
			hql += " and u.username like '%"+user.getUsername()+"%'";
		}
		return super.find(hql, null, null);
	}

	@Override
	public User loadUserByUsername(String username) {
		String hql = "select u from User u where u.username=?";
		return (User) super.queryByHql(hql, new Object[] {username}, null);
	}
	
	@Override
	public List<User> getUserByTime() {
		String hql = "from User u where 1=1 and u.timestamp between '2018-11-01' and '2018-12-01'";
		return super.list(hql, null, null);
	}

	@Override
	public long getCountByName(String username) {
		String hql = "select count(u) from User u where u.username=?";
		return (long) super.queryByHql(hql, new Object[] {username}, null);
	}

	@Override
	public User loadUserByEmailOrPhone(String info) {
		String hql = "from User u where u.email = ? or u.phone = ?";
		return (User) super.queryByHql(hql, new Object[] {info,info}, null);
	}

}
