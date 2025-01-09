package com.zurich.insurance_management.custom.validate.validations;

import com.zurich.insurance_management.custom.validate.validators.ExpirationDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExpirationDateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExpirationDate {

    String message() default "La fecha de expiración debe ser posterior a la fecha de inicio";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
