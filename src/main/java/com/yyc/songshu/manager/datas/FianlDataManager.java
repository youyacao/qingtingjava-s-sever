package com.yyc.songshu.manager.datas;

public class FianlDataManager {
    private static  String accessKeyId = "LTAIyjKGoQlvJ7rr";

    private static  String secret = "n8uJvRZb7ysOsn3irr6baWxJoRdrcr";

    private static  String regionId = "cn-hangzhou";

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getRegionId() {
        return regionId;
    }

    public static void setAccessKeyId(String accessKeyId) {
        FianlDataManager.accessKeyId = accessKeyId;
    }

    public static void setSecret(String secret) {
        FianlDataManager.secret = secret;
    }

    public static void setRegionId(String regionId) {
        FianlDataManager.regionId = regionId;
    }
}
