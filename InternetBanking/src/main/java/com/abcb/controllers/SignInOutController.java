package com.abcb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.abcb.dao.SignInDAOImpl;
import com.abcb.dto.SignInDTO;
import com.abcb.util.PermissionTypeFinder;

@Controller
public class SignInOutController {

	Logger logger = Logger.getLogger(getClass().getName());
	private String groupURL = "visitor/";

	@Autowired
	PermissionTypeFinder permissionTypeFinder;

	@Autowired
	SignInDAOImpl signInDAO;

	@RequestMapping("sign-in")
	String signInPage(@ModelAttribute("signInDTO") SignInDTO signInDTO, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {

		Object permissionType = session.getAttribute("permission_type");
		logger.info("SignInController-> SignIn -> Permission Type: " + permissionType);

		if (permissionType == null) {
			return groupURL + "sign-in";
		} else {

			return permissionType + "/page-not-found";
		}
	}

	@RequestMapping("authenticate")
	String authenticate(@Valid @ModelAttribute("signInDTO") SignInDTO signInDTO, BindingResult result,
			HttpSession session, HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException {

		Object permissionType = session.getAttribute("permission_type");
		logger.info("SignInController -> Authenticate -> Permission Type: " + permissionType);

		if (permissionType == null) {

			if (result.hasErrors()) {
				logger.info("binding result has error: " + result.hasErrors());
				return groupURL + "sign-in";
			}

			boolean authenticated = signInDAO.authenticate(signInDTO);

			if (authenticated) {

				boolean account_frozen = signInDAO.setSession(signInDTO.getUser_id(), session);

				if (account_frozen) {
					request.setAttribute("account_frozen", account_frozen);

					return groupURL + "sign-in";
				}

				if (session.getAttribute("permission_type") == null) {
					logger.info("Session set failed");
				} else {
					logger.info("Session set successful");
				}

//				permissionType = session.getAttribute("permission_type").toString();

				permissionType = session.getAttribute("permission_type");
				response.sendRedirect("/InternetBanking/");
				return permissionType + "/" + permissionType + "-home";

//			logger.info("Request Forward to home was unsuccessful");
			}

			else {
				request.setAttribute("validUser", "false");
				return groupURL + "sign-in";
			}

		} else {

			return permissionType + "/page-not-found";
		}
	}

	@RequestMapping("sign-out")
	void signOut(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {
			response.sendRedirect("page-not-found");
		} else {
			signInDAO.logSignOut(session);
			session.invalidate();
//		return "visitor/visitor-home";
//		RequestDispatcher rd = request.getRequestDispatcher("/");
//		rd.forward(request, response);
			response.sendRedirect("/InternetBanking/");
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomStringPropertyEditor editor = new CustomStringPropertyEditor();

//		Removing all the white spaces
		binder.registerCustomEditor(String.class, "email", editor);
		binder.registerCustomEditor(String.class, "password", editor);
	}
}
