package epsilongtmyon.app.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import epsilongtmyon.app.common.validation.ValidationUtil;
import epsilongtmyon.app.common.validation.annotation.HalfAlphaNumber;

public class HalfAlphaNumberValidator implements ConstraintValidator<HalfAlphaNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValidationUtil.halfAlphaNumber(value);
	}

}
