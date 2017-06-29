/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.utils;

import java.util.Date;

/**
 * htapp
 * Created by tangchengfu on 2017/6/29.
 */
public class DateUtil {
    public static String getTimestrapStr ( ) {
        return String.valueOf ( new Date ( ).getTime ( ) );
    }
}
