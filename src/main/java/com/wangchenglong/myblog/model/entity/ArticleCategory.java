package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/9 16:14
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article_category")
public class ArticleCategory {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "author_id")
    private Long userId;

    private Long articleId;

    private Long categoryId;
}