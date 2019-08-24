package com.cxmedia.goods.ui.home.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.CustomerListResult;
import com.cxmedia.goods.MVP.presenter.CustomerPresenter;
import com.cxmedia.goods.MVP.view.ICustomerView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateCustomerActivity extends BaseMvpActivity<CustomerPresenter> implements ICustomerView {

    @BindView(R.id.et_customer_name)
    EditText etCustomerName;
    @BindView(R.id.et_customer_position)
    EditText etCustomerPosition;
    @BindView(R.id.et_customer_phone)
    EditText etCustomerPhone;
    @BindView(R.id.cx_coupon)
    CheckBox cxCoupon;
    @BindView(R.id.cx_refund)
    CheckBox cxRefund;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private CustomerPresenter customerPresenter;

    @Override
    public void initView() {
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_customer_create;
    }


    @OnClick({R.id.iv_close, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_save:
                String name = etCustomerName.getText().toString();
                String position = etCustomerPosition.getText().toString();
                String phone = etCustomerPhone.getText().toString();
                String coupon = cxCoupon.isChecked() ? "1" : "0";
                String refund = cxRefund.isChecked() ? "1" : "0";
                if(!TextUtils.isEmpty(name)) {
                    if(!TextUtils.isEmpty(position)) {
                        if(!TextUtils.isEmpty(phone)) {
                            if(cxCoupon.isChecked() || cxRefund.isChecked()) {
                                TreeMap<String,String> map = RequestUtils.addCustomerStr((String) Cache.get("empNo"),(String)Cache.get("mchtNo"),name,position,phone,coupon,refund);
                                customerPresenter.doAddCustomer(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                            }else {
                                ToastUtils.showShortToast(this,"请添加管理员权限");
                            }
                        }else {
                            ToastUtils.showShortToast(this,"手机号不能为空");
                        }
                    }else {
                        ToastUtils.showShortToast(this,"职称不能为空");
                    }
                }else {
                    ToastUtils.showShortToast(this,"管理员名称不能为空");
                }
                break;
        }
    }

    @Override
    public void addCustomerResult(String result) {
        ToastUtils.showShortToast(this,result);
        setResult(-1);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void deleteCustomerResult(String result) {

    }

    @Override
    public void customerListResult(List<CustomerListResult.ListBean> result) {

    }

    @Override
    public void customerErrorResult(String error) {

    }

    @Override
    public void setPresenter(CustomerPresenter presenter) {
        if(presenter == null) {
            customerPresenter = new CustomerPresenter();
            customerPresenter.attachView(this);
        }
    }
}
