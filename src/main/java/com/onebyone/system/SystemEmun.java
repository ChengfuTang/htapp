/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.system;

/**
 * Created by tangchengfu on 2017/6/28.
 */
public class SystemEmun {
    public enum StringValueEmun {
        COPY_SUCCESS ( "拷贝成功" ), COPY_FAILD ( "拷贝失败" ), COPY_NO_FIND_SOUCE_FILE ( "没有找到源文件" ), FILE_UPLOAD_SUCCESS (
                "文件上传成功" ), FILE_UPLOAD_FAILED ( "文件上传失败" ), UPLOAD_FILE_ROOT_PATH ( "file.rootPath" ),
        UPLOAD_FILE_MAX_SIZE ( "file.maxSize" ), UPLOAD_FILE_TEMP_PATH ( "file.tempPath" ), UPLOAD_FILE_NOW_PATH (
                "file.nowPath" );
        private String Content;

        StringValueEmun ( String Content ) {
            this.Content = Content;
        }

        public String getContent ( ) {
            return this.Content;
        }
    }

    public enum PropertiesPath {
        FILE_UPLOAD_PROPERTIES ( "fileupload.properties" );
        private String path;

        PropertiesPath ( String path ) {
            this.path = path;
        }

        public String getPath ( ) {
            return path;
        }
    }
}
