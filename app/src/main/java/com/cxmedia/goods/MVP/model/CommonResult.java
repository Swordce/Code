package com.cxmedia.goods.MVP.model;

public class CommonResult {

    /**
     * respCode : 0000
     * respMsg : 交易成功
     */

    private String respCode;
    private String respMsg;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
