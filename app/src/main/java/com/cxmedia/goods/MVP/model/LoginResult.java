package com.cxmedia.goods.MVP.model;

import java.util.List;

public class LoginResult {

    /**
     * respCode : 0000
     * respMsg : 交易成功
     * mchtList : [{"id":2,"mchtNo":"8000000000001","mchtName":"测试商户","mchtShortName":"测试商户","provId":null,"cityId":null,"areaId":null,"address":"武汉市鹦鹉大道天下名企汇","mcc":"4001","businessShours":null,"businessEhours":null,"csTel":null,"status":"00","settleType":null,"settleTerm":null,"fee1":null,"settleBusiness":null,"fee28":null,"outAcctNo":null,"contactName":null,"contactTelNo":null,"contactEmail":null,"contactIdType":null,"contactIdNo":null,"contactIdValidType":null,"contactIdSdate":null,"contactIdEdate":null,"bankProvId":null,"bankCityId":null,"bankName":null,"bankUnionCode":null,"subBankName":null,"bankAcctName":null,"bankAcctId":null,"accountIdType":null,"accountIdNo":null,"accountIdValidType":null,"accountIdSdate":null,"accountIdEdate":null,"isCreditCode":null,"creditCode":null,"regCode":null,"licType":null,"licSdate":null,"licEdate":null,"orgCode":null,"legalName":null,"idType":null,"idNo":null,"idValidType":null,"idSdate":null,"idEdate":null,"merchClass":null,"pnrpayMerType":null,"createTime":"2019-07-01T01:16:26.000+0000","createEmp":"admin","modifyEmp":"admin","modifyTime":"2019-06-18T08:34:07.000+0000"}]
     * tblEmployeeInf : {"id":39,"empNo":"CY00001","empName":"测试姓名","empType":"01","password":"t2O3+pTfNBQ0qF+1xnR+ew==","pwdErrCou":3,"sex":null,"phone":null,"status":"00","createTime":"2019-07-31T12:42:13.000+0000","createEmp":"admin","modifyTime":"2019-07-31T10:08:11.000+0000","modifyEmp":"CY00001"}
     * tblSettleInfo : {"realityMoney":5820,"payMoney":11420,"payTimes":1,"refundMoney":5600}
     */

    private String respCode;
    private String respMsg;
    private int isFirst;
    private TblEmployeeInfBean tblEmployeeInf;
    private TblSettleInfoBean tblSettleInfo;
    private List<MchtListBean> mchtList;

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
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

    public TblEmployeeInfBean getTblEmployeeInf() {
        return tblEmployeeInf;
    }

    public void setTblEmployeeInf(TblEmployeeInfBean tblEmployeeInf) {
        this.tblEmployeeInf = tblEmployeeInf;
    }

    public TblSettleInfoBean getTblSettleInfo() {
        return tblSettleInfo;
    }

    public void setTblSettleInfo(TblSettleInfoBean tblSettleInfo) {
        this.tblSettleInfo = tblSettleInfo;
    }

    public List<MchtListBean> getMchtList() {
        return mchtList;
    }

    public void setMchtList(List<MchtListBean> mchtList) {
        this.mchtList = mchtList;
    }

    public static class TblEmployeeInfBean {
        /**
         * id : 39
         * empNo : CY00001
         * empName : 测试姓名
         * empType : 01
         * password : t2O3+pTfNBQ0qF+1xnR+ew==
         * pwdErrCou : 3
         * sex : null
         * phone : null
         * status : 00
         * createTime : 2019-07-31T12:42:13.000+0000
         * createEmp : admin
         * modifyTime : 2019-07-31T10:08:11.000+0000
         * modifyEmp : CY00001
         */

        private int id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public Object getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getPhone() {
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
    }

    public static class TblSettleInfoBean {
        /**
         * realityMoney : 5820
         * payMoney : 11420
         * payTimes : 1
         * refundMoney : 5600
         */

        private int realityMoney;
        private int payMoney;
        private int payTimes;
        private int refundMoney;

        public int getRealityMoney() {
            return realityMoney;
        }

        public void setRealityMoney(int realityMoney) {
            this.realityMoney = realityMoney;
        }

        public int getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(int payMoney) {
            this.payMoney = payMoney;
        }

        public int getPayTimes() {
            return payTimes;
        }

        public void setPayTimes(int payTimes) {
            this.payTimes = payTimes;
        }

        public int getRefundMoney() {
            return refundMoney;
        }

        public void setRefundMoney(int refundMoney) {
            this.refundMoney = refundMoney;
        }
    }

