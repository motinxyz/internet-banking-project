package com.abcb.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dao.ManagerDAOImpl;
import com.abcb.dto.CashStatDTO;
import com.abcb.dto.LoggedActivityDTO;
import com.abcb.dto.LoggedTransactionsDTO;

@Controller
public class ManagerController {

	@Autowired
	ManagerDAOImpl managerDAO;

	@RequestMapping("transaction-logs")
	String getTransactionLogs(HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		} else if (permissionType.toString().equals("manager")) {

			List<LoggedTransactionsDTO> transactionLogs = managerDAO.getTransactions();
			model.addAttribute("transactionLogs", transactionLogs);
			return permissionType + "/transaction-logs";
		}

		return permissionType + "/page-not-found";

	}

	@RequestMapping("activity-logs")
	String getActivityLogs(HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		} else if (permissionType.toString().equals("manager")) {

			List<LoggedActivityDTO> activityLogs = managerDAO.getActivityLogs();
			model.addAttribute("activityLogs", activityLogs);
			return permissionType + "/activity-logs";
		}

		return permissionType + "/page-not-found";

	}
	
	@RequestMapping("cash-reserve")
	String getCashInfo(HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		} else if (permissionType.toString().equals("manager")) {
			
			CashStatDTO cashStat = managerDAO.getCashInfo();
			model.addAttribute("cashStat",cashStat);
			return permissionType + "/manager-home";
		}
		
		return permissionType+"/page-not-found";
	}

}
