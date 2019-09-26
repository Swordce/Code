package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.PhoneCode;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCodeVerificationActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView,View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_retry_time)
    TextView tvRetryTime;
    @BindView(R.id.et_v_code)
    PhoneCode phoneCode;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private OrderPresenter orderPresenter;

    private String empNo;
    private boolean isSendSmsCode = false;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        empNo = (String) Cache.get("empNo");
        tvPhone.setText(empNo);
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                Intent intent = new Intent(PhoneCodeVerificationActivity.this,SetPayPasswordActivity.class);
                if(getIntent().getBooleanExtra("refund",false)) {
                    intent.putExtra("refund",true);
                    intent.putExtra("verificationCode",code);
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
        getSMSCode();
    }

    private void getSMSCode() {
        if(!TextUtils.isEmpty(empNo)) {
            TreeMap<String,String> map = RequestUtils.sendSmsCode(empNo);
            orderPresenter.doGetSmsCode(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
        }
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
            isSendSmsCode = false;
            tvRetryTime.setText("重新发送");
        }
    };


    @OnClick({ R.id.tv_retry_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_retry_time:
                if(!isSendSmsCode) {
                    getSMSCode();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void orderInfoError(String errorMsg) {
        ToastUtils.showShortToast(this,errorMsg);
        isSendSmsCode = false;
    }

    @Override
    public void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result) {

    }

    @Override
    public void orderInfoListResult(List<OrderInfoListResult.RowsBean> result) {

    }

    @Override
    public void OrderSettleResult(OrderCategroyResult result) {

    }

    @Override
    public void orderDetailResult(OrderDetailResult.DataBean result) {

    }

    @Override
    public void getSmsCodeResult(String result) {
        ToastUtils.showShortToast(this,result);
        isSendSmsCode = true;
        downTimer.start();
    }

    @Override
    public void saveFaceSuccessResult(String result) {

    }

    @Override
    public void faceLoginResult(LoginResult result) {

    }

    @Override
    public void resetRefundPasswordResult(CommonResult result) {

    }

    @Override
    public void faceRefundResult(String result) {

    }

    @Override
    public void passwordRefundResult(String result) {

    }

    @Override
    public void setPresenter(OrderPresenter presenter) {
        if(presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }
}
