package com.cxmedia.goods.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.cache.MchtCache;
import com.cxmedia.goods.ui.adapter.ShopManagerAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import butterknife.BindView;

public class ShopManagerActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    private ShopManagerAdapter shopManagerAdapter;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        shopManagerAdapter = new ShopManagerAdapter(MchtCache.getInstance().getMchtList());
        rvShop.setLayoutManager(new LinearLayoutManager(this));
        rvShop.setAdapter(shopManagerAdapter);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_shop_manager;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }
}
