package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.Employee;
import com.shanejim.myweb.personalmodel.query.AddOrUpdateEmployeeQuery;

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
}
