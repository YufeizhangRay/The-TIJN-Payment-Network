package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.FriendsDao;
import cs631.venmo.pojo.Friends;

@Service("friendsService")
public class FriendsServiceImpl extends BaseServiceImpl<Friends> implements FriendsService {

	@Autowired
	private FriendsDao friendsDao;
	
	@Override
	public List<Friends> getAllFriendsByUsername(String username) {
		return friendsDao.getAllFriendsByUsername(username);
	}

	@Override
	public Friends insertFriend(Friends friends) {
		return friendsDao.insertFriend(friends);
	}

	@Override
	public Friends loadFriendById(Integer id) {
		return friendsDao.loadFriendById(id);
	}

}
