package com.yyc.songshu.manager.util;

public class DataManage {
    private final static String LOCAL_IMAGE_PATH = "/usr/local/web/songshu/images/";
    //private final static String LOCAL_IMAGE_PATH = "C:\\Users\\10481\\Desktop\\text\\";

    private final static String LOCAL_MYSQL_PATH = "http://122.114.79.134:8111/images/";

    private final static String FFMP_PATH = "D:\\谷歌下载\\ffmpeg_exe_downyi.com\\ffmpeg.exe";

    private final static String QINIU_AK = "7Swd0fkWhx8P5IznfkF2t_5vMcik2_Vwt1JqLJOe";
    private final static String QINIU_SK = "XwYhJU5xdsqDVKbwC03eljUW2KpyByLzYnf_h6gf";
    private final static String QINIU_BUCKET = "songshuguanfang";
    private final static String QINIU_PATH = "https://cun.youyacao.com/";
    //private final static String QINIU_LOCAL_PATH = "C:\\Users\\10481\\Desktop\\text\\";
    private final static String QINIU_LOCAL_PATH = "/www/wwwroot/qingtingjavaapi.youyacao.com/static/";

    public static String getQiniuLocalPath() {
        return QINIU_LOCAL_PATH;
    }

    public static String getQiniuAk() {
        return QINIU_AK;
    }

    public static String getQiniuSk() {
        return QINIU_SK;
    }

    public static String getQiniuBucket() {
        return QINIU_BUCKET;
    }

    public static String getQiniuPath() {
        return QINIU_PATH;
    }

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
