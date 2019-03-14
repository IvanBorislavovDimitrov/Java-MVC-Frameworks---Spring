package com.ivan.resident_evil.validation.annotations;

import com.ivan.resident_evil.validation.validators.CreatorTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CreatorTypeValidator.class)
public @interface CreatorType {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
