package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.LoginService;
import com.wangchenglong.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 21:02
 * @Description: 管理端 登录相关控制器
 */
@Api(tags = "管理端-登录相关")
@RestController
@RequestMapping("/admin/api")
public class LoginController {
    @Resource
    LoginService loginService;

    @Resource
    UserService userService;

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="account",value = "账号",required = true),
            @ApiImplicitParam(name ="password",value = "密码",required = true)
    })
    @PostMapping("/login")
    public Result login(@RequestParam("account") String loginName, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        return loginService.login(loginName, password);
    }

    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="account",value = "账号",required = true),
            @ApiImplicitParam(name ="password",value = "密码",required = true)
    })
    @PostMapping("/register")
    public Result registerUser(@RequestParam("account") String loginName, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return new Result(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        return userService.registerUser(loginName, password);
    }
}