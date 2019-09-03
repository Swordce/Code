package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.presenter.UserPresenter;

public interface IUserView extends IBaseView<UserPresenter> {
    void userInfoError(String error);
}
