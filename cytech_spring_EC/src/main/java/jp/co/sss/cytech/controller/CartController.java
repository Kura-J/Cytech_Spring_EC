package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart/add")
	public String addCart() {
		return "cart/add";
	}
	
	@RequestMapping("/cart/detail")
	public String detailCart() {
		return "cart/detail";
	}
}
