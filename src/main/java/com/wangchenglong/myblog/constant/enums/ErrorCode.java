package com.wangchenglong.myblog.constant.enums;

/**
 * 错误
 */
public enum ErrorCode {
    /**
     * 规定 1000-1999  参数相关
     * 2000-2999  用户相关
     * 3000-3999  系统相关
     */
    Exception_IS_ERROR(3000, "系统异常！"),
    Token_IS_ERROR(10001, "无效token"),
    Login_PWD_ERROR(1002, "账号或密码错误"),
    PARAMS_IS_NULL(1003, "提交参数有误"),
    UPDATE_IS_ERROR(10004, "信息设置失败"),
    DELETE_IS_ERROR(10005,"删除失败"),
    ARTICLE_IS_NULL(1006,"文章不存在"),
    UPLOAD_IS_FAILL(1007,"上传失败"),
    INSERT_IS_FAILL(1008,"添加失败"),
    ACCOUNT_NOT_EXIST(2001, "用户不存在");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
