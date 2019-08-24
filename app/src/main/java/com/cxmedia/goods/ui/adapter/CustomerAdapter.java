package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.MVP.model.CustomerListResult;
import com.cxmedia.goods.R;

import java.util.List;

public class CustomerAdapter extends BaseQuickAdapter<CustomerListResult.ListBean, BaseViewHolder> {


    public CustomerAdapter(@Nullable List<CustomerListResult.ListBean> data) {
        super(R.layout.activity_customer_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerListResult.ListBean item) {
        helper.setText(R.id.tv_name, item.getEmpName());
        helper.addOnClickListener(R.id.tv_edit)
                .addOnClickListener(R.id.tv_customer_delete);
    }
}
