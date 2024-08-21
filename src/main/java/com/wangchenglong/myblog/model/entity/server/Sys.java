package com.wangchenglong.myblog.model.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/21 14:58
 * @Description: 系统相关
 */
@ApiModel("系统相关")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sys {

    @ApiModelProperty(name = "computer", value = "服务器名称")
    private String computerName;

    @ApiModelProperty(name = "computerIp", value = "服务器Ip")
    private String computerIp;

    @ApiModelProperty(name = "userDir", value = "项目路径")
    private String userDir;

    @ApiModelProperty(name = "osName", value = "操作系统名称")
    private String osName;

    @ApiModelProperty(name = "osArch", value = "操作系统架构")
    private String osArch;
}