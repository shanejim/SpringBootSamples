package com.shanejim.myweb.personalmodel.utils;

import com.shanejim.myweb.personalmodel.response.Result;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:38
 **/
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
