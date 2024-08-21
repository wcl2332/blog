package com.wangchenglong.myblog.model.entity.server;

import com.wangchenglong.myblog.utils.Arith;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Negative;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/21 14:49
 * @Description: cpu信息
 */
@ApiModel(value = "系统cpu信息")
@AllArgsConstructor
@NoArgsConstructor
public class Cpu {

    @ApiModelProperty(name = "cpuNum", value = "CPU 核心数")
    private Integer cpuNum;

    @ApiModelProperty(name = "total", value = "CPU 总的使用率")
    private double total;

    @ApiModelProperty(name = "sys", value = "CPU 系统使用率")
    private double sys;

    @ApiModelProperty(name = "used", value = "CPU 用户使用率")
    private double used;

    @ApiModelProperty(name = "wait", value = "CPU 当前等待率")
    private double wait;

    @ApiModelProperty(name = "free", value = "CPU 当前空闲率")
    private double free;


    public int getCpuNum()
    {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum)
    {
        this.cpuNum = cpuNum;
    }

    public double getTotal()
    {
        return Arith.round(Arith.mul(total, 100), 2);
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getSys()
    {
        return Arith.round(Arith.mul(sys / total, 100), 2);
    }

    public void setSys(double sys)
    {
        this.sys = sys;
    }

    public double getUsed()
    {
        return Arith.round(Arith.mul(used / total, 100), 2);
    }

    public void setUsed(double used)
    {
        this.used = used;
    }

    public double getWait()
    {
        return Arith.round(Arith.mul(wait / total, 100), 2);
    }

    public void setWait(double wait)
    {
        this.wait = wait;
    }

    public double getFree()
    {
        return Arith.round(Arith.mul(free / total, 100), 2);
    }

    public void setFree(double free)
    {
        this.free = free;
    }
}