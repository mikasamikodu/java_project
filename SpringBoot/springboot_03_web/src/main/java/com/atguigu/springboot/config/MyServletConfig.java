package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.MyFilter;
import com.atguigu.springboot.listener.MyServletListener;
import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServletConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        return bean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new MyFilter());
        bean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean myServletListener(){
        ServletListenerRegistrationBean<MyServletListener> bean = new ServletListenerRegistrationBean<MyServletListener>(new MyServletListener());
        return bean;
    }
}
