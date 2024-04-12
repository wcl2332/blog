package com.wangchenglong.myblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 16:20
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * 分类编号
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
}