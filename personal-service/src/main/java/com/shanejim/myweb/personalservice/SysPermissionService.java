package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.SysPermission;
import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysPermissionQuery;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 11:11
 **/
public interface SysPermissionService {
    int insertSysPermission(AddOrUpdateSysPermissionQuery dto);

    int updateSysPermission(Long id, AddOrUpdateSysPermissionQuery dto);

    int deleteSysPermission(Long id);

    SysPermission getSysPermissionById(Long id);

    PagingReturn listSysPermission(Integer pageNum, Integer pageSize);
}
