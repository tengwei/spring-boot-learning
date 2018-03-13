package org.tw.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@SpringBootApplication
@EnableWebSecurity
public class MybatisApp
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext configurableApplicationContext=SpringApplication.run(MybatisApp.class, args);
        DataSource dataSource=configurableApplicationContext.getBean(DataSource.class);
    }
}
