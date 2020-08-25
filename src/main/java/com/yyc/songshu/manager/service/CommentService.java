package com.yyc.songshu.manager.service;

public interface CommentService {
    String addComment(String commentData);

    String selectCommentAll(String commentData);

    String getChildComment(String data);

    String getAllComment(int limit,int page);

    String delComment(int id);

    String likeOnComment(String commentId);

    String likeOffComment(String commentId);
}
