package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.AppContentDao;
import com.yyc.songshu.manager.pojo.AppContent;
import com.yyc.songshu.manager.service.AppContentService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppContentServiceImpl implements AppContentService {
    @Autowired
    private AppContentDao appContentDao;

    @Override
    public String getUse() {
        return JsonUtil.jsonRe(appContentDao.selectByPrimaryKey(2), JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String getPrivacy() {
        return JsonUtil.jsonRe(appContentDao.selectByPrimaryKey(1), JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String getTort() {
        return JsonUtil.jsonRe(appContentDao.selectByPrimaryKey(3), JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String getAll() {
        return JsonUtil.jsonRe(appContentDao.getAll(), JsonResultUtil.error("200", "成功"));
    }

    @Override
    public String updateContent(AppContent appContent) {
        int d  = appContentDao.updateByPrimaryKeySelective(appContent);
        if (d>0) {
            return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "失败"));
    }

    @Override
    public String delContent(int aId) {
        int d  = appContentDao.deleteByPrimaryKey(aId);
        if (d>0) {
            return JsonUtil.jsonRe(null, JsonResultUtil.error("200", "成功"));
        }
        return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "失败"));
    }


}
