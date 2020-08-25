package com.yyc.songshu.manager.controller;

import com.yyc.songshu.manager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * @param commentData 评论数据
     * @return 成功与否
     */
    @RequestMapping(method = RequestMethod.POST,value = "/commentList")
    public String getCommentList(@RequestBody String commentData){
        return commentService.selectCommentAll(commentData);
    }

    /**
     * @param commentData 评论数据
     * @return 获取评论
     */
    @RequestMapping(method = RequestMethod.POST,value = "/addComment")
    public String addComment(@RequestBody String commentData){
        return commentService.addComment(commentData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/childComment")
    public String getChildComment(@RequestBody String commentData){
        return commentService.getChildComment(commentData);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getAllComment")
    public String getAllComment(@RequestParam(value = "limit")int limit,@RequestParam(value = "page")int page){
        return commentService.getAllComment(limit,page);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/delComment")
    public String delComment(@RequestParam(value = "cId")int cId){
        return commentService.delComment(cId);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/likeComment")
    public String likeComment(@RequestBody String commentData){
        return commentService.likeOnComment(commentData);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/likeOffComment")
    public String likeOffComment(@RequestBody String commentData){
        return commentService.likeOffComment(commentData);
    }
}
