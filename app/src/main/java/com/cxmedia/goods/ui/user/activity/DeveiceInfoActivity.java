package com.cxmedia.goods.ui.user.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import butterknife.BindView;

public class DeveiceInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_device;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }
}
