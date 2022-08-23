package com.abcb.dto;

import javax.validation.constraints.Size;

public class SearchDTO {

	@Size(min = 8, max = 8, message = "{account_number.invalidLength}")
	private String account_number;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

}
