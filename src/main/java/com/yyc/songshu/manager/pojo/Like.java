package com.yyc.songshu.manager.pojo;

public class Like {
    private Integer id;

    private Integer type;

    private Integer userId;

    private Integer vid;

    private Integer status;

    private Integer updatedAt;

    private Integer createdAt;

    private Users users;

    private Video video;

    public Users getUsers() {

        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        return "Like{" +
                "id=" + id +
                ", type=" + type +
                ", userId=" + userId +
                ", vid=" + vid +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", users=" + users +
                ", video=" + video +
                '}';
    }
}