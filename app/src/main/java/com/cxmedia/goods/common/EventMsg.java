package com.cxmedia.goods.common;

public class EventMsg {
    private int msgType;
    private String msgValue;

    public EventMsg(int msgType) {
        this.msgType = msgType;
    }

    public EventMsg(int msgType, String msgValue) {
        this.msgType = msgType;
        this.msgValue = msgValue;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsgValue() {
        return msgValue;
    }

    public void setMsgValue(String msgValue) {
        this.msgValue = msgValue;
    }
}
