package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Date: 2023/7/26 16:19
 * @Description:
 */
@ApiModel(value = "标签类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    /**
     * 标签 id
     */
    @ApiModelProperty(value = "标签id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "authorId" ,value = "作者id")
    private Long authorId;
    /**
     * 标签 name
     */
    @ApiModelProperty(name = "tagName" ,value = "标签名字")
    @TableField(value = "name")
    private String tagName;

    /**
       创建时间
     */
    @ApiModelProperty(name = "createTime",value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}