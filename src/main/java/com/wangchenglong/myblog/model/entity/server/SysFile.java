package com.wangchenglong.myblog.model.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/21 15:12
 * @Description: 系统文件相关
 */
@ApiModel("系统文件相关")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysFile {
    /**
     * 盘符路径
     */
    @ApiModelProperty(name = "dirName", value = "盘符路径")
    private String dirName;

    /**
     * 盘符类型
     */
    @ApiModelProperty(name = "sysTypeName", value = "盘符类型")
    private String sysTypeName;

    /**
     * 文件类型
     */
    @ApiModelProperty(name = "typeName", value = "文件类型")
    private String typeName;

    /**
     * 总大小
     */
    @ApiModelProperty(name = "total", value = "总大小")
    private String total;

    /**
     * 剩余大小
     */
    @ApiModelProperty(name = "free", value = "剩余大小")
    private String free;

    /**
     * 已经使用量
     */
    @ApiModelProperty(name = "used", value = "已经使用量")
    private String used;

    /**
     * 资源的使用率
     */
    @ApiModelProperty(name = "usage", value = "资源的使用率")
    private double usage;

}