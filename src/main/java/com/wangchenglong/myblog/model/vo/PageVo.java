package com.wangchenglong.myblog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 13:52
 * @Description:
 */
@ApiModel("分页返回前端的信息实体，records 中包含的是对应的对象的实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageVo<T> {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页",required = true)
    private Long current;
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数",required = true)
    private Long total;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码",required = true)
    private Long pages;
    /**
     * 长度
     */
    @ApiModelProperty(value = "显示个数",required = true)
    private Long size;
    /**
     * 数据
     */
    @ApiModelProperty(value = "分页内容信息",required = true)
    private List<T> records;



}