package cs631.venmo.service;

import java.util.List;

import cs631.venmo.pojo.Verification;

public interface VerificationService extends BaseService<Verification>{

	public Verification insertVerificatin(Verification verification);
	
	public List<Verification> loadVerificationsByAccount(String num);
	
	public List<Verification> loadVerificationsByUsername(String username);
}
