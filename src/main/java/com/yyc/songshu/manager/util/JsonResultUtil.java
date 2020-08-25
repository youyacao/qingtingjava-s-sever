package com.yyc.songshu.manager.util;
/*
* 该类是返回前端页面的数据绑定类
* */

public class JsonResultUtil {

    private String msg;      //提示信息
    private String code;    //状态码
    public JsonResultUtil(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public JsonResultUtil( ) {

    }

    public static JsonResultUtil error(String code,String msg){
        return new JsonResultUtil(code,msg);
    }

    public static JsonResultUtil ok(String code,String msg){
        return new JsonResultUtil(code,msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "JsonResultUtil{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
