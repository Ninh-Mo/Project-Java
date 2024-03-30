package com.vam.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodeValidator implements ConstraintValidator<Code, String> {

    @Override
    public void initialize(Code constraintAnnotation) {
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext ctx) {
        return !code.equals("　");
    }
}
