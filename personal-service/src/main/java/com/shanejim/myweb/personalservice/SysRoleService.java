package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.vo.SysPermissionTreeVo;

import java.util.List;

public interface SysRoleService {
    int insertSysRole(AddOrUpdateSysRoleQuery dto);

    int updateSysRole(Long id, AddOrUpdateSysRoleQuery dto);

    int deleteSysRole(Long id);

    SysRole getSysRoleById(Long id);

    PagingReturn listSysRole(Integer pageNum, Integer pageSize,String keywords);

    List<SysPermissionTreeVo> listThisSysRolePermission(Long sysRoleId);

    void  updateSysRolePermisson(Long id,List<Long> authList);
}
