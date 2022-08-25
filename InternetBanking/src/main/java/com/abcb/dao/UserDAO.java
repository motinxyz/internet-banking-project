package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.abcb.dto.LoggedTransactionsDTO;

public interface UserDAO {
	
	boolean checkOldPassword(HttpSession session,  String oldPassword);
	boolean updatePassword(HttpSession session, String newRawPassword);
	List<LoggedTransactionsDTO> getTransactionHistories(String account_number);
}
