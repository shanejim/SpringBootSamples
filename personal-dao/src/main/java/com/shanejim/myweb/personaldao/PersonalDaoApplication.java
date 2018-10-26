package com.shanejim.myweb.personaldao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PersonalDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalDaoApplication.class, args);
    }
}
