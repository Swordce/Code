package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.R;

import java.util.List;

public class BillAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private boolean isShowDate = false;

    public BillAdapter(@Nullable List<Integer> data,boolean isShowDate) {
        super(R.layout.activity_bill_item,data);
        this.isShowDate = isShowDate;
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {

        if (isShowDate) {
            helper.setGone(R.id.re_date,true);
        }else {
            helper.setGone(R.id.re_date,false);
        }
        helper.addOnClickListener(R.id.iv_date_picker);
        helper.setImageResource(R.id.iv_pay_type,item);
    }
}