    public static class MchtListBean {
        /**
         * id : 2
         * mchtNo : 8000000000001
         * mchtName : 测试商户
         * mchtShortName : 测试商户
         * provId : null
         * cityId : null
         * areaId : null
         * address : 武汉市鹦鹉大道天下名企汇
         * mcc : 4001
         * businessShours : null
         * businessEhours : null
         * csTel : null
         * status : 00
         * settleType : null
         * settleTerm : null
         * fee1 : null
         * settleBusiness : null
         * fee28 : null
         * outAcctNo : null
         * contactName : null
         * contactTelNo : null
         * contactEmail : null
         * contactIdType : null
         * contactIdNo : null
         * contactIdValidType : null
         * contactIdSdate : null
         * contactIdEdate : null
         * bankProvId : null
         * bankCityId : null
         * bankName : null
         * bankUnionCode : null
         * subBankName : null
         * bankAcctName : null
         * bankAcctId : null
         * accountIdType : null
         * accountIdNo : null
         * accountIdValidType : null
         * accountIdSdate : null
         * accountIdEdate : null
         * isCreditCode : null
         * creditCode : null
         * regCode : null
         * licType : null
         * licSdate : null
         * licEdate : null
         * orgCode : null
         * legalName : null
         * idType : null
         * idNo : null
         * idValidType : null
         * idSdate : null
         * idEdate : null
         * merchClass : null
         * pnrpayMerType : null
         * createTime : 2019-07-01T01:16:26.000+0000
         * createEmp : admin
         * modifyEmp : admin
         * modifyTime : 2019-06-18T08:34:07.000+0000
         */

        private int id;
        private String mchtNo;
        private String mchtName;
        private String mchtShortName;
        private Object provId;
        private Object cityId;
        private Object areaId;
        private String address;
        private String mcc;
        private Object businessShours;
        private Object businessEhours;
        private Object csTel;
        private String status;
        private Object settleType;
        private Object settleTerm;
        private Object fee1;
        private Object settleBusiness;
        private Object fee28;
        private Object outAcctNo;
        private Object contactName;
        private Object contactTelNo;
        private Object contactEmail;
        private Object contactIdType;
        private Object contactIdNo;
        private Object contactIdValidType;
        private Object contactIdSdate;
        private Object contactIdEdate;
        private Object bankProvId;
        private Object bankCityId;
        private Object bankName;
        private Object bankUnionCode;
        private Object subBankName;
        private Object bankAcctName;
        private Object bankAcctId;
        private Object accountIdType;
        private Object accountIdNo;
        private Object accountIdValidType;
        private Object accountIdSdate;
        private Object accountIdEdate;
        private Object isCreditCode;
        private Object creditCode;
        private Object regCode;
        private Object licType;
        private Object licSdate;
        private Object licEdate;
        private Object orgCode;
        private Object legalName;
        private Object idType;
        private Object idNo;
        private Object idValidType;
        private Object idSdate;
        private Object idEdate;
        private Object merchClass;
        private Object pnrpayMerType;
        private String createTime;
        private String createEmp;
        private String modifyEmp;
        private String modifyTime;

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

        public String getMchtName() {
            return mchtName;
        }

        public void setMchtName(String mchtName) {
            this.mchtName = mchtName;
        }

        public String getMchtShortName() {
            return mchtShortName;
        }

        public void setMchtShortName(String mchtShortName) {
            this.mchtShortName = mchtShortName;
        }

        public Object getProvId() {
            return provId;
        }

