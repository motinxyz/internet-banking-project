package com.abcb.dao;

import javax.servlet.http.HttpSession;

import com.abcb.dto.SignInDTO;

public interface SignInDAO {
	
	boolean authenticate(SignInDTO studentDTO);

	boolean setSession(int user_id, HttpSession session);
	
	void logSignOut(HttpSession session);
	
//	boolean isAccountFrozen(String user_id);
}
