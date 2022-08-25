package com.abcb.exception;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(value = Exception.class)
	public String GeneralizedExceptionHandler(HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";
		}

		return permissionType + "/page-not-found";
	}
}
