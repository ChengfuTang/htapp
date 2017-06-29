/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder application ) {
        return application.sources ( HtappApplication.class );
    }

}
