package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/user/login")
	public String loginUser() {
		return "user/login";
	}
	
	@RequestMapping("/user/register")
	public String registerUser() {
		return "user/register";
	}
}
