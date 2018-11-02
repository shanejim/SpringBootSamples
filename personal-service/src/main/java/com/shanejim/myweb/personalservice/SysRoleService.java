package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;

public interface SysRoleService {
    int insertSysRole(AddOrUpdateSysRoleQuery dto);

    int updateSysRole(Long id, AddOrUpdateSysRoleQuery dto);

    int deleteSysRole(Long id);

    SysRole getSysRoleById(Long id);

    PagingReturn listSysRole(Integer pageNum, Integer pageSize,String keywords);
}
