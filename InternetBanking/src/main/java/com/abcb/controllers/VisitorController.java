package com.abcb.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.dto.IBankingRequestDTO;
import com.abcb.util.PermissionTypeFinder;

@Controller
public class VisitorController {

	private String groupURL = "visitor/";

	@Autowired
	PermissionTypeFinder permissionTypeFinder;

	Logger logger = Logger.getLogger(getClass().getName());
	
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

	@RequestMapping("ibanking-request")
	String getiBankingRequestPage(@ModelAttribute("iBankingRequestDTO") IBankingRequestDTO iBankingRequestDTO,
			HttpSession session) {

//		String permissionType = permissionTypeFinder.getPermissionType(session);

		if (session.getAttribute("permission_type") == null) {
			return groupURL + "ibanking-request";
		}

		else {
			String permissionType = session.getAttribute("permission_type").toString();
			return permissionType + "/page-not-found";
		}

	}

	@RequestMapping("submit-ibanking-request")
	String submitiBankingRequest(@Valid @ModelAttribute("iBankingRequestDTO") IBankingRequestDTO iBankingRequestDTO,
			BindingResult result, HttpSession session) {

//		String permissionType = permissionTypeFinder.getPermissionType(session);

		
		if(result.hasErrors()) {
			logger.info("iBanking Request Form has invalid infos");
			
//			return
		}
		
		if (session.getAttribute("permission_type") == null) {
			return groupURL + "ibanking-request";
		}

		else {
			String permissionType = session.getAttribute("permission_type").toString();
			return permissionType + "/page-not-found";
		}

	}
}
