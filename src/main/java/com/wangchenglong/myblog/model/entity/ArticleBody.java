package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 19:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article_content")
public class ArticleBody {
    /**
     * id
     */
    @ApiModelProperty(value = "内容ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容", required = true)
    private String content;
}