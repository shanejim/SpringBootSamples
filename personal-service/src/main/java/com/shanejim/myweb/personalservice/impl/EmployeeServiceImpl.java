package com.shanejim.myweb.personalservice.impl;

import com.shanejim.myweb.personaldao.mapper.EmployeeMapper;
import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;
import com.shanejim.myweb.personalmodel.utils.DigestUtil;
import com.shanejim.myweb.personalservice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 11:07
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

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
        employee.setAddTime(new Date());
        employee.setModifiedTime(new Date());
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
        employee.setModifiedTime(new Date());
        String password = DigestUtil.sha256Digest(newPassword + employeeBefore.getSalt());


        return 0;
    }

    @Override
    public int deleteEmployee(Long id) {
        Employee employeeBefore = employeeMapper.selectByPrimaryKey(id);
        if (employeeBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "账号不存在或已被删除，请刷新后重试");
        }

        Employee employee = new Employee();
        employee.setId(id);
        employee.setModifiedTime(new Date());
        employee.setIsDeleted(new Byte("1"));
        return employeeMapper.updateByPrimaryKeySelective(employee);
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
}

