package com.cxmedia.goods.MVP.presenter;

import android.widget.Toast;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.common.Contents;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class CouponPresenter extends BasePresenter<ICouponView> {

    public void getCouponList(RequestBody body) {
        Disposable disposable = ApiService.getApi().couponList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CouponListResult result = new Gson().fromJson(json,CouponListResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.couponListResult(result.getList());
                        }else {
                            throw new Exception("获取数据失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.addCouponSuccessResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void addCoupon(RequestBody body) {
        Disposable disposable = ApiService.getApi().addCoupon(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.addCouponSuccessResult("添加成功");
                        }else {
                            throw new Exception("添加失失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.addCouponSuccessResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void getCouponDetail(RequestBody body) {
        Disposable disposable = ApiService.getApi().couponInfo(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CouponListResult.ListBean result = new Gson().fromJson(json,CouponListResult.ListBean.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.couponDetailResult(result);
                        }else {
                            throw new Exception("请求失败,请稍后重试！");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.addCouponSuccessResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void deleteCouponDetail(RequestBody body) {
        Disposable disposable = ApiService.getApi().deleteCoupon(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.deleteCouponSuccessResult("删除成功");
                        }else {
                            throw new Exception("删除失败,请稍后重试！");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.addCouponSuccessResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }
}
