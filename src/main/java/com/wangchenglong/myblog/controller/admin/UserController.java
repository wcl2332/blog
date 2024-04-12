package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.UserService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 15:38
 * @Description: TODO
 */
@Api(tags = "管理端-用户信息相关")
@RestController
@RequestMapping("/admin/api/user")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("获取个人信息")
    @GetMapping("/userInfo")
    public Result getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return userService.getUserById(userId);
    }

    @ApiOperation("修改个人信息")
    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody UserDto user, HttpServletRequest httpServletRequest) {
        //System.out.println("userDto" + user);
        if (user == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        userService.updateUserInfo(user, userId);
        return Result.success("");
    }
}