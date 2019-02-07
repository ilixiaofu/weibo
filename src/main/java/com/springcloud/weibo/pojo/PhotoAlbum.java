package com.springcloud.weibo.pojo;

import java.util.Date;

public class PhotoAlbum {
    private Integer id;

    private String name;

    private Date craeteDate;

    private String isDel;

    private String uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCraeteDate() {
        return craeteDate;
    }

    public void setCraeteDate(Date craeteDate) {
        this.craeteDate = craeteDate;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }
}