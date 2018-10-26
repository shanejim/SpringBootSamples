package com.shanejim.myweb.personaladmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.shanejim")
@MapperScan("com.shanejim.myweb.personaldao.mapper")
public class PersonalAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalAdminApplication.class, args);
    }
}
