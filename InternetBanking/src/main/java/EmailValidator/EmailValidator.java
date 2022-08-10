package EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abcb.customValidators.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null || email == "" || email.matches("^\\.|.*\\.\\..*|.*\\.@.*|.*@\\..*")
				|| !email.matches("[a-zA-z]+[a-zA-z0-9.]+@[a-zA-z0-9.]+\\.[a-zA-z0-9]+")) {
			return false;
		}
		return true;
	}
}
