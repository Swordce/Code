package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.cxmedia.goods.widgets.PayPsdInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_code_1)
    PayPsdInputView psdInputView1;
    @BindView(R.id.et_code_2)
    PayPsdInputView psdInputView2;
    @BindView(R.id.tv_step)
    TextView tvStep;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        psdInputView1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 6) {
                    psdInputView1.setVisibility(View.GONE);
                    psdInputView2.setVisibility(View.VISIBLE);
                    psdInputView2.requestFocus();
                    tvStep.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }

    @OnClick(R.id.tv_step)
    public void onViewClicked() {
        if(getIntent().getBooleanExtra("refund",false)) {

        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferencesUtil.getInstance().putString("name","123165465416");
            startActivity(intent);
            AppManager.getAppManager().finishActivity();
        }

    }
}
