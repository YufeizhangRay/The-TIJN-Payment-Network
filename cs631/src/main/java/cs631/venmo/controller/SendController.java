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
import cs631.venmo.pojo.SendRecord;
import cs631.venmo.pojo.SystemContext;
import cs631.venmo.pojo.TotalCancel;
import cs631.venmo.pojo.User;
import cs631.venmo.service.SendRecordService;
import cs631.venmo.service.TotalCancelService;
import cs631.venmo.service.UserService;

@Controller
public class SendController {

	@Autowired
	private SendRecordService sendRecordService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getTotalSendByTime", method = RequestMethod.GET)
	public String getTotalSendByTime(String begin, String end, Model model, HttpSession session) {
		if (begin == null || begin.equals("") || end == null || end.equals("")) {
			return "getSearchDIY";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		Double sendResult = sendRecordService.getTotalSendByTime(loginUser.getUsername(), begin, end);
		System.out.println("sendResult==================="+sendResult);
		model.addAttribute("sendResult", sendResult);
		return "result";
	}

	@RequestMapping(value = "/getTotalRecevieByTime", method = RequestMethod.GET)
	public String getTotalRecevieByTime(String begin, String end, Model model, HttpSession session) {
		if (begin == null || begin.equals("") || end == null || end.equals("")) {
			return "getSearchDIY";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		Double receiveResult = sendRecordService.getTotalRecevieByTime(loginUser, begin, end);
		model.addAttribute("receiveResult", receiveResult);
		return "result";
	}

	@RequestMapping(value = "/getSearchDIY", method = RequestMethod.GET)
	public String getSearchDIY(Model model, HttpSession session) {
		return "getSearchDIY";
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String transactions(Model model, HttpSession session) {
		return "transactions";
	}

	@RequestMapping(value = "/statements", method = RequestMethod.GET)
	public String statements(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		Double totalSendDec = sendRecordService.getSendTotalInDec(loginUser.getUsername());
		Double totalSendNov = sendRecordService.getSendTotalInNov(loginUser.getUsername());
		Double totalRecevieDec = sendRecordService.getRecevieTotalInDec(loginUser);
		Double totalRecevieNov = sendRecordService.getRecevieTotalInNov(loginUser);
		model.addAttribute("totalSendDec", totalSendDec);
		model.addAttribute("totalSendNov", totalSendNov);
		model.addAttribute("totalRecevieDec", totalRecevieDec);
		model.addAttribute("totalRecevieNov", totalRecevieNov);
		return "statements";
	}

	@RequestMapping(value = "/cancelSend/{id}", method = RequestMethod.PUT)
	public String cancelSend(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			SendRecord sendRecord = sendRecordService.getSendRecordById(id);
			sendRecordService.cancel(sendRecord, loginUser, session);
		}
		return "redirect:/getPageSendByUserName";
	}

	@RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
	public String sendMoneyView() {
		return "sendMoney";
	}

	@RequestMapping(value = "/sendMoney", method = RequestMethod.PUT)
	public String sendMoney(String info, String amount, String memo, HttpSession session) {
		if (info.equals("") || info == null || amount == null || amount.equals("")) {
			return "redirect:/sendMoney";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		//没有主账户，不允许汇款
		if(loginUser.getPrimaryAccount().getId()==5) {
			return "redirect:/sendMoney";
		}
		User user = userService.loadUserByUsername(loginUser.getUsername());
		Double value = Double.valueOf(amount);
		sendRecordService.sendMoney(user, info, memo, value, session);
		return "redirect:/sendMoney";
	}

	@RequestMapping(value = "/getPageSendByUserName", method = RequestMethod.GET)
	public String getPageSendByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Double cancelNow = sendRecordService.loadTotalCancelByUserName(loginUser.getUsername());
		Pager<SendRecord> page = sendRecordService.getPageSendByUserName(loginUser.getName());
		List<SendRecord> pageSendRecords = page.getRows();
		long pageNums = 1;
		if (pageSendRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageSendRecords.size());
		}
		model.addAttribute("cancelNow", cancelNow);
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageSendRecords", pageSendRecords);
		model.addAttribute("sendPage", page);
		return "allSendRecords";
	}

	@RequestMapping(value = "/getSendRecordByPage/{i}", method = RequestMethod.GET)
	public String getSendRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Double cancelNow = sendRecordService.loadTotalCancelByUserName(loginUser.getUsername());
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<SendRecord> page1 = sendRecordService.getPageSendByUserName(loginUser.getName());
		List<SendRecord> pageSendRecords = page1.getRows();
		long pageNums = 1;
		if (pageSendRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("cancelNow", cancelNow);
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageSendRecords", pageSendRecords);
		model.addAttribute("sendPage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allSendRecords";
	}

	@RequestMapping(value = "/getPageCancelByUserName", method = RequestMethod.GET)
	public String getPageCancelByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<SendRecord> page = sendRecordService.getPageCancelByUserName(loginUser.getName());
		List<SendRecord> pageCancelRecords = page.getRows();
		long pageNums = 1;
		if (pageCancelRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageCancelRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageCancelRecords", pageCancelRecords);
		model.addAttribute("cancelPage", page);
		return "allCancelRecord";
	}

	@RequestMapping(value = "/getCancelRecordByPage/{i}", method = RequestMethod.GET)
	public String getCancelRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<SendRecord> page1 = sendRecordService.getPageCancelByUserName(loginUser.getName());
		List<SendRecord> pageCancelRecords = page1.getRows();
		long pageNums = 1;
		if (pageCancelRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageCancelRecords", pageCancelRecords);
		model.addAttribute("cancelPage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allCancelRecord";
	}
}
