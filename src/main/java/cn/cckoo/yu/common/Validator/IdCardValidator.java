package cn.cckoo.yu.common.Validator;

import cn.cckoo.yu.common.util.IdCardUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    @Override
    public void initialize(IdCard constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return IdCardUtil.isIdcard(value);
    }
}
