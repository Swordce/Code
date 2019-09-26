package com.cxmedia.goods.MVP.model;

public class HomeOrderInfo {

    /**
     * indexOrderData : {"realPayCount":0,"orderCount":0,"couponCount":0}
     * respMsg : 交易成功
     * respCode : 0000
     */

    private IndexOrderDataBean indexOrderData;
    private String respMsg;
    private String respCode;

    public IndexOrderDataBean getIndexOrderData() {
        return indexOrderData;
    }

    public void setIndexOrderData(IndexOrderDataBean indexOrderData) {
        this.indexOrderData = indexOrderData;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public static class IndexOrderDataBean {
        /**
         * realPayCount : 0
         * orderCount : 0
         * couponCount : 0
         */

        private int realPayCount;
        private int orderCount;
        private int couponCount;

        public int getRealPayCount() {
            return realPayCount;
        }

        public void setRealPayCount(int realPayCount) {
            this.realPayCount = realPayCount;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }
    }
}
