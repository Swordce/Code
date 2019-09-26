package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.R;
import com.cxmedia.goods.utils.CalendarUtil;

import java.util.List;

public class WeekBillAdapter extends BaseQuickAdapter<OrderCategroyResult.PageDataBean.RowsBean, BaseViewHolder> {

    private boolean isShowDate = false;
    private List<OrderCategroyResult.PageDataBean.RowsBean> mList;

    public WeekBillAdapter(@Nullable List<OrderCategroyResult.PageDataBean.RowsBean> data, boolean isShowDate) {
        super(R.layout.activity_bill_item, data);
        this.isShowDate = isShowDate;
        this.mList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderCategroyResult.PageDataBean.RowsBean item) {
        if (isShowDate) {
            helper.setGone(R.id.re_date, true);
        } else {
            helper.setGone(R.id.re_date, false);
        }
        helper.addOnClickListener(R.id.iv_date_picker);
        if (item.getPayChannelType().equals("10")) {
            helper.setImageResource(R.id.iv_pay_type, R.drawable.ic_wechat_pay);
        } else {
            helper.setImageResource(R.id.iv_pay_type, R.drawable.ic_alipay);
        }
        CalendarUtil util = new CalendarUtil();
        String currentDate = util.getDateString(item.getOrderTime());
        String previosDate = helper.getAdapterPosition() >= 1 ? util.getDateString(mList.get(helper.getAdapterPosition() - 1).getOrderTime()) : "";
        if (!TextUtils.equals(currentDate, previosDate)) {
            if (isShowDate) {
                helper.setGone(R.id.re_date, true);
            }
        } else {
            helper.setGone(R.id.re_date, false);
        }

        helper.setText(R.id.tv_title, item.getGoodsDesc())
                .setText(R.id.tv_money, "+" + item.getOrderAmt())
                .setText(R.id.tv_time, currentDate);
    }
}
