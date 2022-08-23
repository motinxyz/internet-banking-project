package com.abcb.dto;

import javax.validation.constraints.Size;

public class UpdatePasswordDTO {

	@Size(min = 8, max = 100, message = "{password.invalidLength}")
	private String oldPassword;

	@Size(min = 8, max = 100, message = "{password.invalidLength}")
	private String newPassword;

	@Size(min = 8, max = 100, message = "{password.invalidLength}")
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
