package com.wangchenglong.myblog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/4 15:08
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    private Long id;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String coverImage;
    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    private String originalUrl;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 标签
     */
    private List<TagDto> tagDTO;

    /**
     * 权重 用于文章置顶
     */
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    private Integer statusCode;
}