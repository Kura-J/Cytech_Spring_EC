package jp.co.sss.cytech.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserForm {
	
	@NotBlank(message = "{user.name.required}")
	private String name;
	
	@NotBlank(message = "{user.email.required}")
	@Email(message = "{user.email.format}")
	private String email;
	
	@NotBlank(message = "{user.password.required}")
	@Size(min = 8, message = "{user.password.size}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{user.password.pattern}")
	private String password;
	
	@NotBlank(message = "{user.passwordConfirm.required}")
	@Size(min = 8, message = "{user.password.size}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{user.password.pattern}")
	private String passwordConfirm;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
