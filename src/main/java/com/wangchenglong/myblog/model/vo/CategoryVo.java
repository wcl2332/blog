package com.wangchenglong.myblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/9 14:17
 * @Description:
 */
@ApiModel("分类返回前端的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    @ApiModelProperty(value = "分类ID")
    private Long id;
    @ApiModelProperty(value = "分类的名字")
    private String name;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "分类生成的时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}