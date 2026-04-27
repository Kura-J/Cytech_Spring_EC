package jp.co.sss.cytech.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewForm {
	
	@NotNull
	private Integer productId;
	
	@NotBlank(message = "{review.dummy-user-name.required}")
	private String dummyUserName;
	
	@NotBlank(message = "{review.comment.required}")
	@Size(max = 300, message = "{review.comment.size}")
	private String comment;
	
	@NotNull(message = "{review.rating.required}")
	@Min(value = 1)
	@Max(value = 5)
	private Integer rating;
	
	@Email
	@NotBlank(message = "{review.email.required}")
	private String email;
	
	private MultipartFile reviewImage;

	public String getComment() {
		return comment;
	}

	public MultipartFile getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(MultipartFile reviewImage) {
		this.reviewImage = reviewImage;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDummyUserName() {
		return dummyUserName;
	}

	public void setDummyUserName(String dummyUserName) {
		this.dummyUserName = dummyUserName;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
