package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.SelectButton;

import butterknife.BindView;

public class LifeSettingActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.select_button_login)
    SelectButton sbLogin;
    @BindView(R.id.select_button_refund)
    SelectButton sbRefund;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        sbLogin.setOffline(false);
        sbLogin.setOnButtonClickListener(new SelectButton.OnButtonClickListener() {
            @Override
            public void onClick(boolean click) {
                sbLogin.setOffline(click);
            }
        });
        sbRefund.setOffline(false);
        sbRefund.setOnButtonClickListener(new SelectButton.OnButtonClickListener() {
            @Override
            public void onClick(boolean click) {
                sbRefund.setOffline(click);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_life_setting;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }
}
