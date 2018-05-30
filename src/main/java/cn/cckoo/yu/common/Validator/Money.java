package cn.cckoo.yu.common.Validator;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { })
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
@NotEmpty
@Pattern(regexp = "^(([1-9]\\d*)|([0]{1}))(\\.(\\d){0,2})?$")
public @interface Money {
    String message() default "{cn.cckoo.yu.money.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
