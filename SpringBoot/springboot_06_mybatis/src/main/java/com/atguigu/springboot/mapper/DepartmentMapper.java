package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDepartmentById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDepartment(Department department);

    @Delete("delete from department where id=#{id}")
    public int deleterDepartmentById(Integer id);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDepartment(Department department);
}
