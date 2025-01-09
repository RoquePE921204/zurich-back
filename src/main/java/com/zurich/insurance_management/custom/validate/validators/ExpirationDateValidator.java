package com.zurich.insurance_management.custom.validate.validators;

import com.zurich.insurance_management.custom.validate.validations.ValidExpirationDate;
import com.zurich.insurance_management.requests.InsuranceRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpirationDateValidator implements ConstraintValidator<ValidExpirationDate, InsuranceRequest> {

	@Override
	public boolean isValid(InsuranceRequest value, ConstraintValidatorContext context) {
		if (value == null || value.getStartDate() == null || value.getExpirationDate() == null) {
			return true;
		}
		return value.getExpirationDate().isAfter(value.getStartDate());
	}
}
