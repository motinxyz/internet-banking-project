package com.abcb.dao;

import com.abcb.dto.IBankingRequestDTO;

public interface IBankingRequestDAO {
	
	boolean checkIfphysicalAccountExists(String account_number);	
	boolean checkIfIBankingAccountAlreadyExists(String account_number);	
	boolean isUserAccountFrozen(IBankingRequestDTO userEnteredInfo);
	boolean areEnteredInformationsValid(IBankingRequestDTO userEnteredInfo);
//	boolean requestAlreadyPending();
}
