package com.legacy.lms.translation;

import com.legacy.lms.util.localization.Tokens;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings("ALL")
@Documented
@Constraint(validatedBy = TranslationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Translation {
    String message() default Tokens.V_TRANSLATION;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 255;

    boolean required() default true;
}
