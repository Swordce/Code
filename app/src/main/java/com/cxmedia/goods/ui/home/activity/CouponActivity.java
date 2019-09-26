package com.cxmedia.goods.ui.home.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.CouponAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends BaseMvpActivity<CouponPresenter> implements ICouponView, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private CouponAdapter couponAdapter;
    private List<CouponListResult.ListBean> mList;

    private CouponPresenter couponPresenter;
    private int deletePos = -1;

    @Override
    public void initView() {

        toolbar.setNavigationOnClickListener(this);
        mList = new ArrayList<>();
        couponAdapter = new CouponAdapter(mList);
        couponAdapter.setOnItemChildClickListener(this);
        rvCoupon.setLayoutManager(new LinearLayoutManager(this));
        rvCoupon.setAdapter(couponAdapter);
    }

    @Override
    public void getData() {
        String mchtNo = (String) Cache.get("mchtNo");
        TreeMap<String, String> map = RequestUtils.couponListStr(mchtNo);
        couponPresenter.getCouponList(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_coupon;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    @OnClick(R.id.tv_create_coupon)
    public void onViewClicked() {
        Intent intent = new Intent(this, CreateCouponActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
        switch (view.getId()) {
            case R.id.tv_look:
                Intent intent = new Intent(this, CouponDetailActivity.class);
                intent.putExtra("couponId", mList.get(position).getId() + "");
                intent.putExtra("couponType",mList.get(position).getCouponType());
                startActivity(intent);
                break;
            case R.id.tv_delete:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("是否删除优惠券?")
                        .setTitle("删除优惠券")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePos = position;
                                String mchtNo = (String) Cache.get("mchtNo");
                                TreeMap<String, String> map = RequestUtils.deleteCouponStr(mchtNo, mList.get(position).getId() + "");
                                couponPresenter.deleteCouponDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();

                dialog.show();
                break;
        }

    }


    @Override
    public void couponDetailResult(CouponListResult.ListBean result) {

    }

    @Override
    public void couponListResult(List<CouponListResult.ListBean> result) {
        mList.clear();
        mList.addAll(result);
        couponAdapter.setNewData(mList);
    }


    @Override
    public void deleteCouponSuccessResult(String result) {
        ToastUtils.showShortToast(this, result);
        mList.remove(deletePos);
        couponAdapter.notifyDataSetChanged();
        deletePos = -1;
    }

    @Override
    public void addCouponSuccessResult(String result) {

    }

    @Override
    public void couponFailedResult(String errorMsg) {
        ToastUtils.showShortToast(this, errorMsg);
    }

    @Override
    public void uploadFileResult(CommonResult result,int type) {

    }

    @Override
    public void setPresenter(CouponPresenter presenter) {
        if (presenter == null) {
            couponPresenter = new CouponPresenter();
            couponPresenter.attachView(this);
        }
    }
}
