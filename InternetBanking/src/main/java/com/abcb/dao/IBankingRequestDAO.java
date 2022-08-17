package com.abcb.dao;

import java.util.List;

import com.abcb.dto.IBankingRequestDTO;

public interface IBankingRequestDAO {
	
	boolean checkIfphysicalAccountExists(String account_number);	
	boolean checkIfIBankingAccountAlreadyExists(String account_number);	
	boolean isUserAccountFrozen(IBankingRequestDTO userEnteredInfo);
	boolean areEnteredInformationsValid(IBankingRequestDTO userEnteredInfo);
	boolean requestAlreadyPending(String account_number);
	
	boolean entryIBankingRequest(IBankingRequestDTO iBankingRequestDTO);
	List<IBankingRequestDTO> getPendingIBankingRequests();
	
	IBankingRequestDTO getPendingIBankingRequestsByAccountNumber(String account_number);
	IBankingRequestDTO getStoredInfoToVerifyRequest(String account_number);
	boolean requestExists(String account_number);
	
	IBankingRequestDTO rejectIBankingRequest(String account_number);
	IBankingRequestDTO acceptIBankingRequest(String account_number);
}
