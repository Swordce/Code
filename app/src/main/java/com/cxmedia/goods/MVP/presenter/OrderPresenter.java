package com.cxmedia.goods.MVP.presenter;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.cache.MchtCache;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.utils.Cache;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class OrderPresenter extends BasePresenter<IOrderView> {

    public void doGetHomeOrderInfo(RequestBody body) {
        Disposable disposable = ApiService.getApi().homeOrderInfo(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        HomeOrderInfo result = new Gson().fromJson(json,HomeOrderInfo.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.homeOrderInfoResult(result.getIndexOrderData());
                        }else {
                            throw new Exception(result.getRespMsg());
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

    public void doGetOrderInfoList(RequestBody body) {
        Disposable disposable = ApiService.getApi().orderInfoList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        OrderInfoListResult result = new Gson().fromJson(json,OrderInfoListResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.orderInfoListResult(result.getRows());
                        }else {
                            throw new Exception(result.getRespMsg());
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
                        OrderDetailResult result = new Gson().fromJson(json,OrderDetailResult.class);
                        if(Contents.SUCCESS_CODE_1.equals(result.getRespCode()+"")) {
                            baseview.orderDetailResult(result.getData());
                        }else {
                            throw new Exception(result.getRespMsg());
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
                        OrderCategroyResult result = new Gson().fromJson(json,OrderCategroyResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getPageData().getRespCode())) {
                            baseview.OrderSettleResult(result);
                        }else {
                            throw new Exception(result.getPageData().getRespMsg());
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
                            baseview.passwordRefundResult(result.getRespMsg());
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
                            baseview.resetRefundPasswordResult(result);
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
                            baseview.faceRefundResult(result.getRespMsg());
                        }else {
                            throw new Exception(result.getRespMsg());
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
                            baseview.getSmsCodeResult("发送成功~");
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

    public void doSaveFace(RequestBody body) {
        Disposable disposable = ApiService.getApi().saveFace(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.saveFaceSuccessResult("发送成功~");
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

    public void doFaceLogin(RequestBody body) {
        Disposable disposable = ApiService.getApi().faceLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        LoginResult model = new Gson().fromJson(json,LoginResult.class);
                        if("0000".equals(model.getRespCode())) {
                            if(model.getTblEmployeeInf() != null) {
                                LoginResult.TblEmployeeInfBean bean = model.getTblEmployeeInf();
                                Cache.put("id",bean.getId());
                                Cache.put("empNo",bean.getEmpNo());
                                Cache.put("empType",bean.getEmpType());
                                Cache.put("empName",bean.getEmpName());

                            }
                            if(model.getMchtList().size() > 0) {
                                Cache.put("mchtNo",model.getMchtList().get(0).getMchtNo());
                                MchtCache.getInstance().addMchtList(model.getMchtList());
                            }
                            baseview.faceLoginResult(model);
                        }else {
                            throw new Exception(model.getRespMsg());
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
