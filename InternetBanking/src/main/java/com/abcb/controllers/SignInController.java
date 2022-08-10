package com.abcb.controllers;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.customPropertyEditors.CustomStringPropertyEditor;
import com.abcb.dto.SignInDTO;

@Controller
public class SignInController {

	Logger logger = Logger.getLogger(getClass().getName());
	private String groupURL = "visitor/";

	@RequestMapping("sign-in")
	String signInPage(@ModelAttribute("signInDTO") SignInDTO signInDTO) {
		return groupURL + "sign-in";
	}

	@RequestMapping("authenticate")
	String authenticate(@Valid @ModelAttribute("signInDTO") SignInDTO signInDTO, BindingResult result) {

		logger.info(result.hasErrors());
		if (result.hasErrors()) {
			return groupURL + "sign-in";
		}

		return "";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomStringPropertyEditor editor = new CustomStringPropertyEditor();

//		Removing all the white spaces
		binder.registerCustomEditor(String.class, "email", editor);
		binder.registerCustomEditor(String.class, "password", editor);
	}
}
