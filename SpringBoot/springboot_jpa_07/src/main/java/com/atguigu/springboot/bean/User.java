package com.atguigu.springboot.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity//标注实体类
@Table(name = "tbl_user")//标注实体类与哪个数据库表进行绑定，如果数据库内没有这个表，jpa就会自动创建
public class User {

    @Id//标注这个属性对应的字段就是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置主键自增
    private Integer id;
    @Column(name = "last_name",length = 50)//指定这个属性在数据库表中有对应的字段，同时可以指定数据库表哪个字段与它进行对应,不指定就默认使用属性名与字段名对应
    private String lastName;
    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
