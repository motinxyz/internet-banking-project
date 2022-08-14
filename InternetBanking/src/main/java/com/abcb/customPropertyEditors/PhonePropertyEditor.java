package com.abcb.customPropertyEditors;

import java.beans.PropertyEditorSupport;

public class PhonePropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String phone) throws IllegalArgumentException {

		phone = phone.replaceAll("\\+88", "");

		if(phone.startsWith("88")) {
			phone = phone.substring(2);
		}
		
		setValue(phone);
	}
}
