package com.abcb.dto;

public class SessionDTO {
	private String name, phone_number, email, permission_type, frozen;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermission_type() {
		return permission_type;
	}

	public void setPermission_type(String permission_type) {
		this.permission_type = permission_type;
	}

	public String getFrozen() {
		return frozen;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}
}
