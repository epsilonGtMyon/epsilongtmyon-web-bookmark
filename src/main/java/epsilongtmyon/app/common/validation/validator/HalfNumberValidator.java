package epsilongtmyon.app.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import epsilongtmyon.app.common.validation.ValidationUtil;
import epsilongtmyon.app.common.validation.annotation.HalfNumber;

public class HalfNumberValidator implements ConstraintValidator<HalfNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValidationUtil.halfNumber(value);
	}

}
