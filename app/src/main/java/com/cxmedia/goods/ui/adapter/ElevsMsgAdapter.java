package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.R;

import java.util.List;

public class ElevsMsgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ElevsMsgAdapter(@Nullable List<String> data) {
        super(R.layout.activity_elevs_msg_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
