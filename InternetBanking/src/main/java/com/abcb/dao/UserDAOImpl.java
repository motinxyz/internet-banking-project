package com.abcb.dao;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.abcb.dto.ChangePasswordDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public boolean checkOldPassword(HttpSession session, ChangePasswordDTO changePasswordDTO) {
		
		Object oldPassword = session.
		return false;
	}

	@Override
	public boolean confirmNewPassword(HttpSession sessionm, ChangePasswordDTO changePasswordDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(HttpSession session, ChangePasswordDTO changePasswordDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
