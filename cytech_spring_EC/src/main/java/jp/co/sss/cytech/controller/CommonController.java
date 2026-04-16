package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sss.cytech.entity.Category;
import jp.co.sss.cytech.repository.CategoryRepository;

@ControllerAdvice
public class CommonController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@ModelAttribute("categoryList")
	public List<Category> setCategoryList() {
		return categoryRepository.findAll();
	}

}
