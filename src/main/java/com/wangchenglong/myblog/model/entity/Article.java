package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 14:36
 * @Description: 文章实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者 ID,
     */
    private Long authorId;
    /**
     * 文章内容 ID
     */
    private Long contentId;
    /**
     * 封面图片
     */
    private String coverImage;
    /**
     * 文章发布时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 分类ID
     */
    private Long categoryId;

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
     * 状态 0 草稿 1 发布 (也就是公开状态) 2 (隐藏状态)
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