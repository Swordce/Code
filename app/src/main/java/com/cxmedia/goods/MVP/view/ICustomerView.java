package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.model.CustomerListResult;
import com.cxmedia.goods.MVP.presenter.CustomerPresenter;

import java.util.List;

public interface ICustomerView extends IBaseView<CustomerPresenter> {
    void addCustomerResult(String result);
    void deleteCustomerResult(String result);
    void customerListResult(List<CustomerListResult.ListBean> result);
    void customerErrorResult(String error);
}
