package com.itheima.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

public class JDBCConfig {

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name="runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    @Bean(name="dataSource")
    public DataSource createDataSourse(){
        try{
            ComboPooledDataSource source = new ComboPooledDataSource();
            source.setDriverClass(driverClass);
            source.setJdbcUrl(url);
            source.setUser(user);
            source.setPassword(password);
            return source;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
