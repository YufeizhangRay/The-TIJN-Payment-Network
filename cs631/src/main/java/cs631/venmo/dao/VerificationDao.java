package cs631.venmo.dao;

import java.util.List;

import cs631.venmo.pojo.Verification;

public interface VerificationDao extends BaseDao<Verification>{

	public Verification insertVerificatin(Verification verification);
	
	public List<Verification> loadVerificationsByAccount(String num);
	
	public List<Verification> loadVerificationsByUsername(String username);
}
