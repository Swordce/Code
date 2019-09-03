package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;

public interface IOrderView extends IBaseView<OrderPresenter> {
    void orderInfoError(String errorMsg);
}
