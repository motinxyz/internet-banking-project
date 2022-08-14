package com.abcb.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {

		if (phone != null && phone.length() == 11 && phone.startsWith("01")) {
			return true;
		}
		return false;
	}

}
