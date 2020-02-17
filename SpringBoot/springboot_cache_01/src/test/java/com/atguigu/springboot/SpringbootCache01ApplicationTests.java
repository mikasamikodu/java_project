package com.atguigu.springboot;

import com.atguigu.springboot.bean.Employee;
import com.atguigu.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootCache01ApplicationTests {

    @Autowired(required = false)
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate myRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /*
        redis常见的五大数据类型
            stirng(字符串)，hash(散列)，list(列表)，set(集合)，zset(有序集合)
            stringRedisTemplate.opsForValue();[stirng(字符串)]
            stringRedisTemplate.opsForHash();[hash(散列)]
            stringRedisTemplate.opsForList();[list(列表)]
            stringRedisTemplate.opsForSet();[set(集合)]
            stringRedisTemplate.opsForZset();[zset(有序集合)]
    * */

    @Test
    public void test1(){
//        stringRedisTemplate.opsForValue().append("msg", "hello world, redis!");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);
//        stringRedisTemplate.opsForSet().add("myset", "1");
//        stringRedisTemplate.opsForSet().add("myset", "2");
        Employee employee = employeeMapper.getEmployeeById(1);
        myRedisTemplate.opsForSet().add("emp1", employee);
    }


    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

}
