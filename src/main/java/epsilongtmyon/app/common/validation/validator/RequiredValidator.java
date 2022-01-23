package epsilongtmyon.app.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import epsilongtmyon.app.common.validation.ValidationUtil;
import epsilongtmyon.app.common.validation.annotation.Required;

public class RequiredValidator implements ConstraintValidator<Required, Object>{

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return ValidationUtil.required(value);
	}

}
