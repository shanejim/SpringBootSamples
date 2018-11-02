package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.SysPermission;
import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.query.UpdateSysRolePermissionQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import com.shanejim.myweb.personalmodel.vo.SysPermissionTreeVo;
import com.shanejim.myweb.personalservice.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 09:15
 **/
@Api(value = "sysroles", description = "后台角色", tags = {"后台角色相关接口"})
@RestController
@RequestMapping(value = "/sysroles")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/v1")
    public Result insertSysRole(@RequestBody @Validated AddOrUpdateSysRoleQuery dto) {
        sysRoleService.insertSysRole(dto);

        return ResultUtil.success();
    }

    @DeleteMapping("/v1/{id}")
    public Result deleteSysRole(@PathVariable Long id) {
        sysRoleService.deleteSysRole(id);

        return ResultUtil.success();
    }

    @PutMapping("/v1/{id}")
    public Result updateSysRole(@PathVariable Long id, @RequestBody @Validated AddOrUpdateSysRoleQuery dto) {
        sysRoleService.updateSysRole(id, dto);

        return ResultUtil.success();
    }

    @GetMapping("/v1/{id}")
    public Result<SysRole> getSysRole(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getSysRoleById(id);

        return ResultUtil.success(sysRole);
    }

    @GetMapping(value = "/v1")
    public Result<PagingReturn<SysRole>> getPagingList(@RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                       @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                       @RequestParam(name = "keywords", required = false) String keywords) {
        PagingReturn model = sysRoleService.listSysRole(pageNum, pageSize, keywords);
        return ResultUtil.success(model);
    }

    @GetMapping("/{sysRoleId}/permissions")
    public Result getSysRolePermission(@PathVariable Long sysRoleId) {
        List<SysPermissionTreeVo> list = sysRoleService.listThisSysRolePermission(sysRoleId);

        return ResultUtil.success(list);
    }

    @PutMapping("/{sysRoleId}/permissions")
    public Result updateSysRolePermission(@PathVariable Long sysRoleId, @RequestBody UpdateSysRolePermissionQuery dto) {
        sysRoleService.updateSysRolePermisson(sysRoleId, dto.getAuthList());
        //List<SysPermissionTreeVo> list = sysRoleService.listThisSysRolePermission(sysRoleId);

        return ResultUtil.success();
    }
}
