package com.wangchenglong.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 16:05
 * @Description:
 */
@ApiModel(value = "用户返回前端的信息实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    /**
     * 账户
     */
    @ApiModelProperty(value = "账号",required = true)
    private String account;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名",required = true)
    private String realName;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像",required = true)
    private String avatar;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介",required = true)
    private String summary;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称",required = true)
    private String nickName;


    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱",required = true)
    private String email;
}