package com.abcb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dao.TellerDAOImpl;
import com.abcb.dto.AccountInfoDTO;
import com.abcb.dto.SearchDTO;
import com.abcb.dto.TransactionDTO;
import com.abcb.dto.UserInfoDTO;

@Controller
public class TellerController {

	@Autowired
	TellerDAOImpl tellerDAO;

	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("transact")
	String getTransactionPage(@ModelAttribute("searchDTO") SearchDTO searchDTO, HttpSession session) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("teller")) {

			return permission_type + "/transact";
		}

		return permission_type + "/page-not-found";

	}

	@RequestMapping("search-accounts-for-transacting")
	String getUserInfoForTransacting(@Valid @ModelAttribute("searchDTO") SearchDTO searchDTO, BindingResult result,
			HttpSession session, HttpServletRequest request,
			@ModelAttribute("transactionDTO") TransactionDTO transactionDTO, Model model) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("teller")) {

			if (result.hasErrors()) {

				return permission_type + "/transact";
			}

			List<Object> resultList = tellerDAO.searchUserToTransact(searchDTO.getAccount_number());

			if (resultList == null) {

				request.setAttribute("registered_user", false);
				return permission_type + "/transact";
			}
			AccountInfoDTO accountInfo = (AccountInfoDTO) resultList.get(0);
			UserInfoDTO userInfo = (UserInfoDTO) resultList.get(1);

			if (userInfo.getFrozen().equals("yes")) {

				request.setAttribute("account_is_frozen", true);
				return permission_type + "/transact";
			}
			model.addAttribute("account_info", accountInfo);
			model.addAttribute("user_info", userInfo);

			return permission_type + "/proceed-to-transact";

		}

		return permission_type + "/page-not-found";
	}

	@RequestMapping("proceed-to-transact")
	String processTransaction(@Valid @ModelAttribute("transactionDTO") TransactionDTO transactionDTO,
			BindingResult result, HttpSession session, Model model) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("teller")) {

			logger.info("Account Number Check: " + transactionDTO.getAccountNumber());

			if (result.hasErrors()) {
				return permission_type + "/proceed-to-transact";
			}

			logger.info("Teller Controller->process-trandaction>account number: " + transactionDTO.getAccountNumber());

			boolean validAmount = tellerDAO.validAmount(transactionDTO.getAction(), transactionDTO.getAccountNumber(),
					Integer.parseInt(transactionDTO.getAmount()));
			if (validAmount) {

				boolean transactionSuccessful = tellerDAO.transact(transactionDTO.getAction(),
						transactionDTO.getAccountNumber(), Integer.parseInt(transactionDTO.getAmount()), session);

				if (transactionSuccessful) {
					model.addAttribute("transactionSuccessful", true);

					tellerDAO.logTransaction(session.getAttribute("user_id").toString(),
							transactionDTO.getAccountNumber(), transactionDTO.getAction(),
							Integer.parseInt(transactionDTO.getAmount()), "successful");

					return permission_type + "/proceed-to-transact";

				}

				model.addAttribute("transactionSuccessful", false);
				return permission_type + "/proceed-to-transact";
			}

			model.addAttribute("validAmount", false);
			return permission_type + "/proceed-to-transact";
		}

		return permission_type + "/page-not-found";
	}
}
