package epsilongtmyon.app.common.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import epsilongtmyon.app.common.validation.validator.MaxLengthValidator;
import epsilongtmyon.app.common.validation.validator.RequiredValidator;

@Constraint(validatedBy = MaxLengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxLength {

	int maxLength();

	String message() default "{epsilongtmyon.app.common.validation.annotation.MaxLength.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Constraint(validatedBy = RequiredValidator.class)
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface List {
		Required[] values();
	}
}
