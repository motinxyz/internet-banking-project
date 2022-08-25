package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.AddTellerDTO;
import com.abcb.dto.SignInDTO;
import com.abcb.dto.UserInfoDTO;
import com.abcb.pe.BCryptPasswordEncoder;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	Logger logger = Logger.getLogger(getClass());

	@Override
	public List<UserInfoDTO> getEmployeeList() {

		String sql = "SELECT * FROM internet_banking.user_info WHERE permission_type = 'manager' OR permission_type = 'admin' OR permission_type = 'ibAdmin' OR permission_type = 'teller'";

		List<UserInfoDTO> employeeList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class));

		return employeeList;
	}

	@Override
	public boolean validEmployee(int user_id) {

		String sql = "SELECT COUNT(*) FROM internet_banking.user_info WHERE user_id = ? AND (permission_type = 'manager' OR permission_type = 'admin' OR permission_type = 'ibAdmin' OR permission_type = 'teller')";

		int valid = jdbcTemplate.queryForObject(sql, Integer.class, user_id);
		if (valid == 1) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfoDTO getDetails(int user_id) {

		String sql = "SELECT * FROM internet_banking.user_info WHERE user_id = ?";

		List<UserInfoDTO> userInfoList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class), user_id);

		return userInfoList.get(0);
	}

	@Override
	public void unFreezeUser(HttpSession session, int user_id, String action) {

		String actionValueMap;
		if (action.equals("freeze")) {
			actionValueMap = "yes";
		} else {
			actionValueMap = "no";
		}
		String sql = "UPDATE internet_banking.user_info SET frozen = '" + actionValueMap + "' WHERE (user_id = "
				+ user_id + ")";
		jdbcTemplate.update(sql);

		logFreezeUnfreeze(session, user_id, action);
	}

	public void logFreezeUnfreeze(HttpSession session, int user_id, String action) {

		String sql = "INSERT INTO internet_banking.activity_logs (user_id,activity_type, activity_status) VALUES ("
				+ session.getAttribute("user_id") + ",'" + action + " : user id: " + user_id + "' , 'successful')";
		jdbcTemplate.update(sql);

	}

	@Override
	public List<UserInfoDTO> getUserList() {

		String sql = "SELECT * FROM internet_banking.user_info WHERE permission_type = 'user'";

		List<UserInfoDTO> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class));

		return userList;
	}

	@Override
	public boolean validUser(int user_id) {

		String sql = "SELECT COUNT(*) FROM internet_banking.user_info WHERE user_id = ? AND permission_type = 'user'";

		int valid = jdbcTemplate.queryForObject(sql, Integer.class, user_id);
		if (valid == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void saveTeller(HttpSession session, AddTellerDTO addTellerDTO) {

		String sql = "INSERT INTO internet_banking.user_info (name, father_name, mother_name, date_of_birth, address, phone_number, email, permission_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = { addTellerDTO.getName(), addTellerDTO.getFather_name(), addTellerDTO.getMother_name(),
				addTellerDTO.getDate_of_birth(), addTellerDTO.getAddress(), addTellerDTO.getPhone_number(),
				addTellerDTO.getEmail(), addTellerDTO.getPermission_type() };

		jdbcTemplate.update(sql, args);

//		extract generated user_id
		sql = "SELECT user_id FROM internet_banking.user_info WHERE email = '" + addTellerDTO.getEmail()
				+ "' AND phone_number = '" + addTellerDTO.getPhone_number() + "' AND permission_type = '"
				+ addTellerDTO.getPermission_type() + "'";
		List<SignInDTO> userIdList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SignInDTO>(SignInDTO.class));
		int user_id = userIdList.get(0).getUser_id();

//		encode password
		String encodedPassword = passwordEncoder.encode(addTellerDTO.getPassword());

//		update sign-in info
		sql = "INSERT INTO internet_banking.sign_in_info VALUES(?,?,?)";
		Object[] signInArgs = { user_id, addTellerDTO.getEmail(), encodedPassword };
		jdbcTemplate.update(sql, signInArgs);

//		log the activity
		sql = "INSERT INTO internet_banking.activity_logs (user_id, activity_type, activity_status) VALUES (?,?,?)";
		Object[] activityArgs = { (int) session.getAttribute("user_id"), "add-teller: teller id: " + user_id,
				"successful" };
		jdbcTemplate.update(sql, activityArgs);

	}

	@Override
	public boolean checkDuplicateTeller(AddTellerDTO addTellerDTO) {

		String sql = "SELECT COUNT(*) FROM internet_banking.user_info WHERE email = '" + addTellerDTO.getEmail()
				+ "' AND phone_number = '" + addTellerDTO.getPhone_number() + "'";
		int duplicateExists = jdbcTemplate.queryForObject(sql, Integer.class);

		if (duplicateExists != 0) {

			return true;
		}

		return false;
	}

	@Override
	public void removeEmployee(HttpSession session, int user_id) {

//		remove user info
		String sql = "DELETE FROM internet_banking.user_info WHERE user_id = " + user_id;
		jdbcTemplate.update(sql);

//		remove sign-in info
		sql = "DELETE FROM internet_banking.sign_in_info WHERE user_id = " + user_id;
		jdbcTemplate.update(sql);

//		log the activity
		sql = "INSERT INTO internet_banking.activity_logs (user_id, activity_type, activity_status) VALUES (?,?,?)";
		Object[] activityArgs = { (int) session.getAttribute("user_id"), "remove-teller: teller id: " + user_id,
				"successful" };
		jdbcTemplate.update(sql, activityArgs);

	}
}
