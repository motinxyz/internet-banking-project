package com.abcb.dto;

public class LoggedTransactionsDTO {

	private int transaction_id, teller_id;
	private String account_number, transaction_type, amount, status, transaction_time;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getTeller_id() {
		return teller_id;
	}

	public void setTeller_id(int teller_id) {
		this.teller_id = teller_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(String transaction_time) {
		this.transaction_time = transaction_time;
	}

}
