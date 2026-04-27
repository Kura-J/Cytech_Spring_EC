package jp.co.sss.cytech.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.cytech.entity.Review;
import jp.co.sss.cytech.form.ReviewForm;
import jp.co.sss.cytech.repository.ReviewRepository;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@RequestMapping("/review/review")
	public String productReview(Integer productId, Model model) {
		
		ReviewForm form = new ReviewForm();
		form.setProductId(productId);
		
		model.addAttribute("reviewForm", form);
		
		return "review/review";
	}
	
	@RequestMapping(path = "/review/review", method = RequestMethod.POST)
	public String addReview(
			@Valid @ModelAttribute ReviewForm form,
			BindingResult result,
			Model model,
			HttpSession session) throws IOException {
		
		Integer userId = (Integer) session.getAttribute("loginUserId");
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "review/review";
		}
		
		String reviewImgPath = "";
		
		MultipartFile reviewImage = form.getReviewImage();
		
		if (reviewImage != null && !reviewImage.isEmpty()) {
			String originalFileName = reviewImage.getOriginalFilename();
			String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
			Path savePath = Paths.get("src/main/resources/static/images/" + fileName);
			Files.copy(reviewImage.getInputStream(), savePath);
			reviewImgPath = "/images/" + fileName;
		}
		
		Review review = new Review();
		review.setProductId(form.getProductId());
		review.setUserId(userId);
		review.setRating(form.getRating());
		review.setComment(form.getComment());
		
		review.setDummyUserName(form.getDummyUserName());
		
		review.setReviewImgPath(reviewImgPath);
		
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		review.setCreatedAt(now);
		review.setUpdatedAt(now);
		
		reviewRepository.save(review);
		
		return "redirect:/product/detail?productId=" + form.getProductId();
		
	}
}




























