package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 16:19
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    /**
     * 标签 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private Long authorId;
    /**
     * 标签 name
     */
    @TableField(value = "name")
    private String tagName;

    /**
       创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}