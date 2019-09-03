package com.cxmedia.goods.MVP.presenter;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.MessageDetailModel;
import com.cxmedia.goods.MVP.model.MessageModel;
import com.cxmedia.goods.MVP.view.IMessageView;
import com.cxmedia.goods.common.Contents;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MessagePresenter extends BasePresenter<IMessageView> {

    public void doGetMessageList(RequestBody body) {
        Disposable disposable = ApiService.getApi().messageInfoList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        MessageModel result = new Gson().fromJson(json,MessageModel.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode())) {
                            baseview.messageListInfo(result.getRows());
                        }else {
                            throw new Exception(result.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.messageInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doMessageDetail(RequestBody body) {
        Disposable disposable = ApiService.getApi().messageDetail(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        MessageDetailModel result = new Gson().fromJson(json,MessageDetailModel.class);
                        if(Contents.SUCCESS_CODE.equals(result.getRespCode()) || Contents.SUCCESS_CODE_1.equals(result.getRespCode())) {
                            baseview.messageDetailInfo(result.getData());
                        }else {
                            throw new Exception("获取失败,请稍后重试!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.messageInfoError(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

}
