package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class MypageController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/mypage")
	public String showMypage(HttpSession session, Model model) {
		
		Integer loginUserId = (Integer) session.getAttribute("loginUserId");
		
		User user = userRepository.findById(loginUserId).orElse(null);
		
		model.addAttribute("user", user);
		
		return "mypage/mypage";
	}

}
