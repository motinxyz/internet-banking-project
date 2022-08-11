package com.abcb.dao;

import javax.servlet.http.HttpSession;

import com.abcb.dto.SignInDTO;

public interface SignInDAO {
	
	boolean authenticate(SignInDTO studentDTO);

	void setSession(int user_id, HttpSession session);
}
