package com.cxmedia.goods.MVP.presenter;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.api.ApiService;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.view.ILoginView;
import com.cxmedia.goods.cache.MchtCache;
import com.cxmedia.goods.utils.Cache;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LoginPresenter extends BasePresenter<ILoginView> {


    public void doLogin(RequestBody body) {
        Disposable disposable = ApiService.getApi().login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        LoginResult model = new Gson().fromJson(responseBody.string(), LoginResult.class);
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
                            baseview.loginSuccessResult(model);
                        }else {
                            throw new Exception(model.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.loginFailedResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }


    public void doEditPassword(RequestBody body) {
        Disposable disposable = ApiService.getApi().editLoginPassword(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        LoginResult model = new Gson().fromJson(responseBody.string(), LoginResult.class);
                        if("0000".equals(model.getRespCode())) {
                            baseview.editPasswordResult("修改成功");
                        }else {
                            throw new Exception(model.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.loginFailedResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

    public void doLoginOut(RequestBody body) {
        Disposable disposable = ApiService.getApi().loginOut(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        CommonResult model = new Gson().fromJson(responseBody.string(), CommonResult.class);
                        if("0000".equals(model.getRespCode())) {
                            baseview.loginOutSuccess("");
                        }else {
                            throw new Exception(model.getRespMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseview.loginFailedResult(throwable.getMessage());
                    }
                });
        addSubscription(disposable);
    }

}
