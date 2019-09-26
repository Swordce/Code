package com.cxmedia.goods.MVP.presenter;

import android.util.Log;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CustomerListResult;
import com.cxmedia.goods.MVP.view.ICustomerView;
import com.cxmedia.goods.common.Contents;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class CustomerPresenter extends BasePresenter<ICustomerView> {

    public void doGetCustomerList(RequestBody body) {
        Disposable disposable = ApiService.getApi().customerList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        CustomerListResult listResult = new Gson().fromJson(result,CustomerListResult.class);
                        if(Contents.SUCCESS_CODE.equals(listResult.getRespCode())) {
                            baseview.customerListResult(listResult.getList());
                        }else {
                            throw new Exception(listResult.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.customerErrorResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doAddCustomer(RequestBody body) {
        Disposable disposable = ApiService.getApi().addCustomer(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        CommonResult result = new Gson().fromJson(json,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.addCustomerResult("添加成功");
                        }else {
                            throw new Exception(result.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.customerErrorResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doDeleteCustomer(RequestBody body) {
        Disposable disposable = ApiService.getApi().deleteCustomer(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        CommonResult response = new Gson().fromJson(result,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(response.getRespCode())) {
                            baseview.deleteCustomerResult("删除成功");
                        }else {
                            throw new Exception(response.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.customerErrorResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doEditCusomer(RequestBody body) {
        Disposable disposable = ApiService.getApi().editCustomer(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        CommonResult response = new Gson().fromJson(result,CommonResult.class);
                        if(Contents.SUCCESS_CODE.equals(response.getRespCode())) {
                            baseview.editCustomerResult("修改成功");
                        }else {
                            throw new Exception(response.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.customerErrorResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }


    public void doSearchCustomer(RequestBody body) {
        Disposable disposable = ApiService.getApi().searchEmp(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        CustomerListResult listResult = new Gson().fromJson(result,CustomerListResult.class);
                        if(Contents.SUCCESS_CODE.equals(listResult.getRespCode())) {
                            baseview.customerListResult(listResult.getList());
                        }else {
                            throw new Exception(listResult.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.customerErrorResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

}
