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
import com.abcb.pe.BCryptPasswordEncoder;
import com.abcb.rse.PendingRequestsRSE;
import com.abcb.rse.UserInfoRSE;

@Repository
public class IBankingRequestDAOImpl implements IBankingRequestDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

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

	@Override
	public boolean requestAlreadyPending(String account_number) {

		String sql = "SELECT COUNT(*) FROM internet_banking.ibanking_requests WHERE account_number = ?";

		int iBankingAccountExists = jdbcTemplate.queryForObject(sql, Integer.class, account_number);

		if (iBankingAccountExists == 1) {
			logger.info("Already a request exists!");
			return true;
		}

		logger.info("There is no pending request!");
		return false;
	}

	@Override
	public boolean entryIBankingRequest(IBankingRequestDTO iBankingRequestDTO) {

		logger.info("Checking Against the bank account : " + iBankingRequestDTO.getAccount_number());

//		find the user_id
		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";
		List<AccountInfoDTO> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class),
				iBankingRequestDTO.getAccount_number());

		logger.info("Size of the extracted userList: " + userList.size());
		int user_id = userList.get(0).getUser_id();

//		enter the date into ibanking request table
		sql = "INSERT INTO internet_banking.ibanking_requests VALUES(?,?,?,?,?,?,?,?)";

		String encodedPassword = passwordEncoder.encode(iBankingRequestDTO.getPassword());

		Object[] args = { user_id, iBankingRequestDTO.getAccount_number(), iBankingRequestDTO.getName(),
				iBankingRequestDTO.getFather_name(), iBankingRequestDTO.getMother_name(),
				iBankingRequestDTO.getAddress(), iBankingRequestDTO.getPhone_number(), encodedPassword };

		int rowAffected = jdbcTemplate.update(sql, args);
		logger.info("IBanking Request-> #Record entered: " + rowAffected);

		if (rowAffected == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<IBankingRequestDTO> getPendingIBankingRequests() {

		String sql = "SELECT * FROM internet_banking.ibanking_requests";
		List<IBankingRequestDTO> requestsList = jdbcTemplate.query(sql, new PendingRequestsRSE());
		return requestsList;
	}

	@Override
	public IBankingRequestDTO getStoredInfoToVerifyRequest(String account_number) {

		// find the user_id
		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";
		List<AccountInfoDTO> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), account_number);

		int user_id = userList.get(0).getUser_id();

		sql = "SELECT * FROM internet_banking.user_info WHERE user_id = ?";
		List<IBankingRequestDTO> usersList = jdbcTemplate.query(sql, new UserInfoRSE(), user_id);

		return usersList.get(0);
	}

	@Override
	public IBankingRequestDTO getPendingIBankingRequestsByAccountNumber(String account_number) {

		String sql = "SELECT * FROM internet_banking.ibanking_requests WHERE account_number = ?";
		List<IBankingRequestDTO> requestsList = jdbcTemplate.query(sql, new PendingRequestsRSE(), account_number);

		return requestsList.get(0);

	}

	@Override
	public boolean requestExists(String account_number) {

		String sql = "SELECT COUNT(*) FROM internet_banking.ibanking_requests WHERE account_number = ?";

		int iBankingAccountExists = jdbcTemplate.queryForObject(sql, Integer.class, account_number);

		if (iBankingAccountExists == 1) {
			logger.info("IBankingRequestDAO-> requestExists-> It's a valid account number");
			return true;
		}

		logger.info("IBankingRequestDAO-> requestExists-> It's an invalid account number");
		return false;
	}

}
