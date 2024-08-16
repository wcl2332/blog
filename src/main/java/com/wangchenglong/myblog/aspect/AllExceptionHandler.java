package com.wangchenglong.myblog.aspect;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.vo.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 20:53
 * @Description: TODO
 */
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> doException(Exception exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("exceptionMessage", exception.getMessage());
        return Result.fail(ErrorCode.Exception_IS_ERROR.getCode(), ErrorCode.Exception_IS_ERROR.getMsg(), errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Result.fail(ErrorCode.Exception_PARAMS_IS_ERROR.getCode(), ErrorCode.Exception_PARAMS_IS_ERROR.getMsg(), errors);
    }
}