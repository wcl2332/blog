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
    @ApiModelProperty(name = "realName", value = "真实姓名", dataType = "String", required = true)
    private String realName;
    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "String", required = false)
    private String avatar;

    /**
     * 简介
     */
    @ApiModelProperty(name = "summary", value = "用户简介", dataType = "String", required = false)
    private String summary;

    /**
     *
     */
    @ApiModelProperty(name = "email", value = "电子邮箱", dataType = "String", required = false)
    private String email;
}