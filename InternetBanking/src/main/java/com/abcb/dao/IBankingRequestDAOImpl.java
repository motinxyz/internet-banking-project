package com.abcb.dao;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.AccountInfoDTO;
import com.abcb.dto.IBankingRequestDTO;
import com.abcb.dto.UserInfoDTO;

@Repository
public class IBankingRequestDAOImpl implements IBankingRequestDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean checkIfphysicalAccountExists(String account_number) {

		String sql = "SELECT COUNT(*) FROM internet_banking.bank_accounts WHERE account_number = ?";

		int iBankingAccountExists = jdbcTemplate.queryForObject(sql, Integer.class, account_number);

		if (iBankingAccountExists == 1) {
			logger.info("Physical Bank Account Exists!");
			return true;
		}

		logger.info("Physical Bank Account Doesn't Exists!");
		return false;
	}

	@Override
	public boolean checkIfIBankingAccountAlreadyExists(String account_number) {

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";
		List<AccountInfoDTO> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), account_number);

		if (userList.get(0).getInternet_banking_activated().equals("yes")) {
			logger.info("Internet Banking Account Already Exists!");
			return true;
		}

		logger.info("Internet Banking Account doesn't Exists!");
		return false;
	}

	@Override
	public boolean areEnteredInformationsValid(IBankingRequestDTO userEnteredInfo) {

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";
		List<AccountInfoDTO> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), userEnteredInfo.getAccount_number());

		int user_id = userList.get(0).getUser_id();

		sql = "SELECT * FROM internet_banking.user_info WHERE user_id=?";
		List<UserInfoDTO> userInfoList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class), user_id);

		UserInfoDTO storedInfo = userInfoList.get(0);

		if (userInformationsAreValid(userEnteredInfo, storedInfo)) {
			return true;
		}

		return false;
	}

	boolean userInformationsAreValid(IBankingRequestDTO userEnteredInfo, UserInfoDTO storedInfo) {

		if (userEnteredInfo.getEmail().matches(storedInfo.getEmail())) {
			if (userEnteredInfo.getPhone_number().matches(storedInfo.getPhone_number())) {

				logger.info("User informations are valid!");
				return true;
			}
		}

		logger.info("User informations are not valid!");
		return false;
	}

	@Override
	public boolean isUserAccountFrozen(IBankingRequestDTO userEnteredInfo) {

		logger.info("Checking Against the bank account : " + userEnteredInfo.getAccount_number());

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";
		List<AccountInfoDTO> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), userEnteredInfo.getAccount_number());

		logger.info("Size of the extracted userList: " + userList.size());
		int user_id = userList.get(0).getUser_id();

		sql = "SELECT * FROM internet_banking.user_info WHERE user_id=?";
		List<UserInfoDTO> userInfoList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class), user_id);

		UserInfoDTO storedInfo = userInfoList.get(0);

		if (storedInfo.getFrozen().equals("yes")) {
			logger.info("User Account is Frozen!");
			return true;
		}

		logger.info("User Account is not Frozen!");
		return false;
	}

}
