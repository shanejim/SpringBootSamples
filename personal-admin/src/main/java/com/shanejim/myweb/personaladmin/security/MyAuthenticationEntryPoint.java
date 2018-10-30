package com.shanejim.myweb.personaladmin.security;

import com.alibaba.fastjson.JSON;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-30 16:46
 **/
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(ResultUtil.error(401, "未登录")));
    }
}
