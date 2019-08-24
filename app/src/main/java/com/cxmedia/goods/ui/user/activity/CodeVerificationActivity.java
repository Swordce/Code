package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.PayPsdInputView;

import butterknife.BindView;

public class CodeVerificationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_pwd)
    PayPsdInputView etPwd;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(!TextUtils.isEmpty(text) && text.length() == 6) {
                    Intent intent = new Intent(CodeVerificationActivity.this,SetPayPasswordActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_code_verification;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }
}
