package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.SignInDTO;
import com.abcb.pe.BCryptPasswordEncoder;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean checkOldPassword(HttpSession session, String oldPassword) {
		
		int user_id = (int)session.getAttribute("user_id");
		
		String sql = "SELECT * FROM internet_banking.sign_in_info WHERE user_id = ?";
		
		List<SignInDTO> userInfos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SignInDTO>(SignInDTO.class) ,user_id);
		
		if(passwordEncoder.matches(oldPassword, userInfos.get(0).getPassword())) {
			return true;
		}
		
		return false;
	}


	@Override
	public boolean updatePassword(HttpSession session, String newRawPassword) {
		
		int user_id = (int)session.getAttribute("user_id");
		String newEncodedPassword = passwordEncoder.encode(newRawPassword);
		
		String sql = "UPDATE internet_banking.sign_in_info SET password = ? WHERE (user_id = ?)";

		Object[] args = {newEncodedPassword, user_id};
		
		int passwordUpdated = jdbcTemplate.update(sql, args);
		
		if(passwordUpdated == 1) {
			return true;
		}
		return false;
	}

}
