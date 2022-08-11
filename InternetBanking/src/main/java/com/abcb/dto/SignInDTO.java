package com.abcb.dto;

import javax.validation.constraints.Size;

import com.abcb.customValidators.Email;

public class SignInDTO {

	@Email
	private String email;

	@Size(min = 8, max = 100, message = "{password.invalidLength}")
	private String password;

	private int user_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

}
