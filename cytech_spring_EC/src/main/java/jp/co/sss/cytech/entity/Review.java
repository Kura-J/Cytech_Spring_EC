package jp.co.sss.cytech.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Integer reviewId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "dummy_user_name")
	private String dummyUserName;
	
	@Column(name = "review_img_path")
	private String reviewImgPath;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDummyUserName() {
		return dummyUserName;
	}

	public void setDummyUserName(String dummyUserName) {
		this.dummyUserName = dummyUserName;
	}

	public String getReviewImgPath() {
		return reviewImgPath;
	}

	public void setReviewImgPath(String reviewImgPath) {
		this.reviewImgPath = reviewImgPath;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
