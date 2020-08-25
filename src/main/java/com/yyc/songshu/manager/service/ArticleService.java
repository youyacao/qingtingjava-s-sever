package com.yyc.songshu.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {

    /**
     * @param dataList 参数集合
     * @return 图文列表
     */
    String selectArticleList(String dataList);

    /**
     * @param file 文件流数据
     * @param data 其余分类信息
     * @return 成功与否
     */
    String uploadArticle(MultipartFile file, String data);
}
