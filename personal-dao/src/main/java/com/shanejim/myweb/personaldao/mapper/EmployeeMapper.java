package com.shanejim.myweb.personaldao.mapper;

import com.shanejim.myweb.personalmodel.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    Employee selectByName(String userName);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee checkLogin(@Param("userName") String userName, @Param("passWord") String passWord);

    List<Employee> selectAllEmployee();
}