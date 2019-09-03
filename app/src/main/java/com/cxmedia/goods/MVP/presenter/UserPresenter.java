package com.cxmedia.goods.MVP.presenter;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.view.IUserView;
import com.cxmedia.goods.common.Contents;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UserPresenter extends BasePresenter<IUserView> {

    public void doSaveAppVersion(RequestBody body) {
        Disposable disposable = ApiService.getApi().saveAppVersion(body)
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
                        baseview.userInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doGetAppVersion() {
        Disposable disposable = ApiService.getApi().getAppVersion()
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
                        baseview.userInfoError(throwable.getMessage());
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

                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.userInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

}
