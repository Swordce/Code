package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.cxmedia.goods.widgets.PhoneCode;

import butterknife.BindView;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_v_code)
    PhoneCode etVcode;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        etVcode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                Intent intent = new Intent(BindPhoneActivity.this, SetPayPasswordActivity.class);
                startActivity(intent);
            }

            @Override
            public void onInput() {

            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }
}
