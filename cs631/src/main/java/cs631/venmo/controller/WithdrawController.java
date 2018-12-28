package cs631.venmo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.SystemContext;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.WithdrawRecord;
import cs631.venmo.service.WithdrawService;

@Controller
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;

	@RequestMapping(value = "/getAllWithdrawByUsername", method = RequestMethod.GET)
	public String getAllRecordByUsername(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<WithdrawRecord> allWithdrawRecords = withdrawService.getAllRecordByUserName(loginUser.getName());
		model.addAttribute("allWithdrawRecords", allWithdrawRecords);
		return "allWithdrawRecords";
	}

	@RequestMapping(value = "/getPageWithdrawByUserName", method = RequestMethod.GET)
	public String getPageRecordByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<WithdrawRecord> page = withdrawService.getPageRecordByUserName(loginUser.getName());
		List<WithdrawRecord> pageWithdrawRecords = page.getRows();
		long pageNums = 1;
		if (pageWithdrawRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageWithdrawRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageWithdrawRecords", pageWithdrawRecords);
		model.addAttribute("WithdrawPage", page);
		return "allWithdrawRecords";
	}

	@RequestMapping(value = "/getWithdrawRecordByPage/{i}", method = RequestMethod.GET)
	public String getPageRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<WithdrawRecord> page1 = withdrawService.getPageRecordByUserName(loginUser.getName());
		List<WithdrawRecord> pageWithdrawRecords = page1.getRows();
		long pageNums = 1;
		if (pageWithdrawRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageWithdrawRecords", pageWithdrawRecords);
		model.addAttribute("WithdrawPage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allWithdrawRecords";
	}

}
