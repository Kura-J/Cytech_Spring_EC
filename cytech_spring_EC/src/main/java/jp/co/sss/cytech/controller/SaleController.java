package jp.co.sss.cytech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaleController {
	@RequestMapping("/sale/detail")
	public String detailSale() {
		return "sale/detail";
	}
	
	@RequestMapping("/sale/confirm")
	public String confirmSale() {
		return "sale/confirm";
	}
	
	@RequestMapping("/sale/complete")
	public String completeSale() {
		return "sale/complete";
	}
}
