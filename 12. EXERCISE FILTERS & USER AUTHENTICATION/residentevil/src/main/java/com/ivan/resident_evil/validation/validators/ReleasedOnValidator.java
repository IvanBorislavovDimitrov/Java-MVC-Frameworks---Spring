package com.ivan.resident_evil.validation.validators;

import com.ivan.resident_evil.validation.annotations.ReleasedOn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReleasedOnValidator implements ConstraintValidator<ReleasedOn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Date date;
        try {
            String[] dateParts = value.split("-");
            date = new GregorianCalendar(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2])).getTime();
        } catch (NumberFormatException e) {
            return false;
        }
        return date.after(new Date());
    }
}
