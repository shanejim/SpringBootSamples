package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.vo.EmployeeVo;
import com.shanejim.myweb.personalmodel.vo.SysPermissionSiderVo;

import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 10:55
 **/
public interface EmployeeService {
    Employee checkLogin(String username, String password);

    int insertEmployee(AddOrUpdateEmployeeQuery employeeDto);

    int deleteEmployee(Long id);

    int modifyPassword(Long id, String newPassword);

    Employee findEmployeeByUSerName(String username);

    PagingReturn<EmployeeVo> listEmployee(Integer pageNum, Integer pageSize, String keywords, String sort);

    List<SysPermissionSiderVo> listMyPermission();
}
