package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.MVP.model.MessageModel;
import com.cxmedia.goods.R;

import java.util.List;

public class ServerMsgAdapter extends BaseQuickAdapter<MessageModel.RowsBean,BaseViewHolder> {

    public ServerMsgAdapter(@Nullable List<MessageModel.RowsBean> data) {
        super(R.layout.fragment_server_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageModel.RowsBean item) {
        helper.setText(R.id.tv_title,item.getName())
                .setText(R.id.tv_content,item.getContent());
    }
}
