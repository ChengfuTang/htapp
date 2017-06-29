/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.controller;

import com.onebyone.service.PreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangchengfu on 2017/6/28.
 */
@RestController
public class PreController {
    @Autowired ( required = false )
    PreService preService;

    @RequestMapping ( "/" )
    public Map< String, Object > preAppMan ( ) {
        Map< String, Object > map = new HashMap<> ( );
        map.put ( "sJkNo", "1-101" );
        map.put ( "sMan_id", "0" );
        map.put ( "sParam1", "0" );
        map.put ( "sParam2", "0" );
        return preService.preAppMan ( map );
    }
}
