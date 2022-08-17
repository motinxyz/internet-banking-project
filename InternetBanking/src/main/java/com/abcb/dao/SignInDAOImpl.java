package com.abcb.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.AccountInfoDTO;
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

	@Override
	public boolean setSession(int user_id, HttpSession session) {

		String sql = "SELECT * FROM internet_banking.user_info WHERE user_id = " + user_id;
		List<SessionDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SessionDTO>(SessionDTO.class));
		SessionDTO sessionInfo = list.get(0);

		if (sessionInfo.getFrozen().equals("yes")) {
			return true;
		}
		session.setAttribute("user_id", user_id);
		session.setAttribute("name", sessionInfo.getName());
		session.setAttribute("father_name", sessionInfo.getFather_name());
		session.setAttribute("mother_name", sessionInfo.getMother_name());
		session.setAttribute("date_of_birth", sessionInfo.getDate_of_birth());
		session.setAttribute("address", sessionInfo.getAddress());
		session.setAttribute("phone_number", sessionInfo.getPhone_number());
		session.setAttribute("email", sessionInfo.getEmail());
		session.setAttribute("permission_type", sessionInfo.getPermission_type());

//		session.setAttribute("frozen", sessionInfo.getFrozen());

		sql = "SELECT * FROM internet_banking.bank_accounts WHERE user_id = ?";
		List<AccountInfoDTO> bankAccounts = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), user_id);

//		if the user is an administrative user ie. admin, manager etc
//		they will not have a bank account with their official email
//		so the bankAccount size will be 0.
//		then there will be no need to set account balance & account number
//		to the session object

		if (bankAccounts.size() != 0) {
			AccountInfoDTO accountInfo = bankAccounts.get(0);

			logger.info("Account Balance is: " + accountInfo.getBalance());
			session.setAttribute("account_number", accountInfo.getAccount_number());

			session.setAttribute("balance", accountInfo.getBalance());
		}
		return false;
	}

	private void logSignIn(int user_id) {
		Object[] args = { user_id, "sign-in", "successful" };
		logActivity(args);
	}

	@Override
	public void logSignOut(HttpSession session) {

		Object[] args = { (int) session.getAttribute("user_id"), "sign-out", "successful" };
		logActivity(args);
	}

	public void logActivity(Object[] args) {
		String sql = "INSERT INTO internet_banking.activity_logs (user_id, activity_type, activity_status) VALUES (?, ?, ?)";

		int rowUpdated = jdbcTemplate.update(sql, args);

		if (rowUpdated == 1) {
			logger.info(args[1] + " activity has been recorded.");
		} else {
			logger.info(args[1] + " activity rocording failed for user " + args[0]);
		}
	}
//
//	@Override
//	public boolean isAccountFrozen(String user_id) {
//		
//		String sql = "SELECT account_number FROM internet_banking.bank_accounts WHERE user_id = ?";
//		
//		List<AccountInfoDTO> accounts = jdbcTemplate.query(sql,
//				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), user_id);
//		AccountInfoDTO accountInfo = accounts.get(0);
//		
//		if(accountInfo.fr)
//		return false;
//	}

}
