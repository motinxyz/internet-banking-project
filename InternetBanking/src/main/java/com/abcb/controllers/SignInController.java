package com.abcb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {

	private String groupURL = "visitor/";

	@RequestMapping("sign-in")
	String signInPage() {
		return groupURL + "sign-in";
	}
}
