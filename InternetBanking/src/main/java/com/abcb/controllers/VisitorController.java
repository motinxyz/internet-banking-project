package com.abcb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.util.PermissionTypeFinder;

@Controller
public class VisitorController {

	private String groupURL = "visitor/";

	@Autowired
	PermissionTypeFinder permissionTypeFinder;

	@RequestMapping("/")
	String getHomepage(HttpSession session) {

//		String permissionType = permissionTypeFinder.getPermissionType(session);

		if (session.getAttribute("permission_type") == null) {
			return groupURL + "visitor-home";
		}

		else {
			String permissionType = session.getAttribute("permission_type").toString();
			return permissionType + "/" + permissionType + "-home";
		}
	}
}
