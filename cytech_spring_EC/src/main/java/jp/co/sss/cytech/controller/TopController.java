package jp.co.sss.cytech.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.cytech.entity.SaleItem;
import jp.co.sss.cytech.repository.SaleItemRepository;

@Controller
public class TopController {
	
	@Autowired
	private SaleItemRepository saleItemRepository;
	
	@RequestMapping("/layout_view")
	public String layout_view() {
		return "layout_view";
	}
	
	@RequestMapping("/top")
	public String showTop(Model model) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		List<SaleItem> saleItemList = saleItemRepository.findActiveSaleItems(now);
		
		model.addAttribute("saleItemList", saleItemList);
		
		return "top";
	}

}










































