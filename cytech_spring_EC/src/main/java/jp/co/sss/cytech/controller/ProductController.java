package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.repository.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/product/list")
	public String showProducts(String productName, Integer categoryId, Model model) {
		
		List<Product> productList;
		
		boolean hasProductName = productName != null && !productName.isEmpty();
		boolean hasCategoryId = categoryId != null;
		
		if (!hasProductName && !hasCategoryId) {
			productList = productRepository.findAll();
		} else if (hasProductName && !hasCategoryId) {
			productList = productRepository.findByProductNameContaining(productName);
		} else if (!hasProductName && hasCategoryId) {
			productList = productRepository.findByCategoryId(categoryId);
		} else {
			productList = productRepository.findByProductNameContainingAndCategoryId(productName, categoryId);
		}
		
		model.addAttribute("productList", productList);
		
		return "product/list";
	}
	
	@RequestMapping("/product/detail")
	public String showDetail() {
		return "product/detail";
	}

}
