package com.yyc.songshu.manager.service.impl;

import com.yyc.songshu.manager.dao.SearchLogDAO;
import com.yyc.songshu.manager.service.SearchLogService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchLogServiceImpl implements SearchLogService {
    @Autowired
    private SearchLogDAO searchLogDAO;
    @Override
    public String wordHot() {
        return JsonUtil.jsonRe(searchLogDAO.selectKeyWord(), JsonResultUtil.error("200", "成功"));
    }
}
