package cn.penglei.teacherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"cn.penglei.mapper","cn.penglei.teacherservice.controller","cn.penglei.teacherservice.service"})
@EnableEurekaClient
public class TeacherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeacherServiceApplication.class,args);
    }
}
