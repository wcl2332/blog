package com.wangchenglong.myblog.model.entity.server;

import com.wangchenglong.myblog.utils.Arith;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/21 15:04
 * @Description: 内存
 */
@ApiModel("内存相关信息")
@AllArgsConstructor
@NoArgsConstructor
public class Mem {

    @ApiModelProperty(name = "total", value = "内存总量")
    private double total;

    @ApiModelProperty(name = "used", value = "已用内存")
    private double used;

    @ApiModelProperty(name = "free", value = "剩余内存")
    private double free;

    public double getTotal() {
        return Arith.div(total, (1024 * 1024 * 1024), 2);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getUsed() {
        return Arith.div(used, (1024 * 1024 * 1024), 2);
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024 * 1024), 2);
    }

    public void setFree(long free) {
        this.free = free;
    }

    public double getUsage() {
        return Arith.mul(Arith.div(used, total, 4), 100);
    }
}