package com.wangchenglong.myblog.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/7 16:23
 * @Description:
 */
@ApiModel(value = "图片验证码返回前端的实体类，")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVo {
    @ApiModelProperty(value = "图片验证码uid,验证时需要携带", required = true)
    private String uid;
    @ApiModelProperty(value = "图片验证码的base64", required = true)
    private String captchaCode;
}