package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public int insertEmployee(Employee employee);
}
