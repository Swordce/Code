package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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
import com.cxmedia.goods.ui.adapter.BillAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class BillActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView,AdapterView.OnItemSelectedListener, View.OnClickListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.bill_type)
    NiceSpinner spinnerBillType;
    @BindView(R.id.bill_customer)
    NiceSpinner spinnerBillCustomer;
    @BindView(R.id.bill_period)
    NiceSpinner spinnerBillPeriod;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    private BillAdapter billAdapter;
    private List<String> period;
    private List<String> type;

    private OrderPresenter orderPresenter;
    private String mchtNo;
    private int pageNum = 0;
    private int pageSize = 20;
    private int payChannelType = 0;//10--微信，20---支付宝
    private String beginTime;
    private String endTime;
    private List<OrderInfoListResult.RowsBean> mList;

    @Override
    public void initView() {
        type = new LinkedList<>(Arrays.asList( "消费订单","微信", "支付宝"));
        List<String> cutomer = new LinkedList<>(Arrays.asList("店员/门店", "店员", "门店"));
        List<String> other = new LinkedList<>(Arrays.asList("其他"));
        period = new LinkedList<>(Arrays.asList("账单查询","日账单", "周账单","月账单"));
        spinnerBillType.attachDataSource(type);
        spinnerBillCustomer.attachDataSource(cutomer);
        spinnerBillPeriod.attachDataSource(period);
        spinnerBillType.setOnItemSelectedListener(this);
        spinnerBillPeriod.setOnItemSelectedListener(this);
        toolbar.setNavigationOnClickListener(this);

        mList = new ArrayList<>();
        billAdapter = new BillAdapter(mList,true);
        billAdapter.setOnItemClickListener(this);
        billAdapter.setOnItemChildClickListener(this);
        rvBill.setLayoutManager(new LinearLayoutManager(this));
        rvBill.setAdapter(billAdapter);
    }

    @Override
    public void getData() {
        mchtNo = (String) Cache.get("mchtNo");
        pageNum = 0;
        getOrder();
    }

    private void getOrder() {
        TreeMap<String,String> map = RequestUtils.orderInfoList(mchtNo,pageNum,pageSize,payChannelType,beginTime,endTime);
        orderPresenter.doGetOrderInfoList(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        spinnerBillPeriod.setSelectedIndex(0);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bill;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,ElevsMessageDetailActivity.class);
        intent.putExtra("orderNo",mList.get(position).getOrderNo());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.bill_type:
                if(type.get(position).contains("微信")) {
                    pageNum = 0;
                    payChannelType = 10;
                    getOrder();
                }else if(type.get(position).contains("支付宝")) {
                    pageNum = 0;
                    payChannelType = 20;
                    getOrder();
                }else {
                    pageNum = 0;
                    payChannelType = 0;
                    getOrder();
                }
                break;
            case R.id.bill_period:
                if (period.get(position).contains("周账单")) {
                    Intent intent = new Intent(this,WeekBillActivity.class);
                    intent.putExtra(WeekBillActivity.BILL_TYPE,0);
                    intent.putExtra("title",period.get(position));
                    startActivityForResult(intent,0);
                }else if(period.get(position).contains("月账单")) {
                    Intent intent = new Intent(this,WeekBillActivity.class);
                    intent.putExtra(WeekBillActivity.BILL_TYPE,1);
                    intent.putExtra("title",period.get(position));
                    startActivityForResult(intent,0);
                }else if(period.get(position).contains("日账单")) {
                    Intent intent = new Intent(this,WeekBillActivity.class);
                    intent.putExtra(WeekBillActivity.BILL_TYPE,2);
                    intent.putExtra("title",period.get(position));
                    startActivityForResult(intent,0);
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        new SlyCalendarDialog()
                .setSingle(true)
                .setHeaderColor(ContextCompat.getColor(this,R.color.theme_color))
                .setSelectedColor(ContextCompat.getColor(this,R.color.theme_color))
                .setStartDate(Calendar.getInstance().getTime())
                .setFirstMonday(true)
                .setCallback(new SlyCalendarDialog.Callback() {
                    @Override
                    public void onCancelled() {

                    }

                    @Override
                    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
                        String startTime = firstDate.get(Calendar.YEAR) + "-" + (firstDate.get(Calendar.MONTH)+ 1) +"-" + firstDate.get(Calendar.DAY_OF_MONTH);
                        Toast.makeText(BillActivity.this, startTime, Toast.LENGTH_LONG).show();
                    }
                })
                .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
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
        mList.clear();
        mList.addAll(result);
        billAdapter.setNewData(mList);
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
