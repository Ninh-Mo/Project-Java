package com.vam.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Code {
    String message() default "文字列を入力してください。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {} ;
}
