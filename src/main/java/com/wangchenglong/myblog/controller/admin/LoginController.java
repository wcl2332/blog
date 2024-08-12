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
            @ApiImplicitParam(name = "account", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "imageUId", value = "验证码uid", required = true),
            @ApiImplicitParam(name = "imageCode", value = "用户输入图片验证码", required = true)
    })
    @PostMapping("/login")
    public Result<String> login(@RequestParam("account") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("imageUId") String imageUId,
                        @RequestParam("imageCode") String imageCode) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(imageUId) || StringUtils.isEmpty(imageCode)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        return loginService.login(loginName, password, imageUId, imageCode);
    }

    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "imageUId", value = "验证码uid", required = true),
            @ApiImplicitParam(name = "imageCode", value = "用户输入图片验证码", required = true),
            @ApiImplicitParam(name = "nickName", value = "昵称", required = true),
    })
    @PostMapping("/register")
    public Result<String> registerUser(@RequestParam("account") String loginName,
                               @RequestParam("password") String password,
                               @RequestParam("imageUId") String imageUId,
                               @RequestParam("imageCode") String imageCode,
                               @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(imageUId) || StringUtils.isEmpty(imageCode)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        return userService.registerUser(loginName, password,imageUId, imageCode,nickName);
    }
}