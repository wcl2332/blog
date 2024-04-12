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
 * @Date: 2023/8/2 16:03
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article_tag")
public class ArticleTag {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "author_id")
    private Long userId;

    private Long articleId;

    private Long tagId;
}