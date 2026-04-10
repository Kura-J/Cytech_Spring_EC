package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("/products")
	public String showProducts() {
		return "product/list";
	}
	
	@RequestMapping("/detail")
	public String showDetail() {
		return "product/detail";
	}

}
