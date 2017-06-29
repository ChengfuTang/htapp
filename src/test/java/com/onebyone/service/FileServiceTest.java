/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.service;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * htapp
 * Created by tangchengfu on 2017/6/28.
 */
public class FileServiceTest {
    @Test
    public void fileUpload ( ) throws Exception {
        FileService fileService = new FileService ( );
        File file = new File ( "G:\\照片\\IMG_20170522_195749.jpg" );
        InputStream is = new FileInputStream ( file );
        BufferedInputStream bis = new BufferedInputStream ( is );
        byte[] bytes = new byte[ bis.available ( ) ];
        bis.read ( bytes, 0, bis.available ( ) );
        StringBuffer sb = new StringBuffer ( );
        for ( int i = 0 ; i < bytes.length ; i++ ) {
            sb.append ( bytes[ i ] + "," );
        }
        String tempPath = fileService.fileUploadToTemp ( sb.toString ( ), "IMG_20170522_195749.jpg" );
        System.out.println ( tempPath );
        int result = fileService.commitUpload ( tempPath, "001" );
        System.out.println ( result );
    }

}