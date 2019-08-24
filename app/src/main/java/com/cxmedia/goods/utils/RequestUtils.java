package com.cxmedia.goods.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RequestUtils {
    private static final String ENCODING_TYPE = "utf-8";
    private static final String SIGN_METHOD = "00";
    private static final String VERSION = "1.0.0";

    public static String oderRefundStr(String merId, String txnOrderId, String origTxnOrderId, String origTxnOrderTime, String txnOrderTime, String origTxnRespSsn, String txnAmt, String operId) {
        TreeMap<String, String> orderMap = new TreeMap<>();
        orderMap.put("encoding", ENCODING_TYPE);
        orderMap.put("signMethod", SIGN_METHOD);
        orderMap.put("txnType", "6003");
        orderMap.put("version", VERSION);
        orderMap.put("merId", merId);
        orderMap.put("txnOrderId", txnOrderId);
        orderMap.put("origTxnOrderId", origTxnOrderId);
        orderMap.put("origTxnOrderTime", origTxnOrderTime);
        orderMap.put("txnOrderTime", txnOrderTime);
        orderMap.put("origTxnRespSsn", origTxnRespSsn);
        orderMap.put("txnAmt", txnAmt);
        orderMap.put("operId", operId);
        return requestStr(orderMap);
    }

    public static String oderQueryStr(String merId, String origTxnOrderId, String origTxnOrderTime) {
        TreeMap<String, String> orderMap = new TreeMap<>();
        orderMap.put("encoding", ENCODING_TYPE);
        orderMap.put("signMethod", SIGN_METHOD);
        orderMap.put("txnType", "6004");
        orderMap.put("version", VERSION);
        orderMap.put("merId", merId);
        orderMap.put("origTxnOrderId", origTxnOrderId);
        orderMap.put("origTxnOrderTime", origTxnOrderTime);
        return requestStr(orderMap);
    }

    public static TreeMap<String, String> addCustomerStr(String currentEmpNo, String mchtNo, String empName, String empType, String phone, String haveCoupon, String haveRefund) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("currentEmpNo", currentEmpNo);
        customerMap.put("mchtNo", mchtNo);
        customerMap.put("empName", empName);
        customerMap.put("empType", empType);
        customerMap.put("phone", phone);
        customerMap.put("haveCoupon", haveCoupon);
        customerMap.put("haveRefund", haveRefund);
        return customerMap;
    }

    public static String editCustomerStr(String empNo, String mchtNo, String empName, String empType, String phone, String currentEmpNo) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("empNo", empNo);
        customerMap.put("mchtNo", mchtNo);
        if (!TextUtils.isEmpty(empName)) {
            customerMap.put("empName", empName);
        }
        if (!TextUtils.isEmpty(empType)) {
            customerMap.put("empType", empType);
        }
        if (!TextUtils.isEmpty(phone)) {
            customerMap.put("phone", phone);
        }
        customerMap.put("currentEmpNo", currentEmpNo);
        return requestStr(customerMap);
    }

    public static TreeMap<String, String> searchCustomerStr(String mchtNo,String keyword) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("searchText", keyword);
        customerMap.put("mchtNo", mchtNo);
        return customerMap;
    }

    public static TreeMap<String, String> deleteCustomerStr(String empNo, String mchtNo) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("empNo", empNo);
        customerMap.put("mchtNo", mchtNo);
        return customerMap;
    }

    /**
     *
     * @return
     */

    public static TreeMap<String,String> customerListStr(String mchtNo,int pageIndex,int pageSize) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("mchtNo",mchtNo);
        customerMap.put("pageIndex",pageIndex+"");
        customerMap.put("pageSize",pageSize+"");
        return customerMap;
    }

    public static String customerDetailStr(String empNo, String mchtNo) {
        TreeMap<String, String> customerMap = new TreeMap<>();
        customerMap.put("encoding", ENCODING_TYPE);
        customerMap.put("signMethod", SIGN_METHOD);
        customerMap.put("txnType", "7005");
        customerMap.put("version", VERSION);
        customerMap.put("empNo", empNo);
        customerMap.put("mchtNo", mchtNo);
        return requestStr(customerMap);
    }

    public static String mchtListStr(String empNo) {
        TreeMap<String, String> mchtMap = new TreeMap<>();
        mchtMap.put("encoding", ENCODING_TYPE);
        mchtMap.put("signMethod", SIGN_METHOD);
        mchtMap.put("txnType", "4001");
        mchtMap.put("version", VERSION);
        mchtMap.put("empNo", empNo);
        return requestStr(mchtMap);
    }

    public static Map<String, String> addCouponStr(String mchtNo, String couponType, String couponNum, String discount,
                                                   String fullAmt, String subtractionAmt, String effectiveDate,
                                                   String expireDate, String currentEmpNo) {
        TreeMap<String, String> couponMap = new TreeMap<>();

        couponMap.put("mchtNo", mchtNo);
        couponMap.put("couponType", couponType);
        couponMap.put("couponNum", couponNum);
        if (!TextUtils.isEmpty(discount)) {
            couponMap.put("discount", discount);
        }
        if (!TextUtils.isEmpty(fullAmt)) {
            couponMap.put("fullAmt", fullAmt);
        }
        if (!TextUtils.isEmpty(subtractionAmt)) {
            couponMap.put("subtractionAmt", subtractionAmt);
        }
        couponMap.put("effectiveDate", effectiveDate);
        couponMap.put("expireDate", expireDate);
        couponMap.put("currentEmpNo", currentEmpNo);
        return couponMap;
    }

    public static String editCouponStr(String couponId, String mchtNo, String currentEmpNo, String couponType,
                                       String couponNum, String discount, String fullAmt, String subtractionAmt,
                                       String effectiveDate, String expireDate) {
        TreeMap<String, String> couponMap = new TreeMap<>();
        couponMap.put("encoding", ENCODING_TYPE);
        couponMap.put("id", couponId);
        couponMap.put("mchtNo", mchtNo);
        couponMap.put("signMethod", SIGN_METHOD);
        couponMap.put("txnType", "5002");
        if (!TextUtils.isEmpty(currentEmpNo)) {
            couponMap.put("currentEmpNo", currentEmpNo);
        }
        if (!TextUtils.isEmpty(couponType)) {
            couponMap.put("couponType", couponType);//01---优惠券,02---满减

        }
        if (!TextUtils.isEmpty(couponNum)) {
            couponMap.put("couponNum", couponNum);

        }
        if (!TextUtils.isEmpty(discount)) {
            couponMap.put("discount", discount);

        }
        if (!TextUtils.isEmpty(fullAmt)) {
            couponMap.put("fullAmt", fullAmt);

        }
        if (!TextUtils.isEmpty(subtractionAmt)) {
            couponMap.put("subtractionAmt", subtractionAmt);

        }
        if (!TextUtils.isEmpty(effectiveDate)) {
            couponMap.put("effectiveDate", effectiveDate);

        }
        if (!TextUtils.isEmpty(expireDate)) {
            couponMap.put("expireDate", expireDate);
        }
        return requestStr(couponMap);
    }

    public static TreeMap<String,String> deleteCouponStr(String mchtNo, String couponId) {
        TreeMap<String, String> couponMap = new TreeMap<>();
        couponMap.put("id", couponId);
        couponMap.put("mchtNo", mchtNo);
        return couponMap;
    }

    public static TreeMap<String, String> couponListStr(String mchtNo) {
        TreeMap<String, String> couponMap = new TreeMap<>();
        couponMap.put("mchtNo", mchtNo);
        return couponMap;
    }

    public static TreeMap<String,String> couponDetaiStr(String couponId,String mchtNo) {
        TreeMap<String, String> detailMap = new TreeMap<>();
        detailMap.put("id", couponId);
        detailMap.put("mchtNo",mchtNo);
        return detailMap;
    }

    public static TreeMap<String, String> editPasswordStr(String empNo, String newPassword) {
        TreeMap<String, String> passwordMap = new TreeMap<>();
        passwordMap.put("empNo", empNo);
        passwordMap.put("newPassword", newPassword);
        return passwordMap;
    }

    public static TreeMap<String, String> loginStr(String phone, String password) {
        TreeMap<String, String> loginMap = new TreeMap<>();
//        loginMap.put("encoding", ENCODING_TYPE);
//        loginMap.put("signMethod", SIGN_METHOD);
//        loginMap.put("txnType", "7002");
//        loginMap.put("version", VERSION);
        loginMap.put("empNo", phone);
        loginMap.put("password", md5(password));
        return loginMap;
    }


    public static String requestStr(TreeMap<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
//            value = URLEncoder.encode(value, "UTF-8");
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        prestr = prestr + "&key=" + Cache.get("signAture");
        String sign = md5(prestr).toUpperCase();
        params.put("signAture", sign);
        return new Gson().toJson(params);
    }

    private static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
