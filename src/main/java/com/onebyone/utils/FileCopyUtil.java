/*
 * Copyright © 2017. Chengfu.Tang All rights reserved
 */

package com.onebyone.utils;

import java.io.*;

/**
 * Created by tangchengfu on 2017/6/28.
 */

public class FileCopyUtil {
    /**
     * @param fromPath
     *         复制文件路径
     * @param toPath
     *         目标文件路径
     * @param deleteSouce
     *         是否删除源文件 true为删除 false为不删除
     * @param overlay
     *         是否覆盖 true为覆盖 false为不覆盖
     *
     * @return -3源文件不存在 -2源文件不是文件 -1源文件已存在 0复制失败 1复制成功
     */
    public static int doCopy ( String fromPath, String toPath, boolean deleteSouce, boolean overlay ) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File fromFile = new File ( fromPath );
        //判断源文件是否存在
        if ( ! fromFile.exists ( ) ) {
            return - 2;
        }
        //判断源文件是否为文件
        if ( ! fromFile.isFile ( ) ) {
            return - 1;
        }

        File toFile = new File ( toPath );
        //判断目标文件是否存在
        if ( toFile.exists ( ) ) {
            //如果存在是否允许覆盖
            if ( overlay ) {
                new File ( toPath ).delete ( );
            }
            else {
                return - 1;
            }
        }
        else {
            //文件不存在是否存在目录
            if ( ! toFile.getParentFile ( ).exists ( ) ) {
                // 目标文件所在目录不存在
                if ( ! toFile.getParentFile ( ).mkdirs ( ) ) {
                    // 复制文件失败：创建目标文件所在目录失败
                    return 0;
                }
            }
        }
        int byteread;
        try {
            fis = new FileInputStream ( fromFile );
            fos = new FileOutputStream ( toFile );
            byte[] buffer = new byte[ fis.available ( ) ];
            while ( ( byteread = fis.read ( buffer ) ) != - 1 ) {
                fos.write ( buffer, 0, byteread );
            }
            return 1;
        } catch ( FileNotFoundException e ) {
            return 0;
        } catch ( IOException e ) {
            return 0;
        } finally {
            try {
                if ( fos != null ) fos.close ( );
                if ( fis != null ) fis.close ( );
            } catch ( IOException e ) {
                e.printStackTrace ( );
            }
            if ( deleteSouce ) {
                new File ( fromPath ).delete ( );
            }
        }
    }
}
