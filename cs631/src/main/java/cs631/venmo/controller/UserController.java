package cs631.venmo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cs631.venmo.utils.SecurityUtil;
import cs631.venmo.web.AuthClass;
import cs631.venmo.web.AuthMethod;
import cs631.venmo.pojo.BankAccount;
import cs631.venmo.pojo.Families;
import cs631.venmo.pojo.Friends;
import cs631.venmo.pojo.TotalWithdraw;
import cs631.venmo.pojo.User;
import cs631.venmo.pojo.Verification;
import cs631.venmo.service.BankAccountService;
import cs631.venmo.service.TotalWithdrawService;
import cs631.venmo.service.UserService;
import cs631.venmo.service.VerificationService;
import cs631.venmo.service.WithdrawService;

@AuthClass
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private WithdrawService withdrawService;

	@RequestMapping(value = "/deleteFamily/{id}", method = RequestMethod.PUT)
	public String deleteFamilies(@PathVariable("id") Integer id, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			Set<Families> families = loginUser.getFamilies();
			for (Families family : families) {
				if (family.getId() == id) {
					loginUser.getFamilies().remove(family);
					userService.deleteFamily(loginUser, session, id);
					return "redirect:/friendsAndFamilies";
				}
			}
		}
		return "redirect:/friendsAndFamilies";
	}

	@RequestMapping(value = "/addFamily", method = RequestMethod.PUT)
	public String addFamily(String username, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		User user = userService.loadUserByUsername(username);
		if (user == null || username.equals(loginUser.getUsername())) {
			return "redirect:/friendsAndFamilies";
		}
		userService.addFamily(loginUser, user, session);
		return "redirect:/friendsAndFamilies";
	}

	@RequestMapping(value = "/deleteFriend/{id}", method = RequestMethod.PUT)
	public String deleteFriend(@PathVariable("id") Integer id, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			Set<Friends> friends = loginUser.getFriends();
			for (Friends friend : friends) {
				if (friend.getId() == id) {
					loginUser.getFriends().remove(friend);
					userService.deleteFriend(loginUser, session, id);
					return "redirect:/friendsAndFamilies";
				}
			}
		}
		return "redirect:/friendsAndFamilies";
	}

	@RequestMapping(value = "/addFriend", method = RequestMethod.PUT)
	public String addFriend(String username, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		User user = userService.loadUserByUsername(username);
		if (user == null || username.equals(loginUser.getUsername())) {
			return "redirect:/friendsAndFamilies";
		}
		userService.addFriend(loginUser, user, session);
		return "redirect:/friendsAndFamilies";
	}

	@RequestMapping(value = "/friendsAndFamilies", method = RequestMethod.GET)
	public String getFriendsAndFamiliesByUsername(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		Set<Friends> friends = loginUser.getFriends();
		Set<Families> families = loginUser.getFamilies();
		session.setAttribute("friends", friends);
		session.setAttribute("families", families);
		return "friendsAndFamilies";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
	public String withdraw(String balance, HttpSession session) {
		if (balance == null || balance.equals("")) {
			return "redirect:/account";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		Double value = Double.valueOf(balance);
		userService.withdraw(loginUser, value, session);
		return "redirect:/account";
	}

	@RequestMapping(value = "/topUp", method = RequestMethod.PUT)
	public String topUp(String balance, HttpSession session) {
		if (balance == null || balance.equals("")) {
			return "redirect:/account";
		}
		User loginUser = (User) session.getAttribute("loginUser");
		//没有主账户，不允许氪金
		if(loginUser.getPrimaryAccount().getId()==5) {
			return "redirect:/account";
		}
		Double value = Double.valueOf(balance);
		userService.topUp(loginUser, value, session);
		return "redirect:/account";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/allAccounts", method = RequestMethod.PUT)
	public String addUserBankAccont(String bankAccount, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		Set<BankAccount> accounts = (Set<BankAccount>) session.getAttribute("allAccounts");
		System.out.println("accounts的数量"+accounts.size());
		System.out.println(accounts);
		for (BankAccount account : accounts) {
			System.out.println("进入循环");
			if(account.getBankAccount()!=null) {
				if (account.getBankAccount().equals(bankAccount)) {
					System.out.println("添加账户");
					loginUser.getBankAccounts().add(account);
					session.setAttribute("loginUser", loginUser);
					userService.addAccount(loginUser, bankAccount, session);
					return "redirect:/allAccounts";
				}
			}
			System.out.println("进入循环");
		}
		System.out.println("准备跳转");
		return "redirect:/allAccounts";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String adduser(@Valid User user, BindingResult error, Model model) {
		if (error.getErrorCount() > 0) {
			System.out.println("某些数据不符合数据有效性校验！");
			for (FieldError fe : error.getFieldErrors()) {
				System.out.println("===" + fe.getField() + ":" + fe.getDefaultMessage());
			}
			return "register";
		}
		String username = user.getUsername();
		String password = user.getPassword();
		if (userService.getCountByName(username) == 1) {
			model.addAttribute("note", "用户名已占用");
			return "register";
		}
		password = SecurityUtil.md5(username, password);
		user.setPassword(password);
		userService.addUser(user);
		return "redirect:/success";
	}

	@AuthMethod
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@AuthMethod
	@RequestMapping(value = "/changeUserState/{id}", method = RequestMethod.GET)
	public String changeUserState(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (id != null) {
			User user = userService.load(id);
			session.setAttribute("userPassword", user.getPassword());
			model.addAttribute("user", user);
		}

		return "changeUserState";
	}

	@ModelAttribute
	public void getUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			User user = userService.load(id);
			// 若下面的方法参数无@ModelAttribute注解，则此键值需要用类型首字母小写 即user
			model.addAttribute("user", user);
		}
	}

	@AuthMethod
	@RequestMapping(value = "/changeUserState", method = RequestMethod.PUT)
	public String changeUserState(@ModelAttribute(value = "user") User user, HttpSession session) {
		String userPassword = (String) session.getAttribute("userPassword");
		userService.updateUser(user, userPassword, session);
		return "redirect:/users";
	}

	@AuthMethod
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") Integer id) {
		if (id != null) {
			userService.delete(id);
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.PUT)
	public String deleteAccount(@PathVariable("id") Integer id, HttpSession session) {
		if (id != null) {
			User loginUser = (User) session.getAttribute("loginUser");
			Set<BankAccount> bankAccounts = loginUser.getBankAccounts();
			for (BankAccount bankAccount : bankAccounts) {
				if (bankAccount.getId() == id) {
					loginUser.getBankAccounts().remove(bankAccount);
					userService.deleteAccount(loginUser, bankAccount.getBankAccount(), session);
					return "redirect:/allAccounts";
				}
			}
		}
		return "redirect:/allAccounts";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		return "success";
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(HttpSession session) {
		List<BankAccount> list = bankAccountService.getAllBankAccounts();
		User loginUser = (User) session.getAttribute("loginUser");
		User user = userService.loadUserByUsername(loginUser.getUsername()); 
		session.setAttribute("loginUser", user);
		Double totalWithdraw = withdrawService.loadTotalByUsername(loginUser.getUsername());
		System.out.println("Controller totalWithdraw" + totalWithdraw);
		session.setAttribute("totalWithdraw", totalWithdraw);
		List<Verification> verifications = verificationService.loadVerificationsByUsername(loginUser.getName());
		session.setAttribute("userVers", verifications);
		Set<BankAccount> allAccounts = new HashSet<BankAccount>(list);
		session.setAttribute("allAccounts", allAccounts);
		return "account";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public String updateUser(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		//这里的键"loginUser"与spring表单的modelAttribute="loginUser"一致，可以用来自动显示数据
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("userbankAccounts", loginUser.getBankAccounts());
		return "update";
	}

	@ModelAttribute
	public void getCurrentUser(@RequestParam(value = "id", required = false) Integer id, Model model,
			HttpSession session) {
		//此方法会在所有controller方法被调用之前自动调用，所以前端没有传来id的方法可以不做操作
		if (id != null) {
			//相当于准备要被覆盖的对象
			User loginUser = (User) session.getAttribute("loginUser");
			//此键"updateUser"与@ModelAttribute(value = "updateUser")对应 需要保持一致
			model.addAttribute("loginUser", loginUser);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)//参数中的updateUser是已经被覆盖完毕的
	public String updateUser(@ModelAttribute(value = "loginUser") User updateUser, HttpSession session) {
		String oldPassword = (String) session.getAttribute("oldPassword");
		System.out.println(session.getAttribute("allAccounts"));
		Set<BankAccount> accounts = (Set<BankAccount>) session.getAttribute("allAccounts");
		System.out.println(accounts);
		for (BankAccount bankAccount : accounts) {
			if(bankAccount.getBankAccount()!=null) {
				if (bankAccount.getId() == updateUser.getPrimaryAccount().getId()) {
					session.setAttribute("primaryAccount", bankAccount);
					updateUser.setPrimaryAccount(bankAccount);
					break;
				}
			}
		}
		userService.updateUser(updateUser, oldPassword, session);
		return "redirect:/account";
	}

	@ResponseBody
	@RequestMapping(value = "/getUserByTime", method = RequestMethod.GET)
	public void getUserByTime() {
		System.out.println(userService.getUserByTime());
	}

	@ResponseBody
	@RequestMapping(value = "/loadUserByEmailOrPhone", method = RequestMethod.GET)
	public void loadUserByEmailOrPhone() {
		System.out.println(userService.loadUserByEmailOrPhone("222"));
	}
}
