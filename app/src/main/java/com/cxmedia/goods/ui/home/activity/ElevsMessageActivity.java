package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.ElevsMsgAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ElevsMessageActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView,View.OnClickListener,BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_elevs_msg)
    RecyclerView rvElevsMsg;
    private List<String> mList;
    private ElevsMsgAdapter elevsMsgAdapter;
    private OrderPresenter orderPresenter;
    private String orderNo;

    @Override
    public void initView() {
        Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderNo");
        mList = new ArrayList<>();
        mList.add("i");
        elevsMsgAdapter = new ElevsMsgAdapter(mList);
        elevsMsgAdapter.setOnItemClickListener(this);
        rvElevsMsg.setLayoutManager(new LinearLayoutManager(this));
        rvElevsMsg.setAdapter(elevsMsgAdapter);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        TreeMap<String,String> map = RequestUtils.orderDetail(orderNo);
        orderPresenter.doGetOrderDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_elevs_msg;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,ElevsMessageDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void orderInfoError(String errorMsg) {

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
