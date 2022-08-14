package com.abcb.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.customPropertyEditors.CustomStringPropertyEditor;
import com.abcb.customPropertyEditors.PhonePropertyEditor;
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
		Object permissionType = session.getAttribute("permission_type");
		logger.info("HomeController -> getHomepage -> Permission Type: " + permissionType);

		if (session.getAttribute("permission_type") == null) {
			return groupURL + "visitor-home";
		}

		else {

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
		if (session.getAttribute("permission_type") == null) {

			if (result.hasErrors()) {

				logger.info("iBanking Request Form has invalid infos");
				return groupURL + "/ibanking-request";

			} else {
				return "";
			}
		}

		else {
			Object permissionType = session.getAttribute("permission_type");
			return permissionType + "/page-not-found";
		}

	}

	@RequestMapping("page-not-found")
	String getPageNotFoundPage(HttpSession session) {
		Object permissionType = session.getAttribute("permission_type");

		if (permissionType != null) {
			return permissionType + "/page-not-found";
		}

		else {
			return groupURL + "/page-not-found";
		}
	}

	@RequestMapping("contact-us")
	String getContactUsPage(HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			return groupURL + "/contact-us";
		} else {
			return permissionType + "/contact-us";
		}
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		CustomStringPropertyEditor editor = new CustomStringPropertyEditor();

//		Removing all the white spaces
		binder.registerCustomEditor(String.class, "email", editor);
		binder.registerCustomEditor(String.class, "phone_number", editor);
		binder.registerCustomEditor(String.class, "password", editor);
		binder.registerCustomEditor(String.class, "name", editor);
		binder.registerCustomEditor(String.class, "father_name", editor);
		binder.registerCustomEditor(String.class, "mother_name", editor);
		binder.registerCustomEditor(String.class, "address", editor);
		binder.registerCustomEditor(String.class, "account_number", editor);

		PhonePropertyEditor phoneEditor = new PhonePropertyEditor();

//		Removing country code
		binder.registerCustomEditor(String.class, "phone_number", phoneEditor);
	}
}
