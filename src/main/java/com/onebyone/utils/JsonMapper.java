/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
    private static Map< Object, Object > map;
    private static ObjectMapper mapper = new ObjectMapper ( );

    public static String toJsonMessage ( boolean isSuccess, String message ) {
        JsonMapper.map = new HashMap<> ( );
        JsonMapper.map.put ( "statu", isSuccess );
        JsonMapper.map.put ( "message", message );
        String json = "";
        try {
            json = JsonMapper.mapper.writeValueAsString ( JsonMapper.map );
        } catch ( JsonProcessingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ( );
        }
        return json;
    }

    public static String toJsonMessage ( boolean isSuccess, String message, Object value ) {
        JsonMapper.map = new HashMap<> ( );
        JsonMapper.map.put ( "statu", isSuccess );
        JsonMapper.map.put ( "message", message );
        JsonMapper.map.put ( "value", value );
        String json = "";
        try {
            json = JsonMapper.mapper.writeValueAsString ( JsonMapper.map );
        } catch ( JsonProcessingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ( );
        }
        return json;
    }

    public static String toJsonMessage ( String message ) {
        JsonMapper.map = new HashMap<> ( );
        JsonMapper.map.put ( "message", message );
        String json = "";
        try {
            json = JsonMapper.mapper.writeValueAsString ( JsonMapper.map );
        } catch ( JsonProcessingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ( );
        }
        return json;
    }
}
