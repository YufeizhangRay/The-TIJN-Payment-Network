package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Friends;

public interface FriendsDao extends BaseDao<Friends> {
	
	public List<Friends> getAllFriendsByUsername(String username);
	
	public Friends insertFriend(Friends friends);
	
	public Friends loadFriendById(Integer id);
}
