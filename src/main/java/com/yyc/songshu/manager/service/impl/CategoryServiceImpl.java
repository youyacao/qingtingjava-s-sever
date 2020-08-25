package com.yyc.songshu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.songshu.manager.dao.CategoryDAO;
import com.yyc.songshu.manager.pojo.Category;
import com.yyc.songshu.manager.service.CategoryService;
import com.yyc.songshu.manager.util.JsonResultUtil;
import com.yyc.songshu.manager.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    private static Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    public String getAllCategory() {
        try {
            List<Category> categories = categoryDAO.selectAllByPid();
            return JsonUtil.jsonRe(categories, JsonResultUtil.error("200", "成功"));
        }catch (Exception e){
            logger.error(e+":所有分类");
            return JsonUtil.jsonRe(null, JsonResultUtil.error("400", "请求错误"));
        }
    }

    @Override
    public String getAllCategoryManager(int limit, int page) {
        PageHelper.startPage(limit,page);
        List<Category> users = categoryDAO.selectAllByPid();
        PageInfo<Category> usersPageInfo = new PageInfo<>(users);
        return JsonUtil.jsonRe(usersPageInfo, JsonResultUtil.ok("200", "成功"));
    }
}
