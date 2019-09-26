package com.cxmedia.goods.ui.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.CouponAdapter;
import com.cxmedia.goods.ui.adapter.CouponTabAdapter;
import com.cxmedia.goods.ui.adapter.TabVpAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.home.fragment.CreateCouponFragment;
import com.cxmedia.goods.ui.home.fragment.CreateDiscountFragment;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.viewpager.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCouponActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    MyViewPager vp;
    private List<Fragment> mFragment;
    private List<String> titles;
    private CouponTabAdapter tabVpAdapter;

    @Override
    public void initView() {
        titles = new ArrayList<>();
        mFragment = new ArrayList<>();
        titles.add("代金券");
        titles.add("折扣券");
        mFragment.add(CreateCouponFragment.newInstance());
        mFragment.add(CreateDiscountFragment.newInstance());

        tabVpAdapter = new CouponTabAdapter(this, getSupportFragmentManager(), mFragment, titles);
        vp.setAdapter(tabVpAdapter);
        mTab.setupWithViewPager(vp);
        for (int i = 0; i < mFragment.size(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(tabVpAdapter.getCustomView(i));
                if (i == 0) {
                    ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setSelected(true);
                    ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setTextColor(ContextCompat.getColor(CreateCouponActivity.this,R.color.color_white));
                }
            }
        }

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setSelected(true);
                ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setTextColor(ContextCompat.getColor(CreateCouponActivity.this,R.color.color_white));
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setSelected(false);
                ((TextView) tab.getCustomView().findViewById(R.id.tv_album_title)).setTextColor(ContextCompat.getColor(CreateCouponActivity.this,R.color.color_black));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_create_coupon;
    }


    @OnClick({R.id.iv_close,R.id.tv_create_coupon})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_create_coupon:
                Fragment fragment = mFragment.get(vp.getCurrentItem());
                if(fragment instanceof CreateCouponFragment) {
                    ((CreateCouponFragment) fragment).uploadCoupon();
                }else if(fragment instanceof CreateDiscountFragment) {
                    ((CreateDiscountFragment) fragment).uploadCoupon();
                }
                break;
        }
    }

}
