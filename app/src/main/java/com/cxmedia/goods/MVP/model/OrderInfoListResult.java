package com.cxmedia.goods.MVP.model;

import java.util.List;

public class OrderInfoListResult {

    /**
     * total : 7
     * rows : [{"orderNo":"ad85d33e0cfb4764bb13ddc70a91a7a1","orderTime":"2019-08-27 22:14:06","transactionTime":null,"payChannelType":"10","orderType":"1000","memberId":"310000016001037025","transStat":"S","refundAmt":0.12,"orderAmt":0.1,"openId":"onhNzwpDvl51kP0OTxspEPO2FEBI","buyerId":null,"goodsDesc":"测试商品","payTypeDetail":"1022","merchandiseFee":0,"couponId":null,"couponType":null,"discount":null,"fullAmt":null,"realAmt":-0.02,"preferentialAmt":null,"updateTime":"2019-08-27 23:42:35","createTime":"2019-08-27 22:14:14","outTransId":"4200000379201908270108096573","refundOrderNos":"7cbb82cf93f3420b98637ce04a8ccfcf,7cbb82cf93f3420b98637ce04a8ccfcf,baee79ea636f4def972c83acb3ece9ff,baee79ea636f4def972c83acb3ece9ff,baee79ea636f4def972c83acb3ece9ff,98881e6b24274c429217b1db03c06a34,3f332db10ba34ae6a82bc39330f81684,242c4206f3f043c5a317dd0c517eac0b,242c4206f3f043c5a317dd0c517eac0b,798a11853c2d412f9c02528bc47772c2,ed428e5ec8114965b2c69b2697ec9222,1419ba7cc88f45408f7d8f6d7a791470"}]
     * respCode : 0000
     * respMsg : 交易成功
     */

    private int total;
    private String respCode;
    private String respMsg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * orderNo : ad85d33e0cfb4764bb13ddc70a91a7a1
         * orderTime : 2019-08-27 22:14:06
         * transactionTime : null
         * payChannelType : 10
         * orderType : 1000
         * memberId : 310000016001037025
         * transStat : S
         * refundAmt : 0.12
         * orderAmt : 0.1
         * openId : onhNzwpDvl51kP0OTxspEPO2FEBI
         * buyerId : null
         * goodsDesc : 测试商品
         * payTypeDetail : 1022
         * merchandiseFee : 0.0
         * couponId : null
         * couponType : null
         * discount : null
         * fullAmt : null
         * realAmt : -0.02
         * preferentialAmt : null
         * updateTime : 2019-08-27 23:42:35
         * createTime : 2019-08-27 22:14:14
         * outTransId : 4200000379201908270108096573
         * refundOrderNos : 7cbb82cf93f3420b98637ce04a8ccfcf,7cbb82cf93f3420b98637ce04a8ccfcf,baee79ea636f4def972c83acb3ece9ff,baee79ea636f4def972c83acb3ece9ff,baee79ea636f4def972c83acb3ece9ff,98881e6b24274c429217b1db03c06a34,3f332db10ba34ae6a82bc39330f81684,242c4206f3f043c5a317dd0c517eac0b,242c4206f3f043c5a317dd0c517eac0b,798a11853c2d412f9c02528bc47772c2,ed428e5ec8114965b2c69b2697ec9222,1419ba7cc88f45408f7d8f6d7a791470
         */

        private String orderNo;
        private String orderTime;
        private String transactionTime;
        private String payChannelType;
        private String orderType;
        private String memberId;
        private String transStat;
        private double refundAmt;
        private double orderAmt;
        private String openId;
        private String buyerId;
        private String goodsDesc;
        private String payTypeDetail;
        private double merchandiseFee;
        private String couponId;
        private String couponType;
        private String discount;
        private String fullAmt;
        private double realAmt;
        private String preferentialAmt;
        private String updateTime;
        private String createTime;
        private String outTransId;
        private String refundOrderNos;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public void setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
        }

        public String getPayChannelType() {
            return payChannelType;
        }

        public void setPayChannelType(String payChannelType) {
            this.payChannelType = payChannelType;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getTransStat() {
            return transStat;
        }

        public void setTransStat(String transStat) {
            this.transStat = transStat;
        }

        public double getRefundAmt() {
            return refundAmt;
        }

        public void setRefundAmt(double refundAmt) {
            this.refundAmt = refundAmt;
        }

        public double getOrderAmt() {
            return orderAmt;
        }

        public void setOrderAmt(double orderAmt) {
            this.orderAmt = orderAmt;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getPayTypeDetail() {
            return payTypeDetail;
        }

        public void setPayTypeDetail(String payTypeDetail) {
            this.payTypeDetail = payTypeDetail;
        }

        public double getMerchandiseFee() {
            return merchandiseFee;
        }

        public void setMerchandiseFee(double merchandiseFee) {
            this.merchandiseFee = merchandiseFee;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getFullAmt() {
            return fullAmt;
        }

        public void setFullAmt(String fullAmt) {
            this.fullAmt = fullAmt;
        }

        public double getRealAmt() {
            return realAmt;
        }

        public void setRealAmt(double realAmt) {
            this.realAmt = realAmt;
        }

        public String getPreferentialAmt() {
            return preferentialAmt;
        }

        public void setPreferentialAmt(String preferentialAmt) {
            this.preferentialAmt = preferentialAmt;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOutTransId() {
            return outTransId;
        }

        public void setOutTransId(String outTransId) {
            this.outTransId = outTransId;
        }

        public String getRefundOrderNos() {
            return refundOrderNos;
        }

        public void setRefundOrderNos(String refundOrderNos) {
            this.refundOrderNos = refundOrderNos;
        }
    }
}
