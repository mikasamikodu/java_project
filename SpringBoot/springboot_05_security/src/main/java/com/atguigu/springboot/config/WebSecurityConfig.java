package com.atguigu.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定义请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/level1/**").hasRole("VIP1")
            .antMatchers("/level2/**").hasRole("VIP2")
            .antMatchers("/level3/**").hasRole("VIP3");
        //开启自动配置的登录功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        /*
        配置formLogin()之后，会有如下功能
          1./login来到登录页
          2.当重定向到/login?error时表示登录失败
          3....
          4.默认post形式的/login是处理登录请求的
        */

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");
        //1./logout表示用户注销，清空session
        //2.点击注销后，会跳转到/login?logout的uri
        //3.配置.logoutSuccessUrl()可以指定注销后跳转的页面

        //开启记住我的功能
        http.rememberMe().rememberMeParameter("remember");
        //登陆成功后，会将cookie发给浏览器保存，日后访问页面时带上这个cookie，只要通过检查就可以免登录
        //点击注销时也会清除cookie
    }

    //定义认证规则

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("tom").password("1234").roles("VIP1", "VIP2")
                .and()
                .withUser("rose").password("1234").roles("VIP2", "VIP3")
                .and()
                .withUser("jack").password("1234").roles("VIP3", "VIP1");
    }
}
