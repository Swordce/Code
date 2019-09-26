package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;

import java.util.List;

public interface IOrderView extends IBaseView<OrderPresenter> {
    void orderInfoError(String errorMsg);
    void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result);
    void orderInfoListResult(List<OrderInfoListResult.RowsBean> result);
    void OrderSettleResult(OrderCategroyResult result);
    void orderDetailResult(OrderDetailResult.DataBean result);
    void getSmsCodeResult(String result);
    void saveFaceSuccessResult(String result);
    void faceLoginResult(LoginResult result);
    void resetRefundPasswordResult(CommonResult result);
    void faceRefundResult(String result);
    void passwordRefundResult(String result);
}
