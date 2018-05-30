package cn.cckoo.yu.common.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCard {
    String message() default "{cn.cckoo.yu.idcard.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
