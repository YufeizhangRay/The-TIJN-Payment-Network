package cs631.venmo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.pojo.SystemContext;
import cs631.venmo.pojo.Pager;
import cs631.venmo.pojo.TopUpRecord;
import cs631.venmo.pojo.User;
import cs631.venmo.service.TopUpRecordService;
import cs631.venmo.web.AuthClass;
import cs631.venmo.web.AuthMethod;

@AuthClass
@Controller
public class TopUpRecordController {

	@Autowired
	private TopUpRecordService topUpRecordService;

	@AuthMethod
	@RequestMapping(value = "/getAllRecord", method = RequestMethod.GET)
	public String getAllRecord() {
		topUpRecordService.getAllRecord();
		return null;
	}

	@RequestMapping(value = "/getAllRecordByUsername", method = RequestMethod.GET)
	public String getAllRecordByUsername(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<TopUpRecord> allTopUpRecords = topUpRecordService.getAllRecordByUserName(loginUser.getName());
		model.addAttribute("allTopUpRecords", allTopUpRecords);
		return "allTopUpRecord";
	}

	@RequestMapping(value = "/getPageRecordByUserName", method = RequestMethod.GET)
	public String getPageRecordByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<TopUpRecord> page = topUpRecordService.getPageRecordByUserName(loginUser.getName());
		List<TopUpRecord> pageTopUpRecords = page.getRows();
		long pageNums = 1;
		if (pageTopUpRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageTopUpRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageTopUpRecords", pageTopUpRecords);
		model.addAttribute("page", page);
		return "allTopUpRecord";
	}

	@RequestMapping(value = "/getTopUpRecordByPage/{i}", method = RequestMethod.GET)
	public String getPageRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<TopUpRecord> page1 = topUpRecordService.getPageRecordByUserName(loginUser.getName());
		List<TopUpRecord> pageTopUpRecords = page1.getRows();
		long pageNums = 1;
		if (pageTopUpRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageTopUpRecords", pageTopUpRecords);
		model.addAttribute("page", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allTopUpRecord";
	}

}
