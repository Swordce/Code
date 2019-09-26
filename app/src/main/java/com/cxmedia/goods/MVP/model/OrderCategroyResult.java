package com.cxmedia.goods.MVP.model;

import java.util.List;

public class OrderCategroyResult {

    /**
     * realPayCount : 1
     * orderCount : 1
     * sumOrderAmt : 1.0
     * sumRealAmt : 0.5
     * couponCount : 0
     * sumPreferentialAmt : 0.0
     * pageData : {"total":7,"rows":[{"orderNo":"bc524b62722a40f4ab5f5a37e958effb","orderTime":"2019-08-27 01:00:14","transactionTime":null,"payChannelType":"10","orderType":"1000","memberId":"310000016001037025","transStat":"S","refundAmt":0.7,"orderAmt":1,"openId":"onhNzwpDvl51kP0OTxspEPO2FEBI","buyerId":null,"goodsDesc":"测试商品","payTypeDetail":"1022","merchandiseFee":0.01,"couponId":null,"couponType":null,"discount":null,"fullAmt":null,"realAmt":0.5,"preferentialAmt":null,"updateTime":"2019-08-27 01:24:56","createTime":"2019-08-27 01:00:21","outTransId":"4200000394201908279540535358","refundOrderNos":"2439099c881e41c58d0e97130d4cd30b,1e6d8cd2c86e4b8bbfb80c55dad3f757,878772c754364f6f954e14ad59550b6b,c1bebdf8e381494b8aa6e87c146fcd37,21fbd37a2d1d407bbfe3c007bca20f9e,43458bfcfd8c445ba0b57d4eada631fd,1d0765afaab84e5a9d23c0c20776abad"}],"respCode":"0000","respMsg":"交易成功"}
     */

    private int realPayCount;
    private int orderCount;
    private double sumOrderAmt;
    private double sumRealAmt;
    private int couponCount;
    private double sumPreferentialAmt;
    private PageDataBean pageData;

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

    public double getSumOrderAmt() {
        return sumOrderAmt;
    }

    public void setSumOrderAmt(double sumOrderAmt) {
        this.sumOrderAmt = sumOrderAmt;
    }

    public double getSumRealAmt() {
        return sumRealAmt;
    }

    public void setSumRealAmt(double sumRealAmt) {
        this.sumRealAmt = sumRealAmt;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }

    public double getSumPreferentialAmt() {
        return sumPreferentialAmt;
    }

    public void setSumPreferentialAmt(double sumPreferentialAmt) {
        this.sumPreferentialAmt = sumPreferentialAmt;
    }

    public PageDataBean getPageData() {
        return pageData;
    }

    public void setPageData(PageDataBean pageData) {
        this.pageData = pageData;
    }

    public static class PageDataBean {
        /**
         * total : 7
         * rows : [{"orderNo":"bc524b62722a40f4ab5f5a37e958effb","orderTime":"2019-08-27 01:00:14","transactionTime":null,"payChannelType":"10","orderType":"1000","memberId":"310000016001037025","transStat":"S","refundAmt":0.7,"orderAmt":1,"openId":"onhNzwpDvl51kP0OTxspEPO2FEBI","buyerId":null,"goodsDesc":"测试商品","payTypeDetail":"1022","merchandiseFee":0.01,"couponId":null,"couponType":null,"discount":null,"fullAmt":null,"realAmt":0.5,"preferentialAmt":null,"updateTime":"2019-08-27 01:24:56","createTime":"2019-08-27 01:00:21","outTransId":"4200000394201908279540535358","refundOrderNos":"2439099c881e41c58d0e97130d4cd30b,1e6d8cd2c86e4b8bbfb80c55dad3f757,878772c754364f6f954e14ad59550b6b,c1bebdf8e381494b8aa6e87c146fcd37,21fbd37a2d1d407bbfe3c007bca20f9e,43458bfcfd8c445ba0b57d4eada631fd,1d0765afaab84e5a9d23c0c20776abad"}]
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
             * orderNo : bc524b62722a40f4ab5f5a37e958effb
             * orderTime : 2019-08-27 01:00:14
             * transactionTime : null
             * payChannelType : 10
             * orderType : 1000
             * memberId : 310000016001037025
             * transStat : S
             * refundAmt : 0.7
             * orderAmt : 1.0
             * openId : onhNzwpDvl51kP0OTxspEPO2FEBI
             * buyerId : null
             * goodsDesc : 测试商品
             * payTypeDetail : 1022
             * merchandiseFee : 0.01
             * couponId : null
             * couponType : null
             * discount : null
             * fullAmt : null
             * realAmt : 0.5
             * preferentialAmt : null
             * updateTime : 2019-08-27 01:24:56
             * createTime : 2019-08-27 01:00:21
             * outTransId : 4200000394201908279540535358
             * refundOrderNos : 2439099c881e41c58d0e97130d4cd30b,1e6d8cd2c86e4b8bbfb80c55dad3f757,878772c754364f6f954e14ad59550b6b,c1bebdf8e381494b8aa6e87c146fcd37,21fbd37a2d1d407bbfe3c007bca20f9e,43458bfcfd8c445ba0b57d4eada631fd,1d0765afaab84e5a9d23c0c20776abad
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
}
