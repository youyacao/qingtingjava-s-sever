package com.yyc.songshu.manager.util;

public class DataManage {
    private final static String LOCAL_IMAGE_PATH = "/usr/local/image/";
    //private final static String LOCAL_IMAGE_PATH = "C:\\Users\\10481\\Desktop\\text\\";

    private final static String LOCAL_MYSQL_PATH = "http://122.114.195.108:8111/images/";

    private final static String FFMP_PATH = "D:\\谷歌下载\\ffmpeg_exe_downyi.com\\ffmpeg.exe";

    public static String getFfmpPath() {
        return FFMP_PATH;
    }

    public static String getLocalImagePath() {
        return LOCAL_IMAGE_PATH;
    }

    public static String getLocalMysqlPath() {
        return LOCAL_MYSQL_PATH;
    }
}
