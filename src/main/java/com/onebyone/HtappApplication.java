/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan ( "com.onebyone.mapper" )
@ServletComponentScan ( "com.onebyone.druid" )
@EnableAutoConfiguration
@EnableCaching
public class HtappApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( HtappApplication.class, args );
    }
}
