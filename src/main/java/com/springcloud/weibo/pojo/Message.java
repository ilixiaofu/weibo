package com.springcloud.weibo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message {
    private Integer id;

    private String title;

    private Integer topicId;

    private Date createDate;

    private Integer forwardingCount;

    private Integer commentsCount;

    private Integer likesCount;

    private String isdel;

    private String ispublic;

    private String uid;

    private String content;

    private String collectionStatus;

    private User user;

    private List<Picture> pictures = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getForwardingCount() {
        return forwardingCount;
    }

    public void setForwardingCount(Integer forwardingCount) {
        this.forwardingCount = forwardingCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic == null ? null : ispublic.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", title=" + title + ", topicId=" + topicId + ", createDate=" + createDate
                + ", forwardingCount=" + forwardingCount + ", commentsCount=" + commentsCount + ", likesCount="
                + likesCount + ", isdel=" + isdel + ", ispublic=" + ispublic + ", uid=" + uid + ", content=" + content
                + "]";
    }
}