package com.wangchenglong.myblog.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 16:24
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户")
public class UserDto {
    /**
     * 真实姓名
     */
    @ApiModelProperty(name = "realName", value = "真实姓名", required = false)
    private String realName;
    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像", required = false)
    private String avatar;

    /**
     * 简介
     */
    @ApiModelProperty(name = "summary", value = "用户简介", required = false)
    private String summary;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(name = "email", value = "电子邮箱", required = false)
    private String email;

    /**
     * 昵称
     */
    @ApiModelProperty(name = "nickName", value = "昵称", required = false)
    private String nickName;

    /**
     * 密码
     */
    //    @ApiModelProperty(name = "password", value = "密码", required = false)
    //    private String password;
}