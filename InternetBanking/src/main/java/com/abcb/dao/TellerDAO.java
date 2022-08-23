package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface TellerDAO {

	List<Object> searchUserToTransact(String account_number);

	boolean transact(String action, String accountNumber, int amount, HttpSession sessiono);

	boolean validAmount(String action, String accountNumber, int amount);

	String logTransaction(String tellerId, String accountNumber, String action, int amount, String status);
}
