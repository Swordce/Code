package com.cxmedia.goods.ui.home.activity;

import android.text.TextUtils;
import android.view.View;
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

public class EditCustomerActivity extends BaseMvpActivity<CustomerPresenter> implements ICustomerView {

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

    private CustomerPresenter customerPresenter;

    private String empNo;

    @Override
    public void initView() {
        empNo = getIntent().getStringExtra("empNo");
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        String phone = getIntent().getStringExtra("phone");
        String haveCoupon = getIntent().getStringExtra("haveCoupon");
        String haveRefund = getIntent().getStringExtra("haveRefund");

        etCustomerName.setText(name);
        etCustomerPhone.setText(empNo);
        etCustomerPosition.setText(type);

        if (!TextUtils.isEmpty(haveCoupon) && "1".equals(haveCoupon)) {
            cxCoupon.setChecked(true);
        }
        if (!TextUtils.isEmpty(haveRefund) && "1".equals(haveRefund)) {
            cxRefund.setChecked(true);
        }
        etCustomerPhone.setEnabled(false);
        cxRefund.setEnabled(false);
        cxCoupon.setEnabled(false);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_customer_edit;
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
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(position)) {
                        if (cxCoupon.isChecked() || cxRefund.isChecked()) {
                            TreeMap<String, String> map = RequestUtils.editCustomerStr(empNo, (String) Cache.get("mchtNo"), name, position,(String) Cache.get("empNo"));
                            customerPresenter.doEditCusomer(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                        } else {
                            ToastUtils.showShortToast(this, "请添加管理员权限");
                        }

                    } else {
                        ToastUtils.showShortToast(this, "职称不能为空");
                    }
                } else {
                    ToastUtils.showShortToast(this, "管理员名称不能为空");
                }
                break;
        }
    }

    @Override
    public void addCustomerResult(String result) {

    }

    @Override
    public void editCustomerResult(String result) {

    }

    @Override
    public void deleteCustomerResult(String result) {

    }

    @Override
    public void customerListResult(List<CustomerListResult.ListBean> result) {

    }

    @Override
    public void customerErrorResult(String error) {
        ToastUtils.showShortToast(this, error);
    }

    @Override
    public void setPresenter(CustomerPresenter presenter) {
        if (presenter == null) {
            customerPresenter = new CustomerPresenter();
            customerPresenter.attachView(this);
        }
    }
}
