package com.yyc.songshu.manager.service;

public interface CategoryService {
    /**
     * @param pid 父类id
     * @return 所有分类
     */
    String getAllCategory();

    String getAllCategoryManager(int limit,int page);
}
