package com.wangchenglong.myblog.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 14:22
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章新增实体")
public class ArticleDto {
    /**
     * 文章标题
     */
    @ApiModelProperty(name = "title", value = "文章标题", required = true)
    @NotEmpty(message = "文章标题不能为空")
    private String title;

    /**
     * 封面图片
     */
    @ApiModelProperty(name = "coverImage", value = "文章封面", required = false)
    private String coverImage;
    /**
     * 文章内容
     */
    @ApiModelProperty(name = "content", value = "文章内容", required = true)
    @NotEmpty(message = "文章内容不能为空")
    private String content;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型(0-原创 1-转载)", required = true)
    @NotEmpty(message = "文章类型不能为空")
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    @ApiModelProperty(name = "originalUrl", value = "原文地址(当 type=1时，需要有原文地址)", required = true)
    private String originalUrl;

    /**
     * 分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID", required = true)
    private Long categoryId;

    /**
     * 标签
     */
    @ApiModelProperty(name = "tagIds", value = "标签id列表（一个文章可以有多个标签） 选择多个时，例如 3,4", required = true)
    private String tagIds;

    /**
     * 权重 用于文章置顶
     */
    @ApiModelProperty(name = "weight", value = "权重 用于文章置顶,数字越大,排序越靠前", required = true)
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    @ApiModelProperty(name = "statusCode", value = "文章状态(0-草稿 1-发布)", required = true)
    @NotEmpty(message = "文章状态不能为空")
    private Integer statusCode;


    /**
     * 文章列表显示内容, 则 概述
     */
    @ApiModelProperty(name = "introduction", value = "文章概述，可以尝试截取合适的文章内容长度做为字段")
    @NotEmpty(message = "文章概述不能为空")
    private String introduction;

}