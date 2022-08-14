package com.abcb.dto;

import javax.validation.constraints.Size;

import com.abcb.customValidators.Email;
import com.abcb.customValidators.Phone;

public class IBankingRequestDTO {

	@Size(min = 4, max = 100, message = "{name.invalidLength}")
	private String name;

	@Size(min = 4, max = 100, message = "{name.invalidLength}")
	private String father_name;

	@Size(min = 4, max = 100, message = "{name.invalidLength}")
	private String mother_name;

	@Size(min = 8, max = 200, message = "{address.invalidLength}")
	private String address;

	@Size(min = 8, max = 8, message = "{account_number.invalidLength}")
	private String account_number;

	@Email
	private String email;

	@Phone
	private String phone_number;

	@Size(min = 8, message = "{password.invalidLength}")
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
