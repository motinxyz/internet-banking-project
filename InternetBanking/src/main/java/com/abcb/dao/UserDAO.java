package com.abcb.dao;

import javax.servlet.http.HttpSession;

import com.abcb.dto.ChangePasswordDTO;

public interface UserDAO {
	boolean checkOldPassword(HttpSession session, ChangePasswordDTO changePasswordDTO);
	boolean confirmNewPassword(HttpSession session, ChangePasswordDTO changePasswordDTO);
	boolean changePassword(HttpSession session, ChangePasswordDTO changePasswordDTO);
}
