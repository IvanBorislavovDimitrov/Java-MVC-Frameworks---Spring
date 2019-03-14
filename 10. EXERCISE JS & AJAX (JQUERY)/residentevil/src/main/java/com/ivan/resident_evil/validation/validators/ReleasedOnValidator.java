package com.ivan.resident_evil.validation.validators;

import com.ivan.resident_evil.validation.annotations.ReleasedOn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ReleasedOnValidator implements ConstraintValidator<ReleasedOn, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return value != null && value.after(new Date());
    }
}
