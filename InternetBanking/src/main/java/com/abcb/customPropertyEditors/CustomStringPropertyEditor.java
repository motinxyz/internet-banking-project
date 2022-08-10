package com.abcb.customPropertyEditors;

import java.beans.PropertyEditorSupport;

public class CustomStringPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim().replaceAll("\n|\t|\b|\r", "");

		text = text.replaceAll(" +", " ");

		setValue(text);
	}
}
