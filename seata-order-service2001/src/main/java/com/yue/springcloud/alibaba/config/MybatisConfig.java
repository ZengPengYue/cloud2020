package com.yue.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.yue.springcloud.alibaba.dao"})
public class MybatisConfig {
}
