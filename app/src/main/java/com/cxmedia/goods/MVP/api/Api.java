package com.cxmedia.goods.MVP.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    //查询商户信息
    @POST("qrcApply")
    Observable<String> shopInfo(@Body RequestBody body);

    //退款
    @POST("refund")
    Observable<String> reFund(@Body RequestBody body);

    //订单查询
    @POST("query")
    Observable<String> orderInfo(@Body RequestBody body);

    //交易结果

    //添加员工
    @POST("emp/addEmp")
    Observable<ResponseBody> addCustomer(@Body RequestBody body);

    //修改员工
    @POST("emp/modifyEmp")
    Observable<String> editCustomer(@Body RequestBody body);

    //删除员工
    @POST("emp/delEmp")
    Observable<ResponseBody> deleteCustomer(@Body RequestBody body);

    //员工列表
    @POST("emp/getEmpList")
    Observable<ResponseBody> customerList(@Body RequestBody body);

    //搜索员工
    @POST("emp/searchEmp")
    Observable<ResponseBody> searchEmp(@Body RequestBody body);

    //员工详情
    @POST("emp/getEmpDetail")
    Observable<String> customerInfo(@Body RequestBody body);

    //商户列表
    @POST("author/getMchtList")
    Observable<String> shopList(@Body RequestBody body);

    //添加优惠券
    @POST("coupon/addCoupon")
    Observable<ResponseBody> addCoupon(@Body RequestBody body);

    //修改优惠券
    @POST("coupon/modifyCoupon")
    Observable<String> editCoupon(@Body RequestBody body);

    //删除优惠券
    @POST("coupon/deleteCoupon")
    Observable<ResponseBody> deleteCoupon(@Body RequestBody body);

    //优惠券列表
    @POST("coupon/couponList")
    Observable<ResponseBody> couponList(@Body RequestBody body);

    //优惠券详情
    @POST("coupon/couponDetail")
    Observable<ResponseBody> couponInfo(@Body RequestBody body);

    //修改登录密码
    @POST("easyEditPassword")
    Observable<ResponseBody> editLoginPassword(@Body RequestBody body);

    //登录
    @POST("login")
    Observable<ResponseBody> login(@Body RequestBody body);


}
