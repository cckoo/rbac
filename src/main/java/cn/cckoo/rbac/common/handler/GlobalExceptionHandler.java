package cn.cckoo.rbac.common.handler;

import cn.cckoo.rbac.common.exception.BaseException;
import cn.cckoo.rbac.common.exception.ExceptionCode;
import cn.cckoo.rbac.common.respond.FailedRespond;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    private String getFieldErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ", " + fieldError.getDefaultMessage() + ". ";
    }

}
