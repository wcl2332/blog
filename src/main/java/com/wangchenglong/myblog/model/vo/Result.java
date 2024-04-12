package com.wangchenglong.myblog.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一返回信息
 */
@ApiModel(value = "统一返回信息")
public class Result {

    @ApiModelProperty(name = "code",value = "返回码")
    private Integer code;

    @ApiModelProperty(name = "msg",value = "返回信息")
    private String msg;

    @ApiModelProperty(name = "data",value = "返回数据")
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
        super();
    }

    public static Result success(Object data) {
        return new Result(0, "success", data);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
