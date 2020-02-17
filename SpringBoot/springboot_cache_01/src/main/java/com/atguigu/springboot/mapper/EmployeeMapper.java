package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmployeeById(Integer id);

    @Select("select * from employee where lastName = #{lastName}")
    public Employee getEmployeeByLastName(String lastName);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender} where id = #{id}")
    public void update(Employee employee);

    @Delete("deleter from employee where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into employee(lastName,email,gender,dId) values(#{lastName},#{email},#{gender},#{dId})")
    public void insert(Employee employee);
}
