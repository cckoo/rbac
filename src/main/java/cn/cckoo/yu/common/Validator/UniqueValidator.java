package cn.cckoo.yu.common.Validator;

import cn.cckoo.yu.common.util.SpringBeanUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private FieldCheck fieldCheck;

    @Override
    public void initialize(Unique unique) {
        this.fieldCheck = SpringBeanUtil.getBean(unique.fieldCheck());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return fieldCheck.isValueUnique(value);
    }
}
