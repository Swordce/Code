package com.cxmedia.goods.MVP.model;

import java.util.List;

public class CouponListResult {

    /**
     * respCode : 0000
     * respMsg : 交易成功
     * list : [{"id":2,"mchtNo":"8000000000001","couponType":"02","couponNum":100,"discount":null,"fullAmt":100,"subtractionAmt":20,"effectiveDate":"2019-07-31 19:17:24","expireDate":"2019-09-31 19:17:24","createTime":"2019-08-02 13:41:56","createEmp":"admin","modifyTime":"2019-06-24 17:48:50","modifyEmp":"admin","poster":"http://163j1x3955.imwork.net:29250/22.jpg","thumbnail":"http://163j1x3955.imwork.net:29250/22.jpg"},{"id":3,"mchtNo":"8000000000001","couponType":"2","couponNum":6,"discount":null,"fullAmt":3,"subtractionAmt":100,"effectiveDate":"2019-07-31 18:08:11","expireDate":"2019-08-31 18:08:11","createTime":"2019-08-02 13:42:12","createEmp":"CY00001","modifyTime":"2019-07-31 19:32:24","modifyEmp":"CY00001","poster":"海报","thumbnail":"缩略图"}]
     */

    private String respCode;
    private String respMsg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2
         * mchtNo : 8000000000001
         * couponType : 02
         * couponNum : 100
         * discount : null
         * fullAmt : 100
         * subtractionAmt : 20
         * effectiveDate : 2019-07-31 19:17:24
         * expireDate : 2019-09-31 19:17:24
         * createTime : 2019-08-02 13:41:56
         * createEmp : admin
         * modifyTime : 2019-06-24 17:48:50
         * modifyEmp : admin
         * poster : http://163j1x3955.imwork.net:29250/22.jpg
         * thumbnail : http://163j1x3955.imwork.net:29250/22.jpg
         */
        private String respCode;
        private int id;
        private String mchtNo;
        private String couponType;
        private int couponNum;
        private String discount;
        private int fullAmt;
        private int subtractionAmt;
        private String effectiveDate;
        private String expireDate;
        private String createTime;
        private String createEmp;
        private String modifyTime;
        private String modifyEmp;
        private String poster;
        private String thumbnail;
        private String getNum;
        private String shareNum;
        private String useNum;

        public String getGetNum() {
            return getNum;
        }

        public void setGetNum(String getNum) {
            this.getNum = getNum;
        }

        public String getShareNum() {
            return shareNum;
        }

        public void setShareNum(String shareNum) {
            this.shareNum = shareNum;
        }

        public String getUseNum() {
            return useNum;
        }

        public void setUseNum(String useNum) {
            this.useNum = useNum;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMchtNo() {
            return mchtNo;
        }

        public void setMchtNo(String mchtNo) {
            this.mchtNo = mchtNo;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getFullAmt() {
            return fullAmt;
        }

        public void setFullAmt(int fullAmt) {
            this.fullAmt = fullAmt;
        }

        public int getSubtractionAmt() {
            return subtractionAmt;
        }

        public void setSubtractionAmt(int subtractionAmt) {
            this.subtractionAmt = subtractionAmt;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateEmp() {
            return createEmp;
        }

        public void setCreateEmp(String createEmp) {
            this.createEmp = createEmp;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getModifyEmp() {
            return modifyEmp;
        }

        public void setModifyEmp(String modifyEmp) {
            this.modifyEmp = modifyEmp;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
