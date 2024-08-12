package com.wangchenglong.myblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/8 15:50
 * @Description: TODO
 */
@ApiModel(value = "标签返回实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    @ApiModelProperty(name = "id", value = "标签ID")
    private Long id;

    @ApiModelProperty(name = "tagName", value = "标签名称")
    private String tagName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Date createTime;
}