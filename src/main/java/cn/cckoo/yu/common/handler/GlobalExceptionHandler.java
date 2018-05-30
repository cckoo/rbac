package cn.cckoo.yu.common.handler;

import cn.cckoo.yu.common.exception.BaseException;
import cn.cckoo.yu.common.exception.ExceptionCode;
import cn.cckoo.yu.common.respond.FailedRespond;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public FailedRespond baseExceptionHandler(BaseException e) {
        return new FailedRespond(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public FailedRespond paramsExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError fieldError:
             fieldErrors) {
            message.append(getFieldErrorMessage(fieldError));
        }

        return new FailedRespond(ExceptionCode.PARAMS_ERROR_CODE, message.toString());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public FailedRespond paramExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            message.append(violation.getMessage());
        }

        return new FailedRespond(ExceptionCode.PARAMS_ERROR_CODE, message.toString());
    }

    private String getFieldErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ", " + fieldError.getDefaultMessage() + ". ";
    }

}
