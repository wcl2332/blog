package com.wangchenglong.myblog.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 14:22
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    /**
     * 文章标题
     */
    @ApiModelProperty(name = "title", value = "文章标题", dataType = "String", required = true)
    private String title;

    /**
     * 封面图片
     */
    @ApiModelProperty(name = "coverImage", value = "文章封面", dataType = "String", required = false)
    private String coverImage;
    /**
     * 文章内容
     */
    @ApiModelProperty(name = "content", value = "文章内容", dataType = "String", required = true)
    private String content;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型(0-原创 1-转载)", dataType = "Integer", required = true)
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    @ApiModelProperty(name = "originalUrl", value = "原文地址(当 type=1时，需要有原文地址)", dataType = "String", required = true)
    private String originalUrl;

    /**
     * 分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID", dataType = "Long", required = true)
    private Long categoryId;

    /**
     * 标签
     */
    @ApiModelProperty(name = "tagDTO", value = "标签", dataType = "List", required = true)
    private List<TagDto> tagDTO;

    /**
     * 权重 用于文章置顶
     */
    @ApiModelProperty(name = "weight", value = "分类ID", dataType = "Integer", required = true)
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    @ApiModelProperty(name = "statusCode", value = "文章状态(0-草稿 1-发布)", dataType = "Integer", required = true)
    private Integer statusCode;

}