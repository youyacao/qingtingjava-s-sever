package com.yyc.songshu.manager.pojo;

public class Comment {
    private Integer id;

    private Integer type;

    private Integer vid;

    private Integer userId;

    private String content;

    private Integer likeNum;

    private Integer pid;

    private Integer commentId;

    private Boolean status;

    private Integer updatedAt;

    private Integer createdAt;

    private String likeNumStr;

    private Users users;

    private String time;

    private int commentCount;

    private String isLike;

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
        return "Comment{" +
                "id=" + id +
                ", type=" + type +
                ", vid=" + vid +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", pid=" + pid +
                ", commentId=" + commentId +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", likeNumStr='" + likeNumStr + '\'' +
                ", users=" + users +
                ", time='" + time + '\'' +
                ", commentCount=" + commentCount +
                ", isLike='" + isLike + '\'' +
                '}';
    }
}