package com.abcb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisitorController {

	private String groupURL = "visitor/";

	@RequestMapping("/")
	String getHomepage() {
		return groupURL + "visitor-home";
	}
}
