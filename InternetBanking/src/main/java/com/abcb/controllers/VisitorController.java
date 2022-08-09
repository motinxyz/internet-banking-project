package com.abcb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisitorController {

	@RequestMapping("/")
	String getHomepage() {
		return "index";
	}
}
