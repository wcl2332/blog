package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 16:20
 * @Description: TODO
 */
@ApiModel(value = "文章分类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /**
     * 分类编号
     */
    @ApiModelProperty(value = "分类ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名字")
    private String name;

    @ApiModelProperty(value = "作者ID")
    private Long authorId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}