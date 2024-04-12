package com.wangchenglong.myblog.aspect;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 20:53
 * @Description: TODO
 */
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception exception) {
        exception.printStackTrace();
        return Result.fail(ErrorCode.Exception_IS_ERROR.getCode(), ErrorCode.Exception_IS_ERROR.getMsg());
    }
}