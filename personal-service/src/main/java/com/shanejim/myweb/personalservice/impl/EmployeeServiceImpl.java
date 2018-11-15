package com.shanejim.myweb.personalservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanejim.myweb.personaldao.mapper.EmployeeMapper;
import com.shanejim.myweb.personaldao.mapper.SysPermissionMapper;
import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.entity.SysPermission;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.utils.DigestUtil;
import com.shanejim.myweb.personalmodel.vo.EmployeeVo;
import com.shanejim.myweb.personalmodel.vo.SysPermissionSiderVo;
import com.shanejim.myweb.personalservice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 11:07
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public Employee findEmployeeByUSerName(String username) {
        return employeeMapper.selectByName(username);
    }

    @Override
    public int insertEmployee(AddOrUpdateEmployeeQuery employeeDto) {
        Employee beforeEmployee = employeeMapper.selectByName(employeeDto.getName());
        if (beforeEmployee != null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "登录名不能重复");
        }

        Employee employee = new Employee();

        employee.setIsDeleted(new Byte("0"));
        employee.setAddTime(LocalDateTime.now());
        employee.setModifiedTime(LocalDateTime.now());
        employee.setName(employeeDto.getName());

        //用UUID生成随机盐
        String salt = UUID.randomUUID().toString();
        employee.setSalt(salt);
        String password = DigestUtil.sha256Digest(employeeDto.getPassword() + salt);
        employee.setPassword(password);
        return employeeMapper.insertSelective(employee);
    }

    @Override
    public int modifyPassword(Long id, String newPassword) {
        Employee employeeBefore = employeeMapper.selectByPrimaryKey(id);
        if (employeeBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "账号不存在或已被删除，请刷新后重试");
        }

        Employee employee = new Employee();
        employee.setId(id);
        employee.setModifiedTime(LocalDateTime.now());
        String password = DigestUtil.sha256Digest(newPassword + employeeBefore.getSalt());
        employee.setPassword(password);

        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public int deleteEmployee(Long id) {
        Employee employeeBefore = employeeMapper.selectByPrimaryKey(id);
        if (employeeBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "账号不存在或已被删除，请刷新后重试");
        }

        Employee employee = new Employee();
        employee.setId(id);
        employee.setModifiedTime(LocalDateTime.now());
        employee.setIsDeleted(new Byte("1"));
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public PagingReturn<EmployeeVo> listEmployee(Integer pageNum, Integer pageSize, String keywords, String sort) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        Map<String, Object> paramMap = new HashMap<>();
        if (sort.contains("+")) {
            paramMap.put("orderdir", "asc");
            sort = sort.replace("+", "");
        }
        if (sort.contains("-")) {
            paramMap.put("orderdir", "desc");
            sort = sort.replace("-", "");
        }
        paramMap.put("orderfield", sort);
        paramMap.put("keywords", keywords);

        List<EmployeeVo> employeeList = employeeMapper.selectEmployeeList(paramMap);
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(employeeList);

        PagingReturn<EmployeeVo> model = new PagingReturn<>();
        model.setTotal(pageInfo.getTotal());
        model.setResults(employeeList);
        return model;
    }

    @Override
    public Employee checkLogin(String username, String password) {
        Employee employee = employeeMapper.selectByName(username);
        if (employee == null) {
            return null;
        }
        String passwordHash = DigestUtil.sha256Digest(password + employee.getSalt());
        if (passwordHash.equals(employee.getPassword())) {
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public List<SysPermissionSiderVo> listMyPermission() {
        List<SysPermission> allSysPermission = sysPermissionMapper.selectAllSysPermission();

        List<SysPermissionSiderVo> returnList = new ArrayList<>();
        for (SysPermission permission : allSysPermission) {
            SysPermissionSiderVo vo = new SysPermissionSiderVo();
            if (permission.getParentId() == 0) {
                vo.setId(permission.getId());
                vo.setParentId(permission.getParentId());
                vo.setText(permission.getText());
                vo.setIcon(permission.getIcon());
                vo.setLabel(permission.getLabel());
                vo.setSubmenu(new ArrayList<>());
                for (SysPermission child : allSysPermission) {
                    if (child.getParentId() == vo.getId()) {
                        vo.getSubmenu().add(child);
                    }
                }
            }
            returnList.add(vo);
        }

        return returnList;
    }
}

