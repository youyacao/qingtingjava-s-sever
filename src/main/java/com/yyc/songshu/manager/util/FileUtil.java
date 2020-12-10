package com.yyc.songshu.manager.util;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Encoder;
import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class FileUtil {

    private StringMap putPolicy;
    /*写入数据*/
    public static String writeImageLocalhost(String path,byte[] imageData){
        try {
            /*获取文件路径*/
            File pathFile = new File(path);
            /*文件写入流*/
            FileOutputStream imageOut = new FileOutputStream(pathFile);
            imageOut.write(imageData);
            imageOut.close();
            return "写入成功";
        }catch (IOException e){
            return "写入失败";
        }
    }
    public static String uploadQiNiu(MultipartFile multipartFile, Auth auth, String url, String bucket, StringMap putPolicy, UploadManager uploadManager) throws Exception {
        String fileName = multipartFile.getOriginalFilename();
        System.out.println(fileName+":"+url);
        File file = new File(url + fileName);
        multipartFile.transferTo(file);
        String token = auth.uploadToken(bucket,null,3600,putPolicy);
        Response response = uploadManager.put(file,null,token);
        //解析上传的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
        String imageName = putRet.hash;
        int retry = 0;
        while(response.needRetry() && retry < 3){
            response = uploadManager.put(file,null,token);
        }
        return imageName;

    }

    public static String readVideoTime(File file){
        Encoder encoder = new Encoder();
        String length = "";
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*springboot存取文件*/
    public static String formUpdateFile(MultipartFile file){
        //System.out.println(System.currentTimeMillis()/1000+":begin");
        //保存图片到服务器
        File filePath = new File(DataManage.getLocalImagePath());
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        System.out.println("------------------------------");
        String fileNameA = "";
    /*    if (file.isEmpty()) {
            return "Failure";
        }*/
        System.out.println("------------------------------");
        //获取后缀
        try {
            String suf = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().indexOf(".") + 1);
            fileNameA = UUID.randomUUID() +  "." + suf;
            FileUtils.writeByteArrayToFile(new File(DataManage.getLocalImagePath() + fileNameA),
                    file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
        //System.out.println(System.currentTimeMillis()/1000+":end");
        return DataManage.getLocalMysqlPath()+fileNameA;
    }

    /*读取数据*/
    public static byte[] readImageByte (String fileName){
        /*获得文件*/
        File imageFile = new File(fileName);
        /*判断文件是否存在*/
        if (!imageFile.exists()){
            return null;
        }
        ByteArrayOutputStream imageOut = new ByteArrayOutputStream((int)imageFile.length());
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(imageFile));
            int image_size = 1024;
            byte[] buffer = new byte[image_size];
            int len = 0;
            while (-1 !=(len = inputStream.read(buffer,0,image_size))){
                imageOut.write(buffer,0,len);
            }
            return imageOut.toByteArray();
        }catch (Exception e){
            return null;
        }finally {
            try {
                inputStream.close();
                imageOut.close();
            }catch (Exception e){
            }
        }
    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
        }
        return null;
    }

    //将Byte数组转换成文件
    public static String getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
           // System.out.println(getFileType(bytes)+"::文件类型");
            if (getFileType(bytes)==null){
                return "写入失败";
            }
            bos.write(bytes);
            return "写入成功";
        } catch (Exception e) {
            return "写入失败";
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }
    private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static String getFileType(byte[] fileByte){
        String type = Objects.requireNonNull(bytesToHexString(fileByte)).toUpperCase();
        if(type.contains("FFD8FF")){
            return "jpg";
        }else if(type.contains("89504E47")){
            return "png";
        }else if(type.contains("47494638")){
            return "gif";
        }else if(type.contains("49492A00")){
            return "tif";
        }else if(type.contains("424D")){
            return "bmp";
        }else{
            return null;
        }
    }
}
