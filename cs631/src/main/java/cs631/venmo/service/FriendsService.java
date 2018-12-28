package cs631.venmo.service;

import java.util.List;

import cs631.venmo.pojo.Friends;

public interface FriendsService extends BaseService<Friends> {

	public List<Friends> getAllFriendsByUsername(String username);

	public Friends insertFriend(Friends friends);

	public Friends loadFriendById(Integer id);
}
