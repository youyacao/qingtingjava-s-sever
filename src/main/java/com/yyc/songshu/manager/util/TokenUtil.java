package com.yyc.songshu.manager.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    // 编码密码,可自定义
    private static final String ENCODED_PASSWORD = "evansw";
    private static Map<String,String> MAP_TOKENS = new HashMap<String,String>();
    private static final int VALID_TIME = 60*60*2; // token有效期(秒)
    public static final String TOKEN_ERROR = "F"; // 非法
    public static final String TOKEN_OVERDUE = "G"; // 过期
    public static final String TOKEN_FAILURE = "S"; // 失效


    /**
     * 编码
     * @param str
     * @return
     */
    public static String encoded(String str) {
        return strToHex(encodedString(str, ENCODED_PASSWORD));
    }

    public static String MD(String str) {//项目中的
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] b = md5.digest();

            StringBuilder sb = new StringBuilder("");
            for (int value : b) {
                int i = value;
                if (i < 0) i += 256;
                if (i < 16) sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();  //32位加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 转换
     * @param str
     * @param password
     * @return
     */
    private static String encodedString(String str, String password) {
        char[] pwd = password.toCharArray();
        int pwdLen = pwd.length;

        char[] strArray = str.toCharArray();
        for (int i=0; i<strArray.length; i++) {
            strArray[i] = (char)(strArray[i] ^ pwd[i%pwdLen] ^ pwdLen);
        }
        return new String(strArray);
    }

    private static String strToHex(String s) {
        return bytesToHexStr(s.getBytes());
    }

    private static String bytesToHexStr(byte[] bytesArray) {
        StringBuilder builder = new StringBuilder();
        String hexStr;
        for (byte bt : bytesArray) {
            hexStr = Integer.toHexString(bt & 0xFF);
            if (hexStr.length() == 1) {
                builder.append("0");
                builder.append(hexStr);
            }else{
                builder.append(hexStr);
            }
        }
        return builder.toString();
    }

    /**
     * 解码
     * @param str
     * @return
     */
    public static String decoded(String str) {
        String hexStr = null;
        try {
            hexStr = hexStrToStr(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hexStr != null) {
            hexStr = encodedString(hexStr, ENCODED_PASSWORD);
        }
        return hexStr;
    }

    private static String hexStrToStr(String hexStr) {
        return new String(hexStrToBytes(hexStr));
    }

    private static byte[] hexStrToBytes(String hexStr) {
        String hex;
        int val;
        byte[] btHexStr = new byte[hexStr.length()/2];
        for (int i=0; i<btHexStr.length; i++) {
            hex = hexStr.substring(2*i, 2*i+2);
            val = Integer.valueOf(hex, 16);
            btHexStr[i] = (byte) val;
        }
        return btHexStr;
    }
    /**
     * 生成token,该token长度不一致,如需一致,可自行MD5或者其它方式加密一下
     * 该方式的token只存在磁盘上,如果项目是分布式,最好用redis存储
     * @param str: 该字符串可自定义,在校验token时要保持一致
     * @return
     */
    public static String getToken(String str) {
        String token = encoded(getCurrentTime()+","+str);
        MAP_TOKENS.put(str, token);
        return token;
    }

    /**
     * 校验token的有效性
     * @param token
     * @return
     */
    public static String checkToken(String token) {
        if (token == null) {
            return TOKEN_ERROR;
        }
        try{
            String[] tArr = decoded(token).split(",");
            if (tArr.length != 2) {
                return TOKEN_ERROR;
            }
            // token生成时间戳
            int tokenTime = Integer.parseInt(tArr[0]);
            // 当前时间戳
            int currentTime = getCurrentTime();
            if (currentTime-tokenTime < VALID_TIME) {
                String tokenStr = tArr[1];
                String mToken = MAP_TOKENS.get(tokenStr);
                if (mToken == null) {
                    return TOKEN_OVERDUE;
                } else if(!mToken.equals(token)) {
                    return TOKEN_FAILURE;
                }
                return tokenStr;
            } else {
                return TOKEN_OVERDUE;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return TOKEN_ERROR;
    }

    /**获取当前时间戳（10位整数）*/
    public static int getCurrentTime() {
        return (int)(System.currentTimeMillis()/1000);
    }

    /**
     * 移除过期的token
     */
    public static void removeInvalidToken() {
        int currentTime = getCurrentTime();
        for (Map.Entry<String,String> entry : MAP_TOKENS.entrySet()) {
            String[] tArr = decoded(entry.getValue()).split(",");
            int tokenTime = Integer.parseInt(tArr[0]);
            if(currentTime-tokenTime > VALID_TIME){
                MAP_TOKENS.remove(entry.getKey());
            }
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String str = "username_and_password";

        // 获取token
        String token = getToken(str);
        System.out.println("token Result: " + token);

        // 校验token
        String checkToken = checkToken(token);
        System.out.println("checkToken Result: " + checkToken);
        if(str.equals(checkToken)) {
            System.out.println("==>token verification succeeded!");
        }

    }


}
