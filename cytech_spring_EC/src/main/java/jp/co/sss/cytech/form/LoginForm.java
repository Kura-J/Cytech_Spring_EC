package jp.co.sss.cytech.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginForm {
	
	@NotBlank(message = "{login.email.required}")
	@Email(message = "{login.email.format}")
	private String email;
	
	@NotBlank(message = "{login.password.required}")
	private String password;

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

}
