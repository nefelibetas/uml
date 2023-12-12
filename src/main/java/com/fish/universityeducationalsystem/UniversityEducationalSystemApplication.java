package com.fish.universityeducationalsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fish.universityeducationalsystem.mapper")
public class UniversityEducationalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityEducationalSystemApplication.class, args);
    }

}
