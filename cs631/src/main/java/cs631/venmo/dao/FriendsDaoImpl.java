package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Friends;

@Repository("friendsDao")
public class FriendsDaoImpl extends BaseDaoImpl<Friends> implements FriendsDao{

	@Override
	public List<Friends> getAllFriendsByUsername(String username) {
		String hql = "select f from Friends f where f.username=?";
		return super.list(hql, new Object[] {username}, null);
	}

	@Override
	public Friends insertFriend(Friends friends) {
		return super.add(friends);
	}

	@Override
	public Friends loadFriendById(Integer id) {
		String hql = "select f from Friends f where f.id=?";
		return (Friends) super.queryByHql(hql, new Object[] {id}, null);
	}

}
