package com.abcb.customValidators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = EmailValidator.class)

public @interface Email {
	String message() default "{mail.invalidEmail}";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
