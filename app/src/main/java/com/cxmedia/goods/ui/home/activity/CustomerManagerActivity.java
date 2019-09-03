package com.cxmedia.goods.ui.home.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CustomerListResult;
import com.cxmedia.goods.MVP.presenter.CustomerPresenter;
import com.cxmedia.goods.MVP.view.ICustomerView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.cache.MchtCache;
import com.cxmedia.goods.ui.adapter.CustomerAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerManagerActivity extends BaseMvpActivity<CustomerPresenter> implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, ICustomerView, TextWatcher, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_jump_create_customer)
    TextView tvJumpCreateCutomer;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_customer)
    RecyclerView rvCustomer;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    private CustomerAdapter customerAdapter;

    private CustomerPresenter customerPresenter;
    private List<CustomerListResult.ListBean> mList;
    private int REQUEST_CODE = 1001;

    private String mchtNo;
    private int pageIndex = 1;
    private int pageSize = 10;
    private int deletePos = -1;

    @Override
    public void initView() {
        mList = new ArrayList<>();
        toolbar.setNavigationOnClickListener(this);
        customerAdapter = new CustomerAdapter(mList);
        customerAdapter.setOnItemChildClickListener(this);
        customerAdapter.setEnableLoadMore(false);
        rvCustomer.setLayoutManager(new LinearLayoutManager(this));
        rvCustomer.setAdapter(customerAdapter);
        mchtNo = (String) Cache.get("mchtNo");
        String name = MchtCache.getInstance().getMchtName(mchtNo);
        tvShopName.setText(name);
//        etKeyword.addTextChangedListener(this);
    }

    @Override
    public void getData() {
        pageIndex = 1;
        doRequest(pageIndex,"");
    }

    private void doRequest(int pageIndex,String searchKey) {
        if(!TextUtils.isEmpty(searchKey)) {
            TreeMap<String, String> map = RequestUtils.searchCustomerStr(mchtNo,searchKey);
            customerPresenter.doSearchCustomer(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
        }else {
            TreeMap<String, String> map = RequestUtils.customerListStr(mchtNo,pageIndex,pageSize);
            customerPresenter.doGetCustomerList(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_customer;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1) {
            if(requestCode == REQUEST_CODE) {
                pageIndex = 1;
                doRequest(pageIndex,"");
            }
        }
    }

    @OnClick({R.id.tv_jump_create_customer, R.id.tv_search})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_jump_create_customer:
                Intent intent = new Intent(this, CreateCustomerActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.tv_search:
                String keyword = etKeyword.getText().toString();
                pageIndex = 1;
                doRequest(pageIndex,keyword);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
        switch (view.getId()) {
            case R.id.tv_edit:
                CustomerListResult.ListBean bean = mList.get(position);
                Intent intent = new Intent(this, EditCustomerActivity.class);
                intent.putExtra("empNo",bean.getEmpNo());
                intent.putExtra("name",bean.getEmpName());
                intent.putExtra("type",bean.getEmpType());
                intent.putExtra("phone",bean.getPhone());
                intent.putExtra("haveCoupon",bean.getHaveCoupon());
                intent.putExtra("haveRefund",bean.getHaveRefund());
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.tv_customer_delete:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("是否删除该员工?")
                        .setTitle("删除员工")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePos = position;
                                TreeMap<String, String> map = RequestUtils.deleteCustomerStr(mList.get(position).getEmpNo(), mchtNo);
                                customerPresenter.doDeleteCustomer(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();

                dialog.show();

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
        ToastUtils.showShortToast(this,result);
        mList.remove(deletePos);
        customerAdapter.notifyItemChanged(deletePos);
        deletePos = -1;
    }

    @Override
    public void customerListResult(List<CustomerListResult.ListBean> result) {
        mList.clear();
        mList.addAll(result);
        customerAdapter.setNewData(mList);
    }


    @Override
    public void customerErrorResult(String error) {
        ToastUtils.showShortToast(this,error);
    }

    @Override
    public void setPresenter(CustomerPresenter presenter) {
        if (presenter == null) {
            customerPresenter = new CustomerPresenter();
            customerPresenter.attachView(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String key = s.toString();
        if(!TextUtils.isEmpty(key)) {
            pageIndex = 1;
            doRequest(pageIndex,key);
        }else {
            pageIndex = 1;
            doRequest(pageIndex,"");
        }
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
