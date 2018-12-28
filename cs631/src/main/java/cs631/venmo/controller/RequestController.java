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
import cs631.venmo.pojo.RequestRecord;
import cs631.venmo.pojo.SystemContext;
import cs631.venmo.pojo.User;
import cs631.venmo.service.RequestRecordService;
import cs631.venmo.service.UserService;

@Controller
public class RequestController {

	@Autowired
	private RequestRecordService requestRecordService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/cancelRequset/{id}", method = RequestMethod.PUT)
	public String cancelRequset(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (id != null) {
			RequestRecord RequestRecord = requestRecordService.getRequestRecordById(id);
			requestRecordService.cancelRequset(RequestRecord, id);
		}
		return "redirect:/getPageRequestByUserName";
	}

	@RequestMapping(value = "/refuse/{id}", method = RequestMethod.PUT)
	public String refuse(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			System.out.println(loginUser);
			RequestRecord requestRecord = requestRecordService.getRequestRecordById(id);
			requestRecordService.refuse(requestRecord);
		}
		return "redirect:/getPageRequestToMe";
	}

	@RequestMapping(value = "/accept/{id}", method = RequestMethod.PUT)
	public String accept(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			if(loginUser.getPrimaryAccount().getId()==5) {
				return "redirect:/getPageRequestToMe";
			}
			User user = userService.loadUserByUsername(loginUser.getUsername());
			RequestRecord requestRecord = requestRecordService.getRequestRecordById(id);
			requestRecordService.accept(requestRecord, user, session);
		}
		return "redirect:/getPageRequestToMe";
	}

	@RequestMapping(value = "/requestMoney", method = RequestMethod.PUT)
	public String requestMoney(String info, String amount, String memo, HttpSession session) {
		if (info.equals("") || info == null || amount == null || amount.equals("")) {
			return "redirect:/requestMoney";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		Double value = Double.valueOf(amount);
		requestRecordService.requsetMoney(loginUser, info, memo, value);
		return "redirect:/requestMoney";
	}

	@RequestMapping(value = "/requestMoneySplit", method = RequestMethod.PUT)
	public String requestMoneySplit(String amount, String info1, String percent, String info2, String memo1,
			String memo2, HttpSession session) {
		if (info1.equals("") || info1 == null || amount == null || amount.equals("") || info2.equals("")
				|| info2 == null || percent.equals("") || percent == null) {
			return "redirect:/requestMoney";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		Double value = Double.valueOf(amount);
		Double per = Double.valueOf(percent);
		requestRecordService.requestMoneySplit(loginUser, info1, info2, per, memo1, memo2, value);
		return "redirect:/requestMoney";
	}

	@RequestMapping(value = "/requestMoneyFromFriendsAndFamilies", method = RequestMethod.PUT)
	public String requestMoneyFromFriendsAndFamilies() {

		return "redirect:/requestMoney";
	}

	@RequestMapping(value = "/requestMoney", method = RequestMethod.GET)
	public String requestMoneyView() {
		return "requestMoney";
	}

	@RequestMapping(value = "/getPageRequestToMe", method = RequestMethod.GET)
	public String getPageRequestToMe(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<RequestRecord> page = requestRecordService.getPageRequestByInfo(loginUser.getName());
		List<RequestRecord> requestToMeRecords = page.getRows();
		long pageNums = 1;
		if (requestToMeRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / requestToMeRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("requestToMeRecords", requestToMeRecords);
		model.addAttribute("requestToMePage", page);
		return "requestToMe";
	}

	@RequestMapping(value = "/getRequestToMeByPage/{i}", method = RequestMethod.GET)
	public String getRequestToMeByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<RequestRecord> page1 = requestRecordService.getPageRequestByInfo(loginUser.getName());
		List<RequestRecord> requestToMeRecords = page1.getRows();
		long pageNums = 1;
		if (requestToMeRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("requestToMeRecords", requestToMeRecords);
		model.addAttribute("requestToMePage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "requestToMe";
	}

	@RequestMapping(value = "/getPageRequestByUserName", method = RequestMethod.GET)
	public String getPageRequestByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<RequestRecord> page = requestRecordService.getPageRequestByUserName(loginUser.getName());
		List<RequestRecord> pageRequestRecords = page.getRows();
		long pageNums = 1;
		if (pageRequestRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageRequestRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageRequestRecords", pageRequestRecords);
		model.addAttribute("requestPage", page);
		return "allRequestRecord";
	}

	@RequestMapping(value = "/getRequestRecordByPage/{i}", method = RequestMethod.GET)
	public String getRequestRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<RequestRecord> page1 = requestRecordService.getPageRequestByUserName(loginUser.getName());
		List<RequestRecord> pageRequestRecords = page1.getRows();
		long pageNums = 1;
		if (pageRequestRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageRequestRecords", pageRequestRecords);
		model.addAttribute("requestPage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allRequestRecord";
	}

	@RequestMapping(value = "/getPageRefuseByUserName", method = RequestMethod.GET)
	public String getPageRefuseByUserName(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Pager<RequestRecord> page = requestRecordService.getPageRefuseByUserName(loginUser.getName());
		List<RequestRecord> pageRefuseRecords = page.getRows();
		long pageNums = 1;
		if (pageRefuseRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page.getTotal() / pageRefuseRecords.size());
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageRefuseRecords", pageRefuseRecords);
		model.addAttribute("refusePage", page);
		return "allRefuseRecord";
	}

	@RequestMapping(value = "/getRefuseRecordByPage/{i}", method = RequestMethod.GET)
	public String getRefuseRecordByPage(@PathVariable("i") Integer i, HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Integer rows = 10;
		if (i != null && i > 0) {
			SystemContext.setPageOffset((i - 1) * rows);
		}
		if (rows != null && rows > 0) {
			SystemContext.setPageSize(rows);
		}
		Pager<RequestRecord> page1 = requestRecordService.getPageRefuseByUserName(loginUser.getName());
		List<RequestRecord> pageRefuseRecords = page1.getRows();
		long pageNums = 1;
		if (pageRefuseRecords.size() != 0) {
			pageNums = (long) Math.ceil((double) page1.getTotal() / rows);
		}
		model.addAttribute("pageNums", pageNums);
		model.addAttribute("pageRefuseRecords", pageRefuseRecords);
		model.addAttribute("refusePage", page1);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return "allRefuseRecord";
	}

}
