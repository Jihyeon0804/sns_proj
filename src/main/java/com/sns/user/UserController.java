package com.sns.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	// 화면 설계
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "/user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "/user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		session.removeAttribute("userEmail");
		return "redirect:/user/sign-in-view";
	}
	
	@GetMapping("/feed")
	public String feedView(Model model) {
		model.addAttribute("viewName", "/user/feed");
		return "template/layout";
	}
}
