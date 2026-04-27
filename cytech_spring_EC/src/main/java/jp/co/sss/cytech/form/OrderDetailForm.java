package jp.co.sss.cytech.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderDetailForm {
	
	private Integer cartId;
	
	@NotBlank(message = "{order.address.required}")
	private String userAddress;
	
	@NotBlank(message = "{order.apartment.required}")
	private String apartmentName;
	
	@NotBlank(message = "{order.card-number.required}")
	@Pattern(regexp = "^[0-9]{15}$", message = "{order.card-number.digit}")
	private String cardNumber;
	
	@NotBlank(message = "{order.card-limit.required}")
	private String cardLimit;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}

}
