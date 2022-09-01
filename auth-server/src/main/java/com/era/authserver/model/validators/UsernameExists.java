package com.era.authserver.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UsernameExistsValidator.class)
@Documented
public @interface UsernameExists {

    String message() default "Имя пользователя уже существует";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
