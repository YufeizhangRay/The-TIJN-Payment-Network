package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Families;

@Repository("familiesDao")
public class FamiliesDaoImpl extends BaseDaoImpl<Families> implements FamiliesDao {

	@Override
	public List<Families> getAllFamiliesByUsername(String username) {
		String hql = "select f from Families f where f.username=?";
		return super.list(hql, new Object[] { username }, null);
	}

	@Override
	public Families insertFriend(Families Families) {
		return super.add(Families);
	}

	@Override
	public Families loadFamilyById(Integer id) {
		String hql = "select f from Families f where f.id=?";
		return (Families) super.queryByHql(hql, new Object[] { id }, null);
	}

}
