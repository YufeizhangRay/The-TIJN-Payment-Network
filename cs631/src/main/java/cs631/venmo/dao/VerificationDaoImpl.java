package cs631.venmo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cs631.venmo.pojo.Verification;

@Repository("verificationDao")
public class VerificationDaoImpl extends BaseDaoImpl<Verification> implements VerificationDao {

	@Override
	public Verification insertVerificatin(Verification verification) {
		return super.add(verification);
	}

	@Override
	public List<Verification> loadVerificationsByAccount(String num) {
		String hql = "select v from Verification v where v.bankAccountNum=?";
		return super.list(hql, new Object[] { num }, null);
	}

	@Override
	public List<Verification> loadVerificationsByUsername(String username) {
		String hql = "select v from Verification v where v.username=?";
		return super.list(hql, new Object[] { username }, null);
	}

}
