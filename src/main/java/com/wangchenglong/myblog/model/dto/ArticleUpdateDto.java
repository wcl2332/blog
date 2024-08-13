package com.wangchenglong.myblog.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/4 15:08
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章修改")
public class ArticleUpdateDto {

    @ApiModelProperty(name = "id", value = "文章Id",  required = true)
    private Long id;
    /**
     * 文章标题
     */
    @ApiModelProperty(name = "title", value = "文章标题",  required = true)
    @NotEmpty(message = "文章标题不能为空")
    private String title;

    /**
     * 封面图片
     */
    @ApiModelProperty(name = "coverImage", value = "文章图片封面",  required = true)
    private String coverImage;
    /**
     * 文章内容
     */
    @ApiModelProperty(name = "content", value = "文章内容",  required = true)
    @NotEmpty(message = "文章内容不能为空")
    private String content;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型（type = 1 为转载）",  required = true)
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    @ApiModelProperty(name = "originalUrl", value = "原文地址（type = 1 为转载文章）",  required = false)
    private String originalUrl;

    /**
     * 分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID",  required = true)
    private Long categoryId;

    /**
     * 标签
     */
    @ApiModelProperty(name = "tagIds", value = "标签Ids 选择多个时，例如 3,4",  required = true)
    private String tagIds;

    /**
     * 权重 用于文章置顶
     */
    @ApiModelProperty(name = "weight", value = "文章权重",  required = true)
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    @ApiModelProperty(name = "statusCode", value = "文章状态（状态 0 草稿 1 发布）",  required = true)
    private Integer statusCode;
}