package com.abcb.dto;

public class AccountInfoDTO {

	private int user_id;
	private String account_number;
	private int balance;
	private String internet_banking_activated;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getInternet_banking_activated() {
		return internet_banking_activated;
	}
	public void setInternet_banking_activated(String internet_banking_activated) {
		this.internet_banking_activated = internet_banking_activated;
	}
	
}
