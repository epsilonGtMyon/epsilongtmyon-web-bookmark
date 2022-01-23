package epsilongtmyon.app.common.validation.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import epsilongtmyon.app.common.validation.validator.RequiredValidator;
import epsilongtmyon.app.common.validation.validator.SimpleMailAddressValidator;

@Constraint(validatedBy = SimpleMailAddressValidator.class)
@Documented
@Retention(RUNTIME)
public @interface SimpleMailAddress {

	String message() default "{epsilongtmyon.app.common.validation.annotation.SimpleMailAddress.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Constraint(validatedBy = RequiredValidator.class)
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface List {
		Required[] values();
	}
}
