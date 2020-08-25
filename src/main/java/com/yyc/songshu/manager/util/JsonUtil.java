package com.yyc.songshu.manager.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/*返回Json数据处理类*/
public class JsonUtil {


    /*获取Jsonvalue*/
    public static String dataValue(String jsonData,String jsonKey){
        try {
            String jsonValue = new JsonParser().parse(jsonData).getAsJsonObject().get(jsonKey).getAsString();
            return jsonValue;
        }catch (Exception e){
            return null;
        }

    }

    /*返回值*/
    public static String jsonRe(Object data,JsonResultUtil message){
        JSONObject equipmentType = new JSONObject();
        JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue);
        equipmentType.put("data", data);
        equipmentType.put("message",message);

        return equipmentType.toJSONString();
    }

    /*Json转对象*/
    public static <T> T objectBean(String beanData,String beanName,Class<T> E){
        try {
            JsonObject jsonObject = new JsonParser().parse(beanData).getAsJsonObject();
            T beanGson = new Gson().fromJson(jsonObject.get(beanName).getAsJsonObject(),E);
            return beanGson;
        }catch (Exception e){
            return null;
        }
    }
    /*对象转Json*/
    public static String jsonBean(Object bean){
        try {
            Gson gson = new Gson();
            String jsonBean = gson.toJson(bean);
            return jsonBean;
        }catch (Exception e){
            return null;
        }
    }
    /*数组转对lsit集合*/
    public static <T> List<T> objectList(String json,Class E){
        try {
            Type type = new ParameterizedTypeImp(E);
            List<T> list = new  Gson().fromJson(json,type);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    /*数组转对象*/
    public static <T> T byteBean(String json,Class E){
        try {
            Type type = new ParameterizedTypeImp(E);
            T beanGson  = new Gson().fromJson(json,type);
            return beanGson;
        }catch (Exception e){
            return null;
        }
    }
    /*泛型类支持*/
    private static class ParameterizedTypeImp implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImp(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
