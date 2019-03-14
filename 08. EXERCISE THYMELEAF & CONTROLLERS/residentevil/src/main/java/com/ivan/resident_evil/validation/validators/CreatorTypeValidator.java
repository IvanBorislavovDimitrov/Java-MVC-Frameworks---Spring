package com.ivan.resident_evil.validation.validators;

import com.ivan.resident_evil.model.enums.Creator;
import com.ivan.resident_evil.validation.annotations.CreatorType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CreatorTypeValidator implements ConstraintValidator<CreatorType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(Creator.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
