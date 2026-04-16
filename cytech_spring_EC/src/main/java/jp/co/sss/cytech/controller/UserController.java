package jp.co.sss.cytech.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.LoginForm;
import jp.co.sss.cytech.form.RegisterForm;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class UserController {
	@RequestMapping(path = "/user/login", method = RequestMethod.GET)
	public String loginUser(@ModelAttribute LoginForm loginForm) {
		return "user/login";
	}
	
	@RequestMapping(path = "/user/login", method = RequestMethod.POST)
	public String doLoginUser(
			@Valid @ModelAttribute LoginForm loginForm,
			BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "user/login";
		}
		
		User user = userRepository.findByEmail(loginForm.getEmail());
		
		if (user == null) {
			result.reject("login.failed");
			return "user/login";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (!encoder.matches(loginForm.getPassword(), user.getPasswords())) {
			result.reject("login.failed");
			return "user/login";
		}
		
		session.setAttribute("loginUserId", user.getUserId());
		session.setAttribute("loginUserName", user.getUserName());
		
		return "redirect:/top";
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/user/register", method = RequestMethod.GET)
	public String showRegister(@ModelAttribute RegisterForm userForm) {
		return "user/register";
	}
	
	@RequestMapping(path = "/user/register", method = RequestMethod.POST)
	public String register(
			@Valid @ModelAttribute RegisterForm userForm,
			BindingResult result,
			Model model) {
		
		if (userForm.getPassword() != null && userForm.getPasswordConfirm() != null
				&& !userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			result.rejectValue("passwordConfirm", "register.password.mismatch");
		}
		
		User existingUser = userRepository.findByEmail(userForm.getEmail());
		if (existingUser != null) {
			result.rejectValue("email", "register.email.already");
		}
		
		if (result.hasErrors()) {
			return "user/register";
		}
		
		User user = new User();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(userForm.getPassword());
		
		user.setUserName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPasswords(encodedPassword);
		
		user.setUserAddress("");
		user.setPhone("");
		
		LocalDateTime now = LocalDateTime.now();
		user.setCreatedAt(now);
		user.setUpdatedAt(now);
		
		userRepository.save(user);
		
		System.out.println("ユーザ名：" + userForm.getName());
		System.out.println("メールアドレス" + userForm.getEmail());
		System.out.println("パスワード" + userForm.getPassword());
		System.out.println("確認用パスワード" + userForm.getPasswordConfirm());
		
		return "redirect:/user/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
}





















