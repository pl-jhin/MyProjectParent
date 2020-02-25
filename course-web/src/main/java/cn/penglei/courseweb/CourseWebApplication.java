package cn.penglei.courseweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//打开eureka
@EnableDiscoveryClient
//打开feign
@EnableFeignClients
//打开Hystrix
@EnableHystrix
public class CourseWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseWebApplication.class, args);
    }

}
