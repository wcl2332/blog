package com.wangchenglong.myblog.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/4 10:32
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签")
public class TagDto {

    @ApiModelProperty(name = "Id", value = "标签Id", required = true)
    private Long Id;

    @ApiModelProperty(name = "tagName", value = "标签名", required = true)
    private String tagName;
}