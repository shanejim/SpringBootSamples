package com.shanejim.myweb.personaladmin.config;

import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:35
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object jsonErrorHandler(Exception e) throws Exception {
        Result baseRes = new Result();
        //自定义业务异常
        if (e instanceof ApiException) {
            ApiException ae = (ApiException) e;
            baseRes.setCode(ae.getCode());
            baseRes.setMsg(ae.getErrMsg());
            baseRes.setData(ae.getData());
            return baseRes;
        }
        //请求参数 模型验证异常
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = me.getBindingResult().getAllErrors();

            baseRes.setCode(CodeEnums.PARAM_ERR.getCode());
            baseRes.setMsg(errors.get(0).getDefaultMessage());
            return baseRes;
        }
        //spring security方法级别的验证，没有权限时抛出的异常，这里后台直接返回403的状态，方便angularjs统一处理
        if (e instanceof AccessDeniedException) {
            baseRes.setCode(CodeEnums.FORBIDDEN.getCode());
            baseRes.setMsg("对不起，您没有操作权限");

            return new ResponseEntity(baseRes, HttpStatus.FORBIDDEN);
        }
        logger.error("【系统异常】{}", e);

        //未知异常 空指针什么的 未知错误
        baseRes.setCode(CodeEnums.INTERNAL_SERVER_ERROR.getCode());
        baseRes.setMsg(CodeEnums.INTERNAL_SERVER_ERROR.getMsg());
        return baseRes;
    }

}
