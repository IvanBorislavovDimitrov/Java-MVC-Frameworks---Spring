package com.ivan.resident_evil.validation.validators;

import com.ivan.resident_evil.model.enums.Creator;
import com.ivan.resident_evil.model.enums.Mutation;
import com.ivan.resident_evil.validation.annotations.Mutator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MutatorValidator implements ConstraintValidator<Mutator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(Mutation.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
