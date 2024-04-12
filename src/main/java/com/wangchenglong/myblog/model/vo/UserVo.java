package com.wangchenglong.myblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 16:05
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    /**
     * 账户
     */
    private String account;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String summary;
}