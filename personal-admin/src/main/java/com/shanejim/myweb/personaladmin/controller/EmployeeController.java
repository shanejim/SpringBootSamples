package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;
import com.shanejim.myweb.personalmodel.query.LoginQuery;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import com.shanejim.myweb.personalservice.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 11:17
 **/
@Api(value = "employee", description = "employee相关的操作", tags = {"员工相关接口"})
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "添加员工", notes = "添加员工接口，Post，传json")
    @PostMapping("/v1")
    public Result insertChatRoom(@RequestBody @Validated AddOrUpdateEmployeeQuery dto) {
        employeeService.insertEmployee(dto);

        return ResultUtil.success();
    }

    @ApiOperation(value = "登录", notes = "后台登录接口")
    @PostMapping("/checkpwd")
    public Result checkLogin(@RequestBody LoginQuery dto,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        //String SESSION_KEY = "employeelogin";

        Employee employee = employeeService.checkLogin(dto.getUserName(), dto.getPassWord());

        if (employee == null) {
            return ResultUtil.error(CodeEnums.COMMON_ERR.getCode(), "登录失败，账号或密码错误");
        } else {
            //request.getSession().setAttribute(SESSION_KEY, employee);
            return ResultUtil.success("登陆成功");
        }
    }


}

