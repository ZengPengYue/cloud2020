package com.yue.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SleuthPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(SleuthPaymentMain8001.class,args);
    }
}
