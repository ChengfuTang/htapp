/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.service;

import com.onebyone.mapper.PreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tangchengfu on 2017/6/28.
 */
@Service
@CacheConfig ( cacheNames = "preApp" )
public class PreService {
    @Autowired
    PreMapper preMapper;

    @Cacheable ( key = "'#{sJkNo},#{sMan_id}'" )
    public Map< String, Object > preAppMan ( Map map ) {
        preMapper.preAppMan ( map );
        return map;
    }
}
