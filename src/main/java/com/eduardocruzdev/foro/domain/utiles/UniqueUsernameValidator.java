package com.eduardocruzdev.foro.domain.utiles;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface UniqueUsernameValidator {
    String message() default "{Username.AlreadyUsed}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
