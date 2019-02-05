package cs631.venmo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.pojo.User;
import cs631.venmo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		// 调用userService来判断登录的用户名和密码对不对
		User loginUser = userService.login(username, password); // 登录成功，返回一个user对象，抛出异常，返回null
		// 登录成功的用户保存到session域空间中
		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("oldPassword", loginUser.getPassword());
			System.out.println(loginUser.getPrimaryAccount());
			session.setAttribute("primaryAccount", loginUser.getPrimaryAccount());
		}
		boolean isadmin = false;
		// 判断登录的用户是普通的用户还是超级管理员
		if (loginUser.getUsername().equals("admin")) {
			isadmin = true;
			// 将是否为超级管理员放入session域空间
			session.setAttribute("isadmin", isadmin);
			return "redirect:/users";
		}
		// 将是否为超级管理员放入session域空间
		session.setAttribute("isadmin", isadmin);

		return "redirect:/main";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("isadmin");
		session.removeAttribute("loginUser");
		session.removeAttribute("primaryAccount");
		session.removeAttribute("oldPassword");
		session.invalidate();
		return "redirect:/login";
	}

}
