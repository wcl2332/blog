package com.wangchenglong.myblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 13:52
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    /**
     * 当前页
     */
    private Long current;
    /**
     * 总数
     */
    private Long total;
    /**
     * 页码
     */
    private Long pages;
    /**
     * 长度
     */
    private Long size;
    /**
     * 数据
     */
    private Object records;

}