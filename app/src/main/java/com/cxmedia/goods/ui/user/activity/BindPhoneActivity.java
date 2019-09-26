package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.PhoneCode;
import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class BindPhoneActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView,View.OnClickListener {

    @BindView(R.id.et_v_code)
    PhoneCode etVcode;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    private String empNo;
    private int bindType = -1;
    private OrderPresenter orderPresenter;


    @Override
    public void initView() {
        empNo = (String) Cache.get("empNo");
        bindType = getIntent().getIntExtra("faceType", -1);
        etPhone.setText(empNo);
        toolbar.setNavigationOnClickListener(this);
        etVcode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                if(bindType == Contents.OPEN_FACE_LOGIN || bindType == Contents.OPEN_FACE_REFUND) {
                    Intent intent = new Intent(BindPhoneActivity.this, FacePreviewActivity.class);
                    intent.putExtra("faceType",bindType);
                    intent.putExtra("smsCode",code);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(BindPhoneActivity.this, SetPayPasswordActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onInput() {

            }
        });
    }

    @Override
    public void getData() {
        TreeMap<String,String> map = RequestUtils.sendSmsCode(empNo);
        orderPresenter.doGetSmsCode(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
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
        ToastUtils.showShortToast(this,result);
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
