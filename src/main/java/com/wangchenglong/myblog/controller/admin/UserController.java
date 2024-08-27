package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.UserVo;
import com.wangchenglong.myblog.service.UserService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 15:38
 * @Description:
 */
@Api(tags = "管理端-用户信息相关")
@RestController
@RequestMapping("/admin/api/user")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation(value = "获取个人信息", response = UserVo.class)
    @GetMapping("/userInfo")
    public Result<UserVo> getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return userService.getUserById(userId);
    }

    @ApiOperation("修改个人信息")
    @PostMapping("/update")
    public Result<Object> updateUserInfo(@Valid @RequestBody UserDto userDto, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(userDto)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return userService.updateUserInfo(userDto, userId);
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码"),
            @ApiImplicitParam(name = "newPassword", value = "新密码")
    })
    @PostMapping("/updatePassword")
    public Result<String> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        long userId = JWTUtils.getUserInfo(httpServletRequest.getHeader("token"));
        return userService.updatePassword(oldPassword, newPassword, userId);
    }
}