package com.shanejim.myweb.personalservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanejim.myweb.personaldao.mapper.SysPermissionMapper;
import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.entity.SysPermission;
import com.shanejim.myweb.personalmodel.entity.SysRole;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateSysPermissionQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalservice.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 11:12
 **/
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public int insertSysPermission(AddOrUpdateSysPermissionQuery dto) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setIsDeleted(new Byte("0"));
        sysPermission.setAddTime(LocalDateTime.now());
        sysPermission.setModifiedTime(LocalDateTime.now());
        sysPermission.setParentId(dto.getParentId());
        sysPermission.setSort(dto.getSort());
        sysPermission.setSref(dto.getSref());
        sysPermission.setText(dto.getText());

        return sysPermissionMapper.insertSelective(sysPermission);
    }

    @Override
    public int updateSysPermission(Long id, AddOrUpdateSysPermissionQuery dto) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        sysPermission.setModifiedTime(LocalDateTime.now());
        sysPermission.setParentId(dto.getParentId());
        sysPermission.setSort(dto.getSort());
        sysPermission.setSref(dto.getSref());
        sysPermission.setText(dto.getText());

        return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
    }

    @Override
    public int deleteSysPermission(Long id) {
        SysPermission sysPermissionBefore = sysPermissionMapper.selectByPrimaryKey(id);
        if (sysPermissionBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "不存在或已被删除！");
        }
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        sysPermission.setModifiedTime(LocalDateTime.now());
        sysPermission.setIsDeleted(new Byte("1"));

        return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
    }

    @Override
    public SysPermission getSysPermissionById(Long id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PagingReturn listSysPermission(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<SysPermission> sysPermissionList = sysPermissionMapper.selectAllSysPermission();
        PageInfo<SysPermission> pageInfo = new PageInfo<>(sysPermissionList);

        PagingReturn<SysPermission> model = new PagingReturn<>();
        model.setTotal(pageInfo.getTotal());
        model.setResults(sysPermissionList);
        return model;
    }
}
