/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by tangchengfu on 2017/6/26.
 * Druid2Sql监控配置
 */
@WebFilter ( filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = { @WebInitParam ( name =
        "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*" )// 忽略资源
} )
public class Druid2Filter extends WebStatFilter {

}

