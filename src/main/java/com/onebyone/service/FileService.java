/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */
package com.onebyone.service;

import com.onebyone.system.SystemEmun;
import com.onebyone.utils.DateUtil;
import com.onebyone.utils.FileCopyUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by tangchengfu on 2017/6/28.
 */
@Service
public class FileService {
    //上传根目录
    private String rootPath;
    //现在的图片目录
    private String nowPath;
    //一个目录最大上传图片数量
    private Integer maxSize;
    //图片上传临时目录
    private String tempPath;


    /**
     * 图片上传实现方法
     *
     * @param tempPath
     *         图片临时目录路径
     * @param manId
     *         用户ID
     *
     * @return 0上传失败    1上传成功
     */
    public int commitUpload ( String tempPath, String manId ) throws IOException {
        //图片路径配置
        Properties properties = new Properties ( );
        InputStream is;
        OutputStream os = null;
        //获取class目录下的配置文件
        ClassLoader classLoader = this.getClass ( ).getClassLoader ( );
        URL url = classLoader.getResource ( SystemEmun.PropertiesPath.FILE_UPLOAD_PROPERTIES.getPath ( ) );
        File propertiesFile = new File ( url.getFile ( ) );
        is = new FileInputStream ( propertiesFile );
        //读取配置文件
        properties.load ( is );
        //获取图片上传参数
        rootPath = ( String ) properties.get ( SystemEmun.StringValueEmun.UPLOAD_FILE_ROOT_PATH.getContent ( ) );
        maxSize = Integer.valueOf ( ( String ) properties.get ( SystemEmun.StringValueEmun.UPLOAD_FILE_MAX_SIZE
                                                                        .getContent ( ) ) );
        nowPath = ( String ) properties.get ( SystemEmun.StringValueEmun.UPLOAD_FILE_NOW_PATH.getContent ( ) );
        //判断目前图片路径是否存在
        if ( null == nowPath || "".equals ( nowPath.trim ( ) ) ) {
            //如果不存在。新建一个目录名称并写入到配置文件中
            nowPath = DateUtil.getTimestrapStr ( );
            properties.setProperty ( "file.nowPath", nowPath );
            os = new FileOutputStream ( propertiesFile );
            os.flush ( );
            properties.store ( os, null );
        }
        //获取当前上传图片目录
        File newFileFiledPath = new File ( rootPath + File.separator + nowPath );
        //判断目录是否存在
        if ( ! newFileFiledPath.exists ( ) ) {
            //不存在则创建
            if ( ! newFileFiledPath.mkdirs ( ) ) {
                //目录创建失败
                return 0;
            }
        }
        //判断当前目录是否已经达到最大文件上限
        if ( newFileFiledPath.list ( ).length >= maxSize ) {
            //如果达到上限。新建一个目录名称并写入到配置文件中
            nowPath = DateUtil.getTimestrapStr ( );
            properties.setProperty ( "file.nowPath", nowPath );
            os = new FileOutputStream ( propertiesFile );
            properties.store ( os, null );
            os.flush ( );
            //新建目录
            newFileFiledPath = new File ( rootPath + File.separator + nowPath );
            if ( ! newFileFiledPath.exists ( ) ) {
                if ( ! newFileFiledPath.mkdirs ( ) ) {
                    //创建目录失败
                    return 0;
                }
            }
        }
        if ( null != is ) is.close ( );
        if ( null != os ) os.close ( );
        //生成时间戳文件名
        String fileNewName = manId + "-" + DateUtil.getTimestrapStr ( );
        //获取文件后缀名
        String fileExtension = tempPath.substring ( tempPath.lastIndexOf ( "." ), tempPath.length ( ) );
        String newFilePath = rootPath + File.separator + nowPath + File.separator + fileNewName + fileExtension;
        FileCopyUtil.doCopy ( tempPath, newFilePath, true, true );
        return 1;
    }


    public String fileUploadToTemp ( String fileStream, String fileName ) throws IOException {
        //获取二进制流
        String[] fileBytes = fileStream.split ( "," );
        byte[] file = new byte[ fileBytes.length ];
        for ( int i = 0 ; i < fileBytes.length ; i++ ) {
            file[ i ] = Byte.valueOf ( fileBytes[ i ] );
        }
        //读取配置文件，获取临时目录路径
        Properties properties = new Properties ( );
        InputStream is = this.getClass ( ).getClassLoader ( ).getResourceAsStream ( SystemEmun.PropertiesPath
                                                                                            .FILE_UPLOAD_PROPERTIES
                                                                                            .getPath ( ) );
        if ( null != is ) {
            properties.load ( is );
            tempPath = ( String ) properties.get ( SystemEmun.StringValueEmun.UPLOAD_FILE_TEMP_PATH.getContent ( ) );
            if ( null != is ) is.close ( );
            //临时文件路径
            String tempFilePath = tempPath + File.separator + UUID.randomUUID ( ).toString ( ) + fileName.substring (
                    fileName.lastIndexOf ( "." ), fileName.length ( ) );
            //临时文件目录
            File fileFiled = new File ( tempPath );
            //判断临时文件目录是否存在
            if ( ! fileFiled.exists ( ) ) {
                //创建临时文件目录
                if ( ! fileFiled.mkdirs ( ) ) {
                    return "";
                }
            }
            File tempFile = new File ( tempFilePath );
            if ( ! tempFile.exists ( ) ) {
                //新建文件
                tempFile.createNewFile ( );
            }
            FileOutputStream fos;
            //将图片文件写入
            fos = new FileOutputStream ( tempFile );
            fos.write ( file );
            fos.flush ( );
            if ( null != fos ) fos.close ( );
            return tempFilePath;
        }
        return "";
    }
}
