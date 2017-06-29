/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.controller;

import com.onebyone.service.FileService;
import com.onebyone.system.SystemEmun;
import com.onebyone.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * htapp
 * Created by Chengfu.Tang on 2017/6/28.
 */
@RestController
@RequestMapping ( "/file" )
public class FileController {
    private static Logger logger = LoggerFactory.getLogger ( FileController.class );
    @Autowired
    FileService fileService;

    @RequestMapping ( "/upload" )
    public String fileUpload ( String fileStream, String fileName ) {
        try {
            String tempPath = fileService.fileUploadToTemp ( fileStream, fileName );
            if ( null != tempPath && ! "".equals ( tempPath ) ) {
                logger.info ( SystemEmun.StringValueEmun.FILE_UPLOAD_SUCCESS.getContent ( ) );
                return JsonMapper.toJsonMessage ( true, SystemEmun.StringValueEmun.FILE_UPLOAD_SUCCESS.getContent ( ) );
            }
            else {
                logger.error ( SystemEmun.StringValueEmun.FILE_UPLOAD_FAILED.getContent ( ) );
                return JsonMapper.toJsonMessage ( true, SystemEmun.StringValueEmun.FILE_UPLOAD_FAILED.getContent ( ) );
            }
        } catch ( Exception e ) {
            logger.error ( SystemEmun.StringValueEmun.FILE_UPLOAD_FAILED.getContent ( ) + e.getMessage ( ) );
            return JsonMapper.toJsonMessage ( false, SystemEmun.StringValueEmun.FILE_UPLOAD_FAILED.getContent ( ) );
        }
    }
}
