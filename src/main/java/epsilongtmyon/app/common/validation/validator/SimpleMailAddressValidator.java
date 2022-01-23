package epsilongtmyon.app.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import epsilongtmyon.app.common.validation.ValidationUtil;
import epsilongtmyon.app.common.validation.annotation.SimpleMailAddress;

public class SimpleMailAddressValidator implements ConstraintValidator<SimpleMailAddress, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValidationUtil.simpleMailAddress(value);
	}

}
