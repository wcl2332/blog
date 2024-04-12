package com.wangchenglong.myblog.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.UserService;
import com.wangchenglong.myblog.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 21:05
 * @Description: TODO
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //判断 token 是否为空
        String token = request.getHeader("token");
        System.out.println("token>>>" + token);
        if (token == null || "".equals(token)) {
            Result result = Result.fail(ErrorCode.Token_IS_ERROR.getCode(), ErrorCode.Token_IS_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
            return false;
        }
        // 判断 token 是否有效
        try {
            DecodedJWT decodedJWT = JWTUtils.verify(token);
        } catch (TokenExpiredException e) {
            Result result = Result.fail(ErrorCode.Token_IS_ERROR.getCode(), ErrorCode.Token_IS_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
            return false;
        } catch (Exception e) {
            Result result = Result.fail(ErrorCode.Token_IS_ERROR.getCode(), ErrorCode.Token_IS_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
            return false;
        }
        // 判断 token 中用户信息是否正确
        Long userId = JWTUtils.getUserInfo(token);
        User user = userService.getById(userId);
        if (user == null) {
            Result result = Result.fail(ErrorCode.Token_IS_ERROR.getCode(), ErrorCode.Token_IS_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
            return false;
        }
        return true;
    }
}