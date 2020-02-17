package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class Springboot03WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03WebApplication.class, args);
    }

    @Bean
    public ViewResolver MyViewResolver(){
        return new MyViewResolver();
    }

    private class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
