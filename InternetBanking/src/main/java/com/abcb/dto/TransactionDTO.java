package com.abcb.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TransactionDTO {

	@Pattern(regexp = "deposit|withdraw", message = "{transaction.action.invalid}")
	private String action;

	@Min(value = 100, message = "{amount.invalid}")
	@Pattern(regexp = "^\\d\\d\\d+$", message = "{amount.invalid}")
	private String amount;

	private AccountInfoDTO accountInfoDTO;

	private UserInfoDTO userInfoDTO;

	@Size(min = 8, max = 8, message = "{account_number.invalidLength}")
	private String accountNumber;

	public UserInfoDTO getUserInfoDTO() {
		return userInfoDTO;
	}

	public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
		this.userInfoDTO = userInfoDTO;
	}

	public AccountInfoDTO getAccountInfoDTO() {
		return accountInfoDTO;
	}

	public void setAccountInfoDTO(AccountInfoDTO accountInfoDTO) {
		this.accountInfoDTO = accountInfoDTO;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
