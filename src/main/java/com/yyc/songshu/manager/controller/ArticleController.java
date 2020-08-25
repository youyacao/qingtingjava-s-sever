package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * @param data 参数数组
     * @return 图文；列表
     */
    @RequestMapping(method = RequestMethod.POST,value = "/articleList")
    public String getArticleList(@RequestBody String data){
        return articleService.selectArticleList(data);
    }


    /**
     * @param file 文件流数据
     * @param data 其余分类信息
     * @return 成功与否
     */
    @RequestMapping(method = RequestMethod.POST,value = "/uploadArticle")
    public String uploadArticle(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "data",required = false)String data){
        return articleService.selectArticleList(data);
    }

}
