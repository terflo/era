package com.era.authserver.model.validators;

import com.era.authserver.model.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UsernameExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.userService.userExistsByUsername(value);
    }
}
