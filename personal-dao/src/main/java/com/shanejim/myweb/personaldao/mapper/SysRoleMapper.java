package com.shanejim.myweb.personaldao.mapper;

import com.shanejim.myweb.personalmodel.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    SysRole selectByRoleName(@Param("roleName")String roleName);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectAllSysRole(@Param("keywords") String keywords);
}