package cs631.venmo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.pojo.BankAccount;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.Verification;
import cs631.venmo.service.BankAccountService;
import cs631.venmo.service.VerificationService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private VerificationService verificationService;

	@RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
	public String allAccount(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<Verification> userVers = verificationService.loadVerificationsByUsername(loginUser.getUsername());
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("accounts", loginUser.getBankAccounts());
		model.addAttribute("userVers",userVers);
		return "allAccounts";
	}

	@RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
	public List<BankAccount> getAllAccounts() {
		bankAccountService.getAllBankAccounts();
		return bankAccountService.getAllBankAccounts();
	}
}
