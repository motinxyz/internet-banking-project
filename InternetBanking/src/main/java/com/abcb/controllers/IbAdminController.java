package com.abcb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ibAdmin")
public class IbAdminController {

	@RequestMapping("/")
	String getHome(HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return "visitor/page-not-found";
		}

		else if (permissionType.toString().equals("ibAdmin")) {
			return "ibAdmin/ibAdmin-home";
		}

		else {
			return permissionType + "/page-not-found";
		}

	}
}
