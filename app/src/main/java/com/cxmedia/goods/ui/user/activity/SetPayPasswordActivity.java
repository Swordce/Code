package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.PayPsdInputView;
import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPasswordActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView,View.OnClickListener {

    @BindView(R.id.et_code_1)
    PayPsdInputView psdInputView1;
    @BindView(R.id.et_code_2)
    PayPsdInputView psdInputView2;
    @BindView(R.id.tv_step)
    TextView tvStep;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private OrderPresenter orderPresenter;
    private String verificationCode;
    private String password1;
    private String password2;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
        verificationCode = getIntent().getStringExtra("verificationCode");
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
            password1 = psdInputView1.getPasswordString();
            password2 = psdInputView2.getPasswordString();
            String empNo = (String) Cache.get("empNo");
            if(password1.equals(password2)) {
                TreeMap<String,String> map = RequestUtils.orderResetOrderPassword(verificationCode,empNo,password1);
                orderPresenter.doResetOrderPassword(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
            }else {
                ToastUtils.showShortToast(this,"两次密码输入不一致，请重新设置");
                psdInputView1.setText("");
                psdInputView2.setText("");
                psdInputView1.setVisibility(View.VISIBLE);
                psdInputView2.setVisibility(View.GONE);
                psdInputView1.requestFocus();
                tvStep.setVisibility(View.GONE);
            }
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferencesUtil.getInstance().putString("name","123165465416");
            startActivity(intent);
            AppManager.getAppManager().finishActivity();
        }

    }

    @Override
    public void orderInfoError(String errorMsg) {
        ToastUtils.showShortToast(this,errorMsg);
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

    }

    @Override
    public void saveFaceSuccessResult(String result) {

    }

    @Override
    public void faceLoginResult(LoginResult result) {

    }

    @Override
    public void resetRefundPasswordResult(CommonResult result) {
        ToastUtils.showShortToast(this,result.getRespMsg());
        AppManager.getAppManager().finishActivity(PhoneCodeVerificationActivity.class);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void faceRefundResult(String result) {

    }

    @Override
    public void passwordRefundResult(String result) {
        ToastUtils.showShortToast(this,result);

    }

    @Override
    public void setPresenter(OrderPresenter presenter) {
        if(presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }
}
