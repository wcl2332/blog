package com.wangchenglong.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.NavigableMap;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/23 14:36
 * @Description: 异常日志实体
 */
@ApiModel(value = "异常日志实体")
@TableName("ex_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLog {
    @ApiModelProperty(name = "id", value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "userId", value = "所属的 userId")
    private Long userId;

    @ApiModelProperty(name = "uri", value = "请求地址")
    private String uri;

    @ApiModelProperty(name = "method", value = "请求方法")
    private String method;

    @ApiModelProperty(name = "params", value = "请求参数")
    private String params;

    @ApiModelProperty(name = "description", value = "操作描述描述")
    private String description;

    @ApiModelProperty(name = "error", value = "异常信息")
    private String error;

    @ApiModelProperty(name = "ip", value = "ip")
    private String ip;

    @ApiModelProperty(name = "ipSource", value = "ip来源")
    @TableField("ipSource")
    private String ipSource;

    @ApiModelProperty(name = "os", value = "操作系统")
    private String os;

    @ApiModelProperty(name = "browser", value = "浏览器")
    private String browser;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    @TableField(value = "createTime")
    private Date createTime;

    @ApiModelProperty(name = "userAgent", value = "浏览器标识")
    @TableField(value = "userAgent")
    private String userAgent;

    public ExceptionLog(String uri, String method, String description, String error, String ip, String userAgent) {
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.error = error;
        this.ip = ip;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }


}