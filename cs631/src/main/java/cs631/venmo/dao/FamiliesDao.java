package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Families;

public interface FamiliesDao extends BaseDao<Families> {

	public List<Families> getAllFamiliesByUsername(String username);

	public Families insertFriend(Families Families);
	
	public Families loadFamilyById(Integer id);

}
