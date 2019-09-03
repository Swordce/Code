package com.cxmedia.goods.MVP.view;

import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.MVP.model.MessageDetailModel;
import com.cxmedia.goods.MVP.model.MessageModel;
import com.cxmedia.goods.MVP.presenter.MessagePresenter;

import java.util.List;

public interface IMessageView extends IBaseView<MessagePresenter> {
    void messageInfoError(String error);
    void messageListInfo(List<MessageModel.RowsBean> result);
    void messageDetailInfo(MessageDetailModel.DataBean result);
}
