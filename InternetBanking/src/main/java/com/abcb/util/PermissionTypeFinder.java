package com.abcb.util;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class PermissionTypeFinder {

	public String getPermissionType(HttpSession session) {

		if (session.getAttribute("permissionType") == null) {
			return null;
		} else {
			return session.getAttribute("permissionType").toString();
		}

	}
}
