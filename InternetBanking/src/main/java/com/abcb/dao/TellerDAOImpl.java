package com.abcb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.AccountInfoDTO;
import com.abcb.dto.CashStatDTO;
import com.abcb.dto.UserInfoDTO;

@Repository
public class TellerDAOImpl implements TellerDAO {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Object> searchUserToTransact(String account_number) {

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = ?";

		List<AccountInfoDTO> returnedAccountList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class), account_number);

		if (returnedAccountList.isEmpty()) {
			return null;
		}

		AccountInfoDTO accountInfo = returnedAccountList.get(0);

		int user_id = accountInfo.getUser_id();

		sql = "SELECT * FROM internet_banking.user_info WHERE user_id = ?";
		List<UserInfoDTO> returnedUserList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfoDTO>(UserInfoDTO.class), user_id);

		if (returnedUserList.isEmpty()) {
			return null;
		}

		UserInfoDTO userInfo = returnedUserList.get(0);

		List<Object> resultList = new ArrayList<>();

		resultList.add(accountInfo);
		resultList.add(userInfo);

		logger.info("Account & User Info has been returned!");

		return resultList;
	}

	@Override
	public boolean transact(String action, String accountNumber, int amount, HttpSession session) {

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = " + accountNumber;

		List<AccountInfoDTO> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class));
		int balance = list.get(0).getBalance();

		if (action.equals("deposit")) {
			balance += amount;
		} else if (action.equals("withdraw")) {
			balance -= amount;
		}

		sql = "UPDATE internet_banking.bank_accounts SET balance =" + balance + "  WHERE account_number = "
				+ accountNumber;

		int transactionSuccessful = jdbcTemplate.update(sql);

		if (transactionSuccessful == 1) {

			sql = "SELECT * FROM internet_banking.cash_stats";
			List<CashStatDTO> cashStatList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<CashStatDTO>(CashStatDTO.class));
			CashStatDTO cashStat = cashStatList.get(0);
			int cash_reserve = cashStat.getCash_reserve();

			int cash_deposited = cashStat.getCash_deposited();
			int cash_withdrawn = cashStat.getCash_withdrawn();

			if (action.equals("deposit")) {

				sql = "UPDATE internet_banking.cash_stats SET cash_deposited = ?, cash_reserve = ? WHERE (id = 1)";
				Object[] args = { cash_deposited + amount, cash_reserve + amount };

				jdbcTemplate.update(sql, args);

			} else if (action.equals("withdraw")) {

				sql = "UPDATE internet_banking.cash_stats SET cash_withdrawn = ?, cash_reserve = ? WHERE (id = 1)";
				Object[] args = { cash_withdrawn + amount, cash_reserve - amount };

				jdbcTemplate.update(sql, args);
			}

			session.removeAttribute("balance");
			session.setAttribute("balance", balance);

			return true;
		}

		return false;
	}

	@Override
	public boolean validAmount(String action, String accountNumber, int amount) {

		String sql = "SELECT * FROM internet_banking.bank_accounts WHERE account_number = " + accountNumber;

		List<AccountInfoDTO> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AccountInfoDTO>(AccountInfoDTO.class));

		if (!list.isEmpty()) {

			if (action.equals("deposit")) {
				return true;
			} else if (action.equals("withdraw")) {

				if (list.get(0).getBalance() >= amount) {

					logger.info("Amount is valid: " + amount);
					return true;
				}
			}
		}

		logger.info("Amount is invalid: " + amount);
		return false;
	}

	@Override
	public String logTransaction(String tellerId, String accountNumber, String action, int amount, String status) {

		String sql = "INSERT INTO internet_banking.transaction_logs (teller_id, account_number, transaction_type, amount, status) VALUES(?, ?, ?, ?, ?)";
		Object[] args = { tellerId, accountNumber, action, amount, status };

		int loggingSuccessful = jdbcTemplate.update(sql, args);

		/*
		 * if(loggingSuccessful == 1) { sql =
		 * "SELECT transaction_id FROM internet_banking.transaction_logs" }
		 */
		return null;
	}

}
