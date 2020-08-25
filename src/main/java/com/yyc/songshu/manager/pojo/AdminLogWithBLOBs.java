package com.yyc.songshu.manager.pojo;

public class AdminLogWithBLOBs extends AdminLog {
    private String datas;

    private String requests;

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}