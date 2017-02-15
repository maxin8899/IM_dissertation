package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haizhi on 2017/2/6.
 */
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@EnableEurekaClient
@ComponentScan
@Slf4j
public class UserApplication {
    public static void main(String[] args) {
        log.info("啊哈哈".toLowerCase());
        new SpringApplicationBuilder(UserApplication.class).web(true).run(args);
    }
}
