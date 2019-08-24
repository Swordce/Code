package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.R;

import java.util.List;

public class ShopManagerAdapter extends BaseQuickAdapter<LoginResult.MchtListBean, BaseViewHolder> {

    public ShopManagerAdapter(@Nullable List<LoginResult.MchtListBean> data) {
        super(R.layout.activity_shop_manager_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoginResult.MchtListBean item) {
        helper.setText(R.id.tv_shop_name,item.getMchtName());
    }
}
