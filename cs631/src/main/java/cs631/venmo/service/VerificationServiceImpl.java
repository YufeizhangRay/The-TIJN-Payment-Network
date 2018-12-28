package cs631.venmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.dao.VerificationDao;
import cs631.venmo.pojo.Verification;

@Service("verificationService")
public class VerificationServiceImpl extends BaseServiceImpl<Verification> implements VerificationService{

	@Autowired
	private VerificationDao verificationDao;
	
	@Override
	public Verification insertVerificatin(Verification verification) {
		return verificationDao.insertVerificatin(verification);
	}

	@Override
	public List<Verification> loadVerificationsByAccount(String num) {
		return verificationDao.loadVerificationsByAccount(num);
	}

	@Override
	public List<Verification> loadVerificationsByUsername(String username) {
		return verificationDao.loadVerificationsByUsername(username);
	}

}
