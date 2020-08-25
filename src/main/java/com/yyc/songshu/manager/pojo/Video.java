package com.yyc.songshu.manager.pojo;

public class Video {
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String title;

    private String thumb;

    private String videoUrl;

    private Integer viewNum;

    private Integer likeNum;

    private Integer commentNum;

    private Integer collectNum;

    private String collectNumStr;

    private Integer shareNum;

    private String tags;

    private Integer status;

    private Integer updatedAt;

    private Integer createdAt;

    private Users users;

    private String shareNumStr;

    private String viewNumStr;

    private String commentNumStr;

    private String likeNumStr;

    private String duration;

    private String time;

    private String isCollect;

    private String isLike;

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getCollectNumStr() {
        return collectNumStr;
    }

    public void setCollectNumStr(String collectNumStr) {
        this.collectNumStr = collectNumStr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getShareNumStr() {
        return shareNumStr;
    }

    public void setShareNumStr(String shareNumStr) {
        this.shareNumStr = shareNumStr;
    }

    public String getViewNumStr() {
        return viewNumStr;
    }

    public void setViewNumStr(String viewNumStr) {
        this.viewNumStr = viewNumStr;
    }

    public String getCommentNumStr() {
        return commentNumStr;
    }

    public void setCommentNumStr(String commentNumStr) {
        this.commentNumStr = commentNumStr;
    }

    public String getLikeNumStr() {
        return likeNumStr;
    }

    public void setLikeNumStr(String likeNumStr) {
        this.likeNumStr = likeNumStr;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", viewNum=" + viewNum +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", shareNum=" + shareNum +
                ", tags='" + tags + '\'' +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", users=" + users +
                ", shareNumStr='" + shareNumStr + '\'' +
                ", viewNumStr='" + viewNumStr + '\'' +
                ", commentNumStr='" + commentNumStr + '\'' +
                ", likeNumStr='" + likeNumStr + '\'' +
                ", duration='" + duration + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}