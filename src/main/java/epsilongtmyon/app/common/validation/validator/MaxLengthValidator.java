package epsilongtmyon.app.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import epsilongtmyon.app.common.validation.ValidationUtil;
import epsilongtmyon.app.common.validation.annotation.MaxLength;

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

	int maxLength;

	@Override
	public void initialize(MaxLength constraintAnnotation) {
		maxLength = constraintAnnotation.maxLength();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValidationUtil.maxLength(value, maxLength);
	}

}
