package com.shanejim.myweb.personalmodel.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:25
 **/
public class Result<T> {
    @ApiModelProperty(value = "code码，0表示成功，其他表示错误，系统错误或业务失败", required = true)
    private Integer code;

    @ApiModelProperty(value = "消息，错误提示", required = true)
    private String msg;

    @ApiModelProperty(value = "数据内容")
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
