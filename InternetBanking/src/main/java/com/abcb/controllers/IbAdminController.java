package com.abcb.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dao.IBankingRequestDAOImpl;
import com.abcb.dto.IBankingRequestDTO;

@Controller
//@RequestMapping("ibAdmin")
public class IbAdminController {

	String groupURL = "ibAdmin";

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IBankingRequestDAOImpl iBankingRequestDAO;

	@RequestMapping("pending-ibanking-requests")
	String getPendingIBankingRequests(HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().equals("ibAdmin")) {

			List<IBankingRequestDTO> requestsList = iBankingRequestDAO.getPendingIBankingRequests();

//			logger.info("Sample request: " + requestsList.get(0).toString());

			model.addAttribute("requestsList", requestsList);

			return permissionType + "/pending-ibanking-requests";
		} else {
			return permissionType + "/page-not-found";
		}
	}

	@RequestMapping("view-request-details/{account_number}")

	String getRequestDetailsPage(@PathVariable(value = "account_number") String account_number, HttpSession session,
			Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().equals("ibAdmin")) {

//			Check if the account number received from the URL, has a pending internet
//			banking request
			boolean validAccountNumber = iBankingRequestDAO.requestExists(account_number);

			if (validAccountNumber == true) {

				IBankingRequestDTO receivedInfo = iBankingRequestDAO
						.getPendingIBankingRequestsByAccountNumber(account_number);
				IBankingRequestDTO storedInfo = iBankingRequestDAO.getStoredInfoToVerifyRequest(account_number);

//				logger.info("Sample request: " + requestsList.get(0).toString());

				model.addAttribute("receivedInfo", receivedInfo);
				model.addAttribute("storedInfo", storedInfo);

				return permissionType + "/view-request-details";

			} else {
//				If the account number received from the URL
//				is invalid
				return permissionType + "/page-not-found";
			}

		} else {

			return permissionType + "/page-not-found";
		}
	}

	@RequestMapping("ibanking-request-response/{account_number}/{response}")
	String respondToIBankingRequest(@PathVariable("account_number") String account_number,
			@PathVariable("response") String response, HttpSession session, Model model) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {

			return "visitor/page-not-found";

		} else if (permission_type.toString().equals("ibAdmin")) {

			boolean validAccountNumber = iBankingRequestDAO.requestExists(account_number);

			if (validAccountNumber) {

				logger.info("Response message is: " + response);

				if (response.equals("reject")) {

					logger.info("Response message passed Check == reject");
					IBankingRequestDTO user = iBankingRequestDAO.rejectIBankingRequest(account_number);

					if (user == null) {

//						if requestDTO is null that means the deletion operation has
//						failed, reject/acceptIBankingRequest method only
//						returns the object, if the operation has been successful

						return permission_type + "/page-not-found";
					}

					model.addAttribute("requestStatus", "Rejected");
					model.addAttribute("user", user);

					return permission_type + "/ibanking-request-response";

				} else if (response.equals("activate")) {

					logger.info("Response message passed Check == activate");
					IBankingRequestDTO user = iBankingRequestDAO.acceptIBankingRequest(account_number);

					if (user == null) {

//						if requestDTO is null that means the deletion operation has
//						failed, reject/acceptIBankingRequest method only
//						returns the object, if the operation has been successful

						return permission_type + "/page-not-found";
					}

					model.addAttribute("requestStatus", "Accepted");
					model.addAttribute("user", user);

					return permission_type + "/ibanking-request-response";
				}
			}
		}

		return permission_type + "/page-not-found";
	}

}
