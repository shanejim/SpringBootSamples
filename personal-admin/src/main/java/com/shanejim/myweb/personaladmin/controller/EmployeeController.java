package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;
import com.shanejim.myweb.personalmodel.query.ModifyPasswordQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import com.shanejim.myweb.personalservice.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 11:17
 **/
@Api(value = "employees", description = "employee相关的操作", tags = {"员工相关接口"})
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "添加员工", notes = "添加员工接口，Post，传json")
    @PostMapping("")
    public Result insertEmployee(@RequestBody @Validated AddOrUpdateEmployeeQuery dto) {
        employeeService.insertEmployee(dto);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除员工", notes = "根据id删除员工")
    @DeleteMapping("/{id}")
    public Result deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "修改密码", notes = "修改密码接口，Post，传json")
    @PostMapping("/modifyPassword")
    public Result modifyPassword(@RequestBody @Validated ModifyPasswordQuery dto) {
        employeeService.modifyPassword(dto.getId(), dto.getPassword());
        return ResultUtil.success();
    }

    @ApiOperation(value = "分页获取员工列表", notes = "分页获取所有员工")
    @GetMapping(value = "")
    public Result<PagingReturn<Employee>> getPagingList(@RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                        @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        PagingReturn model = employeeService.listEmployee(pageNum, pageSize);
        return ResultUtil.success(model);
    }

    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @GetMapping("/menus")
    public Result menus() {
        return ResultUtil.success(employeeService.listMyPermission());
    }

}