        public void setProvId(Object provId) {
            this.provId = provId;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public Object getAreaId() {
            return areaId;
        }

        public void setAreaId(Object areaId) {
            this.areaId = areaId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMcc() {
            return mcc;
        }

        public void setMcc(String mcc) {
            this.mcc = mcc;
        }

        public Object getBusinessShours() {
            return businessShours;
        }

        public void setBusinessShours(Object businessShours) {
            this.businessShours = businessShours;
        }

        public Object getBusinessEhours() {
            return businessEhours;
        }

        public void setBusinessEhours(Object businessEhours) {
            this.businessEhours = businessEhours;
        }

        public Object getCsTel() {
            return csTel;
        }

        public void setCsTel(Object csTel) {
            this.csTel = csTel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSettleType() {
            return settleType;
        }

        public void setSettleType(Object settleType) {
            this.settleType = settleType;
        }

        public Object getSettleTerm() {
            return settleTerm;
        }

        public void setSettleTerm(Object settleTerm) {
            this.settleTerm = settleTerm;
        }

        public Object getFee1() {
            return fee1;
        }

        public void setFee1(Object fee1) {
            this.fee1 = fee1;
        }

        public Object getSettleBusiness() {
            return settleBusiness;
        }

        public void setSettleBusiness(Object settleBusiness) {
            this.settleBusiness = settleBusiness;
        }

        public Object getFee28() {
            return fee28;
        }

        public void setFee28(Object fee28) {
            this.fee28 = fee28;
        }

        public Object getOutAcctNo() {
            return outAcctNo;
        }

        public void setOutAcctNo(Object outAcctNo) {
            this.outAcctNo = outAcctNo;
        }

        public Object getContactName() {
            return contactName;
        }

        public void setContactName(Object contactName) {
            this.contactName = contactName;
        }

        public Object getContactTelNo() {
            return contactTelNo;
        }

        public void setContactTelNo(Object contactTelNo) {
            this.contactTelNo = contactTelNo;
        }

        public Object getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(Object contactEmail) {
            this.contactEmail = contactEmail;
        }

        public Object getContactIdType() {
            return contactIdType;
        }

        public void setContactIdType(Object contactIdType) {
            this.contactIdType = contactIdType;
        }

        public Object getContactIdNo() {
            return contactIdNo;
        }

        public void setContactIdNo(Object contactIdNo) {
            this.contactIdNo = contactIdNo;
        }

        public Object getContactIdValidType() {
            return contactIdValidType;
        }

        public void setContactIdValidType(Object contactIdValidType) {
            this.contactIdValidType = contactIdValidType;
        }

        public Object getContactIdSdate() {
            return contactIdSdate;
        }

        public void setContactIdSdate(Object contactIdSdate) {
            this.contactIdSdate = contactIdSdate;
        }

        public Object getContactIdEdate() {
            return contactIdEdate;
        }

        public void setContactIdEdate(Object contactIdEdate) {
            this.contactIdEdate = contactIdEdate;
        }

        public Object getBankProvId() {
            return bankProvId;
        }

        public void setBankProvId(Object bankProvId) {
            this.bankProvId = bankProvId;
        }

        public Object getBankCityId() {
            return bankCityId;
        }

        public void setBankCityId(Object bankCityId) {
            this.bankCityId = bankCityId;
        }

        public Object getBankName() {
            return bankName;
        }

        public void setBankName(Object bankName) {
            this.bankName = bankName;
        }

        public Object getBankUnionCode() {
            return bankUnionCode;
        }

        public void setBankUnionCode(Object bankUnionCode) {
            this.bankUnionCode = bankUnionCode;
        }

        public Object getSubBankName() {
            return subBankName;
        }

        public void setSubBankName(Object subBankName) {
            this.subBankName = subBankName;
        }

        public Object getBankAcctName() {
            return bankAcctName;
        }

        public void setBankAcctName(Object bankAcctName) {
            this.bankAcctName = bankAcctName;
        }

        public Object getBankAcctId() {
            return bankAcctId;
        }

        public void setBankAcctId(Object bankAcctId) {
            this.bankAcctId = bankAcctId;
        }

        public Object getAccountIdType() {
            return accountIdType;
        }

        public void setAccountIdType(Object accountIdType) {
            this.accountIdType = accountIdType;
        }

        public Object getAccountIdNo() {
            return accountIdNo;
        }

        public void setAccountIdNo(Object accountIdNo) {
            this.accountIdNo = accountIdNo;
        }

        public Object getAccountIdValidType() {
            return accountIdValidType;
        }

        public void setAccountIdValidType(Object accountIdValidType) {
            this.accountIdValidType = accountIdValidType;
        }

        public Object getAccountIdSdate() {
            return accountIdSdate;
        }

        public void setAccountIdSdate(Object accountIdSdate) {
            this.accountIdSdate = accountIdSdate;
        }

        public Object getAccountIdEdate() {
            return accountIdEdate;
        }

        public void setAccountIdEdate(Object accountIdEdate) {
            this.accountIdEdate = accountIdEdate;
        }

        public Object getIsCreditCode() {
            return isCreditCode;
        }

        public void setIsCreditCode(Object isCreditCode) {
            this.isCreditCode = isCreditCode;
        }

        public Object getCreditCode() {
            return creditCode;
        }

        public void setCreditCode(Object creditCode) {
            this.creditCode = creditCode;
        }

        public Object getRegCode() {
            return regCode;
        }

        public void setRegCode(Object regCode) {
            this.regCode = regCode;
        }

        public Object getLicType() {
            return licType;
        }

        public void setLicType(Object licType) {
            this.licType = licType;
        }

        public Object getLicSdate() {
            return licSdate;
        }

        public void setLicSdate(Object licSdate) {
            this.licSdate = licSdate;
        }

        public Object getLicEdate() {
            return licEdate;
        }

        public void setLicEdate(Object licEdate) {
            this.licEdate = licEdate;
        }

        public Object getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(Object orgCode) {
            this.orgCode = orgCode;
        }

        public Object getLegalName() {
            return legalName;
        }

        public void setLegalName(Object legalName) {
            this.legalName = legalName;
        }

        public Object getIdType() {
            return idType;
        }

        public void setIdType(Object idType) {
            this.idType = idType;
        }

        public Object getIdNo() {
            return idNo;
        }

        public void setIdNo(Object idNo) {
            this.idNo = idNo;
        }

        public Object getIdValidType() {
            return idValidType;
        }

        public void setIdValidType(Object idValidType) {
            this.idValidType = idValidType;
        }

        public Object getIdSdate() {
            return idSdate;
        }

        public void setIdSdate(Object idSdate) {
            this.idSdate = idSdate;
        }

        public Object getIdEdate() {
            return idEdate;
        }

        public void setIdEdate(Object idEdate) {
            this.idEdate = idEdate;
        }

        public Object getMerchClass() {
            return merchClass;
        }

        public void setMerchClass(Object merchClass) {
            this.merchClass = merchClass;
        }

        public Object getPnrpayMerType() {
            return pnrpayMerType;
        }

        public void setPnrpayMerType(Object pnrpayMerType) {
            this.pnrpayMerType = pnrpayMerType;
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

        public String getModifyEmp() {
            return modifyEmp;
        }

        public void setModifyEmp(String modifyEmp) {
            this.modifyEmp = modifyEmp;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
