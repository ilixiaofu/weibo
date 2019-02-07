package com.springcloud.weibo.pojo;

public class FocusUser {
    private String uid;

    private String focusUid;

    private Integer focusGroupId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getFocusUid() {
        return focusUid;
    }

    public void setFocusUid(String focusUid) {
        this.focusUid = focusUid == null ? null : focusUid.trim();
    }

    public Integer getFocusGroupId() {
        return focusGroupId;
    }

    public void setFocusGroupId(Integer focusGroupId) {
        this.focusGroupId = focusGroupId;
    }
}