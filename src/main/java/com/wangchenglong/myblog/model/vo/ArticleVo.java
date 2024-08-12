package com.wangchenglong.myblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangchenglong.myblog.model.entity.ArticleBody;
import com.wangchenglong.myblog.model.entity.Category;
import com.wangchenglong.myblog.model.entity.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 11:18
 * @Description: TODO
 */
@ApiModel(value = "文章返回信息的实体类，包括分页中、获取文章中等返回的关于文章的信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(description = "文章")
public class ArticleVo {
    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "文章id", required = true)
    private Long id;
    /**
     * 文章标题
     */
    @ApiModelProperty(name = "title", value = "文章标题", required = true)
    private String title;
    /**
     * 作者 ID,
     */
    @ApiModelProperty(name = "user", value = "作者信息", required = true)
    private UserVo user;
    /**
     * 文章内容 ID
     */
    @ApiModelProperty(name = "articleBody", value = "文章内容", required = true)
    private ArticleBody articleBody;
    /**
     * 封面图片
     */
    @ApiModelProperty(name = "coverImage", value = "封面图片", required = true)
    private String coverImage;

    /**
     * 文章标签
     */
    //private List<String> tagNames;
    /**
     * 文章发布时间
     */
    @ApiModelProperty(name = "createTime", value = "文章发布时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(name = "updateTime", value = "修改时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
     * 分类
     */
    @ApiModelProperty(name = "category", value = "分类", required = true)
    private Category category;
    /**
     * 标签
     */
    @ApiModelProperty(name = "tag", value = "分类", required = true)
    private List<Tag> tag;

    /**
     * 类型 文章类型 0 原创 1转载
     */
    @ApiModelProperty(name = "type", value = "文章类型  0 原创 1转载", required = true)
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    @ApiModelProperty(name = "originalUrl", value = "当 type = 1  为转载文章时 ，需要有原文地址", required = false)
    private String originalUrl;

    /**
     * 权重 用于文章置顶
     */
    @ApiModelProperty(name = "weight", value = "权重 用于文章置顶", required = true)
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    @ApiModelProperty(name = "statusCode", value = "状态 0 草稿 1 发布", required = true)
    private Integer statusCode;
    /**
     * 浏览量
     */
    @ApiModelProperty(name = "viewsCount", value = "浏览量", required = true)
    private Integer viewsCount;
    /**
     * 文章列表显示内容, 则 概述
     */
    @ApiModelProperty(name = "introduction", value = "文章列表显示内容, 概述", required = true)
    private String introduction;
}