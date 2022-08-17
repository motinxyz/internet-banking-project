package com.abcb.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dto.ChangePasswordDTO;

@Controller
public class UserController {

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

	@RequestMapping("change-password")
	String changePassword(@ModelAttribute("changePasswordDTO") ChangePasswordDTO changePasswordDTO,
			HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		}

		return permissionType + "/change-password";
	}

	@RequestMapping("process-change-password-request")
	String processChangePassword(@Valid @ModelAttribute("changePasswordDTO") ChangePasswordDTO changePasswordDTO,
			BindingResult result, HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		}

		return permissionType + "/change-password";
	}
}
