package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @param pid 父类ID
     * @return 所有分类
     */
    @RequestMapping(method = RequestMethod.POST,value = "/category")
    public String getCategory(){
        return categoryService.getAllCategory();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/categoryAll")
    public String getCategory(@RequestParam(value = "limit")int limit,@RequestParam(value = "page")int page){
        return categoryService.getAllCategoryManager(limit,page);
    }
}
