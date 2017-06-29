/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangchengfu on 2017/6/26.
 */
@Configuration
public class Druid2Init {
    @Bean
    @ConfigurationProperties ( prefix = "spring.datasource" )
    public DruidDataSource druidDataSource ( ) {
        return new DruidDataSource ( );
    }
}
