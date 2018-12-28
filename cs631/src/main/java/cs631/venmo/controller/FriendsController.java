package cs631.venmo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs631.venmo.pojo.Friends;
import cs631.venmo.pojo.User;
import cs631.venmo.service.FriendsService;

@Controller
public class FriendsController {

	@Autowired
	private FriendsService friendsService;
	
	@RequestMapping(value="/getAllFriendsByUsername",method=RequestMethod.GET)
	public String getAllFriendsByUsername(Model model,HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<Friends> friends =  friendsService.getAllFriendsByUsername(loginUser.getUsername());
		session.setAttribute("friends", friends);
		return "friendsAndFamilies";
	}
}
