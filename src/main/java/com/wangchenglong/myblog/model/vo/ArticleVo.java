package com.wangchenglong.myblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangchenglong.myblog.model.entity.ArticleBody;
import com.wangchenglong.myblog.model.entity.Category;
import com.wangchenglong.myblog.model.entity.Tag;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(description = "文章")
public class ArticleVo {
    /**
     * 主键
     */
    // @ApiModelProperty(name = "id", value = "文章id", required = true, dataType = "Long")
    private Long id;
    /**
     * 文章标题
     */
    //@ApiModelProperty(name = "title", value = "文章标题", required = true, dataType = "String")
    private String title;
    /**
     * 作者 ID,
     */
    //@ApiModelProperty(name = "userVO", value = "作者信息", required = true, dataType = "UserVO")
    private UserVo user;
    /**
     * 文章内容 ID
     */
    private ArticleBody articleBody;
    /**
     * 封面图片
     */
    private String coverImage;

    /**
     * 文章标签
     */
    //private List<String> tagNames;
    /**
     * 文章发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
     * 分类
     */
    private Category category;
    /**
     * 标签
     */
    private List<Tag> tag;

    /**
     * 类型 文章类型 0 原创 1转载
     */
    private Integer type;

    /**
     * 当 type = 1  为转载文章时 ，需要有原文地址
     */
    private String originalUrl;

    /**
     * 权重 用于文章置顶
     */
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    private Integer statusCode;
    /**
     * 浏览量
     */
    private Integer viewsCount;
    /**
     * 文章列表显示内容, 则 概述
     */
    private String introduction;
}