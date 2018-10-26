package com.shanejim.myweb.personalmodel.config;

import com.shanejim.myweb.personalmodel.enums.CodeEnums;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:33
 **/
public class ApiException extends RuntimeException {
    private Integer code;
    private String errMsg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiException(Integer code, String errMsg, Object data) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ApiException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

    public ApiException(CodeEnums codeEnums) {
        super(codeEnums.getMsg());
        this.code = codeEnums.getCode();
        this.errMsg = codeEnums.getMsg();
    }

    public ApiException(CodeEnums codeEnums, Object data) {
        super(codeEnums.getMsg());
        this.code = codeEnums.getCode();
        this.errMsg = codeEnums.getMsg();
        this.data = data;
    }
}

