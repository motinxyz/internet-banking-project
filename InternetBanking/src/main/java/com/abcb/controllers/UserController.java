package com.abcb.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dao.UserDAOImpl;
import com.abcb.dto.LoggedTransactionsDTO;
import com.abcb.dto.UpdatePasswordDTO;

@Controller
public class UserController {

	@Autowired
	UserDAOImpl userDAOImpl;

	@RequestMapping("user-info")
	String getUserInfo(HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		} else {
			return permissionType + "/user-info";
		}
	}

	@RequestMapping("security-info")
	String getUserSecurity(HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		}

		return permissionType + "/security-info";
	}

	@RequestMapping("update-password")
	String updatePassword(@ModelAttribute("updatePasswordDTO") UpdatePasswordDTO updatePasswordDTO,
			HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		}

		return permissionType + "/update-password";
	}

	@RequestMapping("process-update-password-request")
	String processupdatePassword(@Valid @ModelAttribute("updatePasswordDTO") UpdatePasswordDTO updatePasswordDTO,
			BindingResult result, HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		}

		if (result.hasErrors()) {

			return permissionType + "/update-password";
		}

		boolean oldPasswordMatched = userDAOImpl.checkOldPassword(session, updatePasswordDTO.getOldPassword());
		if (!oldPasswordMatched) {

			model.addAttribute("old_password_matched", false);

			return permissionType + "/update-password";
		}

		boolean confirmPasswordMatched = updatePasswordDTO.getNewPassword()
				.equals(updatePasswordDTO.getConfirmPassword());

		if (!confirmPasswordMatched) {

			model.addAttribute("confirm_password_matched", false);
			return permissionType + "/update-password";
		}

		boolean newPasswordIsSameAsOldPassword = updatePasswordDTO.getOldPassword()
				.equals(updatePasswordDTO.getConfirmPassword());

		if (newPasswordIsSameAsOldPassword) {

			model.addAttribute("new_password_is_same_as_old_password", true);
			return permissionType + "/update-password";
		}

		boolean passwordupdated = userDAOImpl.updatePassword(session, updatePasswordDTO.getConfirmPassword());

		if (passwordupdated) {

			model.addAttribute("password_updated_successfully", true);
			return permissionType + "/update-password";
		}

		return permissionType + "/update-password";
	}

	@RequestMapping("transaction-histories")
	String getTransactionHistories(HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		} else if (permissionType.toString().equals("user")) {

			List<LoggedTransactionsDTO> transactionHistories = userDAOImpl
					.getTransactionHistories(session.getAttribute("account_number").toString());
			model.addAttribute("transactionHistories", transactionHistories);
			
			return permissionType+"/transaction-histories";
		}
		
		return permissionType+"/page-not-found";
	}
}
