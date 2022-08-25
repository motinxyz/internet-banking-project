package com.abcb.dao;

import java.util.List;

import com.abcb.dto.CashStatDTO;
import com.abcb.dto.LoggedActivityDTO;
import com.abcb.dto.LoggedTransactionsDTO;

public interface ManagerDAO {
	
	List<LoggedTransactionsDTO> getTransactions();
	List<LoggedActivityDTO> getActivityLogs();
	CashStatDTO getCashInfo();
}
