package com.cxmedia.goods.MVP.presenter;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.common.Contents;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class OrderPresenter extends BasePresenter<IOrderView> {

    public void doGetOrderInfoList(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderInfoList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }


    public void doGetOrderDetail(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderDetail(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doGetOrderSettleList(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderSettleList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }


    public void doPasswordRefund(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderPasswordRefund(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doResetOrderPassword(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderForgetRefundPassword(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doOrderFaceRefund(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderFaceRefund(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }


    public void doGetSmsCode(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderSendCode(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.orderInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

}
