package com.wangchenglong.myblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/7 16:23
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVo {
    private String uid;
    private String captchaCode;
}