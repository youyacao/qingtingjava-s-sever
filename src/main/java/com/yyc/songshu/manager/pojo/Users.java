package com.yyc.songshu.manager.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Users {
    private Integer id;

    private String username;

    private String phone;

    private String email;

    private String password;

    private String nickname;

    private String avatar;

    private String refcode;

    private String truename;

    private String desc;

    private String qq;

    private Boolean sex;

    private String birthday;

    private BigDecimal amount;

    private Integer integral;

    private Integer gold;

    private String alipayAccountName;

    private String alipayAccount;

    private Integer heat;

    private Integer vipEndTime;

    private Integer pid;

    private String apiToken;

    private String wxOpenid;

    private String qqOpenid;

    private String wbOpenid;

    private Boolean isSuper;

    private Date lastLoginTime;

    private Boolean status;

    private Integer updatedAt;

    private Integer createdAt;
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getSuper() {
        return isSuper;
    }

    public void setSuper(Boolean aSuper) {
        isSuper = aSuper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getAlipayAccountName() {
        return alipayAccountName;
    }

    public void setAlipayAccountName(String alipayAccountName) {
        this.alipayAccountName = alipayAccountName;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public Integer getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Integer vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWbOpenid() {
        return wbOpenid;
    }

    public void setWbOpenid(String wbOpenid) {
        this.wbOpenid = wbOpenid;
    }

    public Boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Boolean isSuper) {
        this.isSuper = isSuper;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", refcode='" + refcode + '\'' +
                ", truename='" + truename + '\'' +
                ", desc='" + desc + '\'' +
                ", qq='" + qq + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", amount=" + amount +
                ", integral=" + integral +
                ", gold=" + gold +
                ", alipayAccountName='" + alipayAccountName + '\'' +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", heat=" + heat +
                ", vipEndTime=" + vipEndTime +
                ", pid=" + pid +
                ", apiToken='" + apiToken + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
                ", qqOpenid='" + qqOpenid + '\'' +
                ", wbOpenid='" + wbOpenid + '\'' +
                ", isSuper=" + isSuper +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", sign='" + sign + '\'' +
                '}';
    }
}