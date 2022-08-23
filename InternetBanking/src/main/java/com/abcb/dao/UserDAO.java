package com.abcb.dao;

import javax.servlet.http.HttpSession;

public interface UserDAO {
	
	boolean checkOldPassword(HttpSession session,  String oldPassword);
	boolean updatePassword(HttpSession session, String newRawPassword);
	
}
