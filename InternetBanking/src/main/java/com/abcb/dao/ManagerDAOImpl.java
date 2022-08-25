package com.abcb.dao;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcb.dto.CashStatDTO;
import com.abcb.dto.LoggedActivityDTO;
import com.abcb.dto.LoggedTransactionsDTO;

@Repository
public class ManagerDAOImpl implements ManagerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(getClass());

	@Override
	public List<LoggedTransactionsDTO> getTransactions() {

		String sql = "SELECT * FROM internet_banking.transaction_logs ORDER BY transaction_time DESC";
		List<LoggedTransactionsDTO> transactionLogs = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<LoggedTransactionsDTO>(LoggedTransactionsDTO.class));

		return transactionLogs;
	}

	@Override
	public List<LoggedActivityDTO> getActivityLogs() {

		String sql = "SELECT * FROM internet_banking.activity_logs  ORDER BY activity_time DESC";
		List<LoggedActivityDTO> activityLogs = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<LoggedActivityDTO>(LoggedActivityDTO.class));

		return activityLogs;
	}

	@Override
	public CashStatDTO getCashInfo() {
		String sql = "SELECT * FROM internet_banking.cash_stats";
		List<CashStatDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CashStatDTO>(CashStatDTO.class));

		CashStatDTO cashStat = list.get(0);
		return cashStat;
	}

}
