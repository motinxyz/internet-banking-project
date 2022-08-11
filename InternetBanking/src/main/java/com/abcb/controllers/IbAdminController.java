package com.abcb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ibAdmin")
public class IbAdminController {

	@RequestMapping("/")
	String getHome(HttpSession session) {

		if (session.getAttribute("permission_type").toString().equals("ibAdmin")) {
			return "ibAdmin/ibAdmin-home";
		} else {
			return "page-not-found";
		}

	}
}
