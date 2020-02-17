package com.atguigu.springboot.service;

import com.atguigu.springboot.bean.Employee;
import com.atguigu.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Autowired(required = false)
    private EmployeeMapper employeeMapper;

    /*@Cacheable作用：
        将方法结果进行缓存，以后再要相同的数据，直接从缓存中获取，不用调用方法；
        CacheManager管理多个Cache组件,对缓存的crud操作即使在cache组件中，每个cache组件都有自己唯一的名字
        @Cacheable的几个属性：
              value/cacheNames：指定缓存组件的名字
              key:缓存数据库使用的key，可以用它来指定。默认是使用的方法参数，(默认情况下,传入参数1，则缓存内的值是1-方法结果)
                  key还可以是spEL表达式：如#id，就是将传入的参数指定为key;
                                       #root.args[0]，是将传入参数数组的第一个参数指定为key
              keyGenerator:key的生成器，与key作用相同，因此二者只能指定一个
              cacheManager:指定Cache组件所属的管理器
              cacheResolver：作用与cacheManager相同，二选一
              condition:指定成立条件，条件成立才会进行缓存
              unless:指定成立条件，条件成立方法结果不会进行缓存，可以通过spEL表达--#result（方法结果）指定成立条件
              sync:是否使用异步模式
         原理：
            1.自动配置类：CacheAutoConfiguration
            2.缓存的配置类
                org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
                org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
                org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
                org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
                org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
                org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
                org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
                org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
                org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
                org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
            3.默认SimpleCacheConfiguration这个配置类默认生效，在其他缓存如redis的依赖未导入的情况下目标
            SimpleCacheConfiguration的作用
            4.向容器中添加了一个id为cacheManager的ConcurrentMapCacheManager缓存组件
            5.获取和创建 ConcurrentMapCache类型的缓存组件(先是获取，获取不到再进行创建并返回)，作用是将数据保存在ConcurrentMap中
            运行流程：@Cacheable
                1.当请求数据时，首先查询相关的缓存组件(Cache)，按照cacheNames指定的名字进行查询
                    (CacheManager先获取相关缓存组件)，第一次获取如果没有就进行自动创建
                    Cache cache = this.cacheMap.get(name);
                2.去cache中查找缓存的内容，使用一个key，默认是方法参数；
                    key时按照某种策略生成的。默认使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
                    SimpleKeyGenerator的默认生成key策略：
                        如果目标方法没有参数，就key=new SimpleKey();
                        如果目标方法有一个参数param，就key=param；
                        如果目标方法有多个参数params，就key=new SimpleKey(params);
                3.没有查询到缓存就调用目标方法
                4.将目标方法的结果放入缓存

                @Cacheable标注的方法执行之前先来检查缓存中是否有没有这个数据，默认是按照参数的值作为key去查询缓存如果没有就运行方法，
                并将方法的结果放入缓存

                核心：
                    1）使用CacheManager[ConcurrentMapCacheManager]按照名字查询缓存组件Cache[ConcurrentMapCache]
                    2) key使用keyGenerator生成的，默认是SimpleKeyGenerator

            @Cacheable的几个属性：
              value/cacheNames：指定缓存组件的名字
                cacheNames = {"emp"}   value = {"emp"}
              key:缓存数据库使用的key，可以用它来指定。默认是使用的方法参数，(默认情况下,传入参数1，则缓存内的值是1-方法结果)
                  key还可以是spEL表达式：如#id，就是将传入的参数指定为key;
                                       #root.args[0]，是将传入参数数组的第一个参数指定为key
                  key = "#root.methodName+'['+#id+']'"
              keyGenerator:key的生成器，与key作用相同，因此二者只能指定一个
                    自己实现keyGenerator的接口bong注册到ioc容器，名为mykeyGenerator
                   keyGenerator = "mykeyGenerator"
              cacheManager:指定Cache组件所属的管理器
              cacheResolver：作用与cacheManager相同，二选一
              condition:指定成立条件，条件成立才会进行缓存
                condition = "#id>0"  参数列表中参数id大于0时将方法结果进行缓存
                condition = "#a0>0"  参数列表中第一个参数大于0时将方法结果进行缓存
                condition = "#a0 eq '1' and #a0>0" 参数列表中第一个参数大于0且等于1时将方法结果进行缓存
              unless:指定成立条件，条件成立方法结果不会进行缓存，可以通过spEL表达--#result（方法结果）指定成立条件
                unless = "#result == null" 当方法结果为空时不进行缓存
              sync:是否使用异步模式
                当sync=true时，unless将不会起作用
    * */
    @Cacheable(value = {"emp"}, key = "#id")
    public Employee getEmployee(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;
    }

    /*
    @CachePut既调用方法又更新缓存数据
        修改数据库的某个数据，同时更新缓存
        运行时机：
            1.先调用目标方法
            2.将目标方法的结果缓存起来
        这个注解的属性与 @Cacheable相同

        测试步骤：
            1.查询1号员工；将查询的结果放入缓存中
            2.以后查询还是之前的结果
            3.更新1号员工；
            4.查询1号员工，查询结果是更新后的结果
    * */
    @CachePut(/*cacheNames = {"emp"}, */key = "#employee.id")
    public Employee updateEmployee(Employee employee){
        System.out.println("更新"+employee.getId()+"号员工");
        employeeMapper.update(employee);
        return employee;
    }

    /**
     @CacheEvict清除指定缓存中指定的key的缓存
        也可以通过指定allEntries = true 将指定缓存中的所有缓存清除
        beforeInvocation = true 使得缓存在方法执行之前清除
     */
    @CacheEvict(/*value = "emp", */key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("删除了"+id+"号员工");
    }

    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp", */keyGenerator = "myKeyGenerator")
            },
            put = {
                   @CachePut(/*value = "emp", */key = "#lastName"),
                   @CachePut(/*value = "emp", */key = "#result.id"),
            }
    )
    public Employee getByNameEmp(String lastName){
        System.out.println("查询了名为"+lastName+"的员工");
        return employeeMapper.getEmployeeByLastName(lastName);
    }
}
