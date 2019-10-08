package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.ui.home.fragment.DisAmtFragment;
import com.cxmedia.goods.ui.home.fragment.FullAmtFragment;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class CouponDetailActivity extends BaseMvpActivity<CouponPresenter> implements View.OnClickListener, ICouponView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        Intent intent = getIntent();
        String couponType = intent.getStringExtra("couponType");
        String couponId = intent.getStringExtra("couponId");
        if(couponType.equals("1") || couponType.contains("01")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_content, DisAmtFragment.getInstance(couponId)).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_content, FullAmtFragment.getInstance(couponId)).commit();
        }
    }

    @Override
    public void getData() {

    }


    @Override
    public int getLayout() {
        return R.layout.activity_coupon_detail;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }


    @Override
    public void couponDetailResult(CouponListResult.ListBean result) {

    }

    @Override
    public void couponListResult(List<CouponListResult.ListBean> result) {

    }

    @Override
    public void deleteCouponSuccessResult(String result) {

    }

    @Override
    public void addCouponSuccessResult(String result) {

    }

    @Override
    public void couponFailedResult(String errorMsg) {
        ToastUtils.showShortToast(this,errorMsg);
    }

    @Override
    public void uploadFileResult(CommonResult result,int type) {

    }

    @Override
    public void setPresenter(CouponPresenter presenter) {
    }
}

