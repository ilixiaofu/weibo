package com.springcloud.weibo.pojo;

public class HotList {
    private Integer id;

    private Integer hotValue;

    private Integer msgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotValue() {
        return hotValue;
    }

    public void setHotValue(Integer hotValue) {
        this.hotValue = hotValue;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }
}