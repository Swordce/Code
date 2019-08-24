package com.cxmedia.goods.MVP.model;

import java.util.List;

public class CustomerListResult {

    /**
     * respCode : 0000
     * respMsg : 交易成功
     * list : [{"id":null,"empNo":"17712906965","empName":"测试","empType":"测试","password":"e10adc3949ba59abbe56e057f20f883e","pwdErrCou":0,"sex":null,"phone":null,"status":"00","createTime":"2019-08-15T02:10:04.000+0000","createEmp":"CY00045","modifyTime":"2019-08-15T02:10:04.000+0000","modifyEmp":"CY00045","refundPassword":null,"haveCoupon":null,"haveRefund":null,"isFirst":null},{"id":null,"empNo":"3","empName":"3213","empType":"2","password":"e10adc3949ba59abbe56e057f20f883e","pwdErrCou":0,"sex":null,"phone":null,"status":"00","createTime":"2019-08-03T12:01:05.000+0000","createEmp":"12231313","modifyTime":"2019-08-03T12:01:05.000+0000","modifyEmp":"12231313","refundPassword":null,"haveCoupon":null,"haveRefund":null,"isFirst":null},{"id":null,"empNo":"CY00045","empName":"新员","empType":"02","password":"e10adc3949ba59abbe56e057f20f883e","pwdErrCou":0,"sex":null,"phone":null,"status":"00","createTime":"2019-08-01T09:03:58.000+0000","createEmp":"CY00001","modifyTime":"2019-08-01T09:03:58.000+0000","modifyEmp":"CY00001","refundPassword":null,"haveCoupon":null,"haveRefund":null,"isFirst":null}]
     * totalCount : 3
     */

    private String respCode;
    private String respMsg;
    private int totalCount;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : null
         * empNo : 17712906965
         * empName : 测试
         * empType : 测试
         * password : e10adc3949ba59abbe56e057f20f883e
         * pwdErrCou : 0
         * sex : null
         * phone : null
         * status : 00
         * createTime : 2019-08-15T02:10:04.000+0000
         * createEmp : CY00045
         * modifyTime : 2019-08-15T02:10:04.000+0000
         * modifyEmp : CY00045
         * refundPassword : null
         * haveCoupon : null
         * haveRefund : null
         * isFirst : null
         */

        private String id;
        private String empNo;
        private String empName;
        private String empType;
        private String password;
        private int pwdErrCou;
        private String sex;
        private String phone;
        private String status;
        private String createTime;
        private String createEmp;
        private String modifyTime;
        private String modifyEmp;
        private String refundPassword;
        private String haveCoupon;
        private String haveRefund;
        private String isFirst;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmpNo() {
            return empNo;
        }

        public void setEmpNo(String empNo) {
            this.empNo = empNo;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getEmpType() {
            return empType;
        }

        public void setEmpType(String empType) {
            this.empType = empType;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPwdErrCou() {
            return pwdErrCou;
        }

        public void setPwdErrCou(int pwdErrCou) {
            this.pwdErrCou = pwdErrCou;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getRefundPassword() {
            return refundPassword;
        }

        public void setRefundPassword(String refundPassword) {
            this.refundPassword = refundPassword;
        }

        public String getHaveCoupon() {
            return haveCoupon;
        }

        public void setHaveCoupon(String haveCoupon) {
            this.haveCoupon = haveCoupon;
        }

        public String getHaveRefund() {
            return haveRefund;
        }

        public void setHaveRefund(String haveRefund) {
            this.haveRefund = haveRefund;
        }

        public String getIsFirst() {
            return isFirst;
        }

        public void setIsFirst(String isFirst) {
            this.isFirst = isFirst;
        }
    }
}
