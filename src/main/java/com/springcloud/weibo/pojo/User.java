package com.springcloud.weibo.pojo;

import java.util.Date;

public class User {
    private String uid;

    private String nickName;

    private String realName;

    private String password;

    private String sex;

    private Date birthday;

    private Date registerDate;

    private String email;

    private String tel;

    private String qq;

    private String blogId;

    private String signature;

    private String introduce;

    private Integer localtionId;

    private Integer lv;

    private HeadImg headImg;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getLocaltionId() {
        return localtionId;
    }

    public void setLocaltionId(Integer localtionId) {
        this.localtionId = localtionId;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public HeadImg getHeadImg() {
        return headImg;
    }

    public void setHeadImg(HeadImg headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "User [uid=" + uid + ", nickName=" + nickName + ", realName=" + realName + ", password=" + password
                + ", sex=" + sex + ", birthday=" + birthday + ", registerDate=" + registerDate + ", email=" + email
                + ", tel=" + tel + ", qq=" + qq + ", blogId=" + blogId + ", signature=" + signature + ", introduce="
                + introduce + ", localtionId=" + localtionId + ", lv=" + lv + "]";
    }

}