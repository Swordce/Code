package com.cxmedia.goods.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.R;
import com.cxmedia.goods.utils.GlideApp;

import java.util.List;

public class CouponAdapter extends BaseQuickAdapter<CouponListResult.ListBean,BaseViewHolder> {

    public CouponAdapter(@Nullable List<CouponListResult.ListBean> data) {
        super(R.layout.activity_coupon_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListResult.ListBean item) {
        GlideApp.with(mContext).load(item.getThumbnail()).placeholder(R.drawable.icon).into((ImageView)helper.getView(R.id.iv_thumb));
        if(item.getCouponType().equals("1")) {
            if(!TextUtils.isEmpty(item.getDiscount())) {

                helper.setText(R.id.tv_coupon_name,"折扣券立打"+item.getDiscount()+"折");
            }else {
                helper.setText(R.id.tv_coupon_name,"折扣券立打0元");
            }
        }else {
            if(!TextUtils.isEmpty(item.getSubtractionAmt())) {
                helper.setText(R.id.tv_coupon_name,"优惠券立减"+item.getSubtractionAmt()+"元");
            }else {
                helper.setText(R.id.tv_coupon_name,"优惠券立减0元");
            }
        }

        helper.addOnClickListener(R.id.tv_look)
                .addOnClickListener(R.id.tv_delete);
    }
}
