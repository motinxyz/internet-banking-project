package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.abcb.dto.AddTellerDTO;
import com.abcb.dto.UserInfoDTO;

public interface AdminDAO {
	
	List<UserInfoDTO> getEmployeeList();
	boolean validEmployee(int user_id); 
	
	void unFreezeUser(HttpSession session, int user_id, String action);
	UserInfoDTO getDetails(int user_id);
	
	List<UserInfoDTO> getUserList();
	boolean validUser(int user_id); 

	void saveTeller(HttpSession session, AddTellerDTO addTellerDTO);
	
	boolean checkDuplicateTeller(AddTellerDTO addTellerDTO);
	
	void removeEmployee(HttpSession session, int user_id);
}
