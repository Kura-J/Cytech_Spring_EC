package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {
	@RequestMapping("/layout_view")
	public String layout_view() {
		return "layout_view";
	}
	
	@RequestMapping("/top")
	public String showTop() {
		return "top";
	}

}
