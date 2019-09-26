package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.presenter.LoginPresenter;

public interface ILoginView extends IBaseView<LoginPresenter> {

    void loginSuccessResult(LoginResult result);
    void loginFailedResult(String errorMsg);
    void editPasswordResult(String result);
    void loginOutSuccess(String result);
}
