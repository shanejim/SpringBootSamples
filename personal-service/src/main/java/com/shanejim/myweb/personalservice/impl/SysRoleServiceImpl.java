package com.shanejim.myweb.personalservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanejim.myweb.personaldao.mapper.SysRoleMapper;
import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysRoleQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalservice.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 09:20
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int insertSysRole(AddOrUpdateSysRoleQuery dto) {
        SysRole sysRoleBefore = sysRoleMapper.selectByRoleName(dto.getRoleName());
        if (sysRoleBefore != null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "角色名不能重复！");
        }
        SysRole sysRole = new SysRole();
        sysRole.setIsDeleted(new Byte("0"));
        sysRole.setAddTime(LocalDateTime.now());
        sysRole.setModifiedTime(LocalDateTime.now());
        sysRole.setRoleName(dto.getRoleName());

        return sysRoleMapper.insertSelective(sysRole);
    }

    @Override
    public int updateSysRole(Long id, AddOrUpdateSysRoleQuery dto) {
        SysRole sysRoleBefore = sysRoleMapper.selectByRoleName(dto.getRoleName());
        if (sysRoleBefore != null && sysRoleBefore.getId() != id) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "角色名不能重复！");
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setModifiedTime(LocalDateTime.now());
        sysRole.setRoleName(dto.getRoleName());

        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public int deleteSysRole(Long id) {
        SysRole sysRoleBefore = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRoleBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "角色名不存在或已被删除！");
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setModifiedTime(LocalDateTime.now());
        sysRole.setIsDeleted(new Byte("1"));

        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public SysRole getSysRoleById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PagingReturn listSysRole(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<SysRole> sysRoleList = sysRoleMapper.selectAllSysRole();
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);

        PagingReturn<SysRole> model = new PagingReturn<>();
        model.setTotal(pageInfo.getTotal());
        model.setResults(sysRoleList);
        return model;
    }
}
