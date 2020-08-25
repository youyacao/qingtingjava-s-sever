package com.yyc.songshu.manager.util;



import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.net.URL;


public class VideoUTtils {
    public static void main(String[] args) {
        System.out.println(readVideoTime("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=134238&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss"));
    }
    public synchronized static String readVideoTime(String fileUrl) {
        String length = null;
        try {
            URL url = new URL(fileUrl);
            File file = new File(url.getFile());
            MultimediaObject multimediaObject = new MultimediaObject(url);
            MultimediaInfo multimediaInfo = multimediaObject.getInfo();
            long ls = multimediaInfo.getDuration()/1000;
            int min = (int)(ls / 60);
            length = min +"分"+(ls-(min*60))+"秒";
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
        return length;
    }
}
