package epsilongtmyon.app.common.validation.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import epsilongtmyon.app.common.validation.validator.HalfAlphaNumberValidator;
import epsilongtmyon.app.common.validation.validator.RequiredValidator;

@Constraint(validatedBy = HalfAlphaNumberValidator.class)
@Documented
@Retention(RUNTIME)
public @interface HalfAlphaNumber {

	String message() default "{epsilongtmyon.app.common.validation.annotation.HalfAlphaNumber.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Constraint(validatedBy = RequiredValidator.class)
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface List {
		Required[] values();
	}
}
