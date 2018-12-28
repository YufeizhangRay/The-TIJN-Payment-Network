package cs631.venmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.service.BankService;
import cs631.venmo.web.AuthClass;
import cs631.venmo.web.AuthMethod;

@AuthClass
@Controller
public class BankController {

	@Autowired
	private BankService bankService;

	@AuthMethod
	@RequestMapping(value = "/getAllBank", method = RequestMethod.GET)
	public String getAllBank() {
		bankService.getAllBank();
		return null;
	}
}
