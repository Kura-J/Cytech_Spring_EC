package jp.co.sss.cytech.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterForm {
	
	@NotBlank(message = "{register.name.required}")
	private String name;
	
	@NotBlank(message = "{register.email.required}")
	@Email(message = "{register.email.format}")
	private String email;
	
	@NotBlank(message = "{register.password.required}")
	@Size(min = 8, message = "{register.password.size}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{register.password.pattern}")
	private String password;
	
	@NotBlank(message = "{register.passwordConfirm.required}")
	@Size(min = 8, message = "{register.password.size}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{register.password.pattern}")
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
