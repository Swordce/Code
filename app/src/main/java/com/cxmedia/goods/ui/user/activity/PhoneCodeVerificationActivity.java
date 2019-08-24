package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.PhoneCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCodeVerificationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_retry_time)
    TextView tvRetryTime;
    @BindView(R.id.et_v_code)
    PhoneCode phoneCode;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                Intent intent = new Intent(PhoneCodeVerificationActivity.this,SetPayPasswordActivity.class);
                if(getIntent().getBooleanExtra("refund",false)) {
                    intent.putExtra("refund",true);
                }
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
        return R.layout.activity_phone_code_verification;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }

    private CountDownTimer downTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            tvRetryTime.setText(time + "s后重发");
        }

        @Override
        public void onFinish() {
            tvRetryTime.setText("重新发送");
        }
    };


    @OnClick({ R.id.tv_retry_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_retry_time:
                downTimer.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
