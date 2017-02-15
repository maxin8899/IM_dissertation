package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by haizhi on 2017/1/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaServer
@ComponentScan
@EnableZuulProxy
@Slf4j
public class StartApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(StartApplication.class).web(true).run(args);
    }
}
