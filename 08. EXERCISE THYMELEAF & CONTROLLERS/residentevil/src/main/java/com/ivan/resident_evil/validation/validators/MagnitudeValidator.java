package com.ivan.resident_evil.validation.validators;

import com.ivan.resident_evil.model.enums.Magnitude;
import com.ivan.resident_evil.validation.annotations.MagnitudeAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MagnitudeValidator implements ConstraintValidator<MagnitudeAnnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(Magnitude.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
