package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.SessionDTO;
import com.abcb.dto.SignInDTO;
import com.abcb.pe.PasswordEncoder;

@Repository
public class SignInDAOImpl implements SignInDAO {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean authenticate(SignInDTO signInDTO) {
		String sql = "SELECT * FROM internet_banking.sign_in_info WHERE email=?";
		List<SignInDTO> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SignInDTO>(SignInDTO.class),
				signInDTO.getEmail());

		SignInDTO storedInfo;

		if (!user.isEmpty()) {
			storedInfo = user.get(0);
		} else {
			return false;
		}

		if (signInDTO.getEmail().equals(storedInfo.getEmail())) {

			String storedPassword = storedInfo.getPassword();
			String enteredPassword = signInDTO.getPassword();

			if (passwordEncoder.matches(enteredPassword, storedPassword)) {

				signInDTO.setUser_id(storedInfo.getUser_id());
				logSignIn(storedInfo.getUser_id());
				logger.info("Successfully Authenticated");
				return true;
			}

		}
		logger.info("Authentication Failed");
		return false;
	}

	private void logSignIn(int user_id) {
		String sql = "INSERT INTO internet_banking.activity_logs (user_id, activity_type, activity_status) VALUES (?, 'sign-in', 'successful')";

		int rowUpdated = jdbcTemplate.update(sql, user_id);

		if (rowUpdated == 1) {
			logger.info("SignIn activity has been recorded.");
		} else {
			logger.info("SignIn activity rocording failed for user " + user_id);
		}
	}

	@Override
	public void setSession(int user_id, HttpSession session) {

		String sql = "SELECT name, phone_number, email, permission_type, frozen FROM internet_banking.user_info WHERE user_id = "
				+ user_id;
		List<SessionDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SessionDTO>(SessionDTO.class));
		SessionDTO sessionInfo = list.get(0);

		session.setAttribute("user_id", user_id);
		session.setAttribute("name", sessionInfo.getName());
		session.setAttribute("email", sessionInfo.getEmail());
		session.setAttribute("phone_number", sessionInfo.getPhone_number());
		session.setAttribute("permission_type", sessionInfo.getPermission_type());
		session.setAttribute("frozen", sessionInfo.getFrozen());
	}

}
