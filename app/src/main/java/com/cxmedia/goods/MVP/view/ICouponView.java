package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;

import java.util.List;

public interface ICouponView extends IBaseView<CouponPresenter> {

    void couponDetailResult(CouponListResult.ListBean result);
    void couponListResult(List<CouponListResult.ListBean> result);
    void deleteCouponSuccessResult(String result);
    void addCouponSuccessResult(String result);
    void couponFailedResult(String errorMsg);
}
