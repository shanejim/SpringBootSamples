package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.SysPermission;
import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysPermissionQuery;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import com.shanejim.myweb.personalservice.SysPermissionService;
import com.shanejim.myweb.personalservice.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 11:09
 **/
@Api(value = "syspermissions", description = "后台权限", tags = {"后台权限相关接口"})
@RestController
@RequestMapping(value = "/syspermissions")
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    @PostMapping("/v1")
    public Result insertSysPermission(@RequestBody @Validated AddOrUpdateSysPermissionQuery dto) {
        sysPermissionService.insertSysPermission(dto);

        return ResultUtil.success();
    }

    @DeleteMapping("/v1/{id}")
    public Result deleteSysPermission(@PathVariable Long id) {
        sysPermissionService.deleteSysPermission(id);

        return ResultUtil.success();
    }

    @PutMapping("/v1/{id}")
    public Result updateSysPermission(@PathVariable Long id, @RequestBody @Validated AddOrUpdateSysPermissionQuery dto) {
        sysPermissionService.updateSysPermission(id, dto);

        return ResultUtil.success();
    }

    @GetMapping("/v1/{id}")
    public Result<SysPermission> getSysPermission(@PathVariable Long id) {
        SysPermission sysPermission = sysPermissionService.getSysPermissionById(id);

        return ResultUtil.success(sysPermission);
    }

    @GetMapping(value = "/v1")
    public Result<PagingReturn<SysPermission>> getPagingList(@RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                       @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        PagingReturn model = sysPermissionService.listSysPermission(pageNum, pageSize);
        return ResultUtil.success(model);
    }
}
