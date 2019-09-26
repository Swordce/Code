package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
import com.cxmedia.goods.ui.adapter.WeekBillAdapter;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeekBillActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_week_bill)
    RecyclerView rvWeekBill;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_previous_period)
    TextView tvPreviousPeriod;
    @BindView(R.id.tv_next_period)
    TextView tvNextPeriod;
    @BindView(R.id.tv_today_count)
    TextView tvTodayCount;
    @BindView(R.id.tv_today_money)
    TextView tvTodayMoney;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_refund_count)
    TextView tvRefundCount;
    @BindView(R.id.tv_refund_money)
    TextView tvRefundMoney;
    @BindView(R.id.tv_coupon_count)
    TextView tvCouponCount;
    @BindView(R.id.tv_coupon_money)
    TextView tvCouponMoney;

    private WeekBillAdapter billAdapter;
    private List<OrderCategroyResult.PageDataBean.RowsBean> mList;
    public static final String BILL_TYPE = "bill_type";//0---周账单,1---月账单
    private int billType = 0;

    private OrderPresenter orderPresenter;
    private String mchtNo;
    private int pageNum = 0;
    private int pageSize = 20;
    private int payChannelType = 0;//10--微信，20---支付宝
    private String beginTime;
    private String endTime;
    private String selectTime;

    @Override
    public void initView() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        billType = intent.getIntExtra(BILL_TYPE, 0);
        tvTitle.setText(title);
        mchtNo = (String) Cache.get("mchtNo");
        final CalendarUtil tt = new CalendarUtil();
        selectTime = tt.getNowTime("");
        if (billType == 0) {
            tvNextPeriod.setText("下一周");
            tvPreviousPeriod.setText("上一周");
            beginTime = tt.getMondayOFWeek();
            endTime = tt.getCurrentWeekday();
        } else if (billType == 1) {
            tvPreviousPeriod.setText("上一月");
            tvNextPeriod.setText("下一月");
            beginTime = tt.getFirstDayOfMonth();
            endTime = tt.getDefaultDay();
        } else {
            tvPreviousPeriod.setText("上一日");
            tvNextPeriod.setText("下一日");
            beginTime = tt.getNowTime("");
            endTime = tt.getNowTime("");
        }
        mList = new ArrayList<>();
        billAdapter = new WeekBillAdapter(mList, false);
        billAdapter.setOnItemClickListener(this);
        rvWeekBill.setLayoutManager(new LinearLayoutManager(this));
        rvWeekBill.setAdapter(billAdapter);

        tvPreviousPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billType == 0) {
                    String s = tt.getPreviousWeekday(selectTime);
                    String e = tt.getPreviousWeekSunday(selectTime);
                    selectTime = s;
                    beginTime = s;
                    endTime = e;
                    pageNum = 0;
                    getOrder();
                } else if (billType == 1) {
                    String monthFirstDay = tt.getPreviousMonthFirst(selectTime);
                    String monthLastDay = tt.getPreviousMonthEnd(selectTime);
                    beginTime = monthFirstDay;
                    endTime = monthLastDay;
                    selectTime = beginTime;
                    pageNum = 0;
                    getOrder();
                } else {
                    String before = tt.getSpecifiedDayBefore(selectTime);
                    beginTime = before;
                    endTime = selectTime;
                    selectTime = before;
                    pageNum = 0;
                    getOrder();
                }

            }
        });

        tvNextPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billType == 0) {
                    String s = tt.getNextMonday(selectTime);
                    String e = tt.getNextSunday(selectTime);
                    selectTime = s;
                    beginTime = s;
                    endTime = e;
                    pageNum = 0;
                    getOrder();
                } else if (billType == 1) {
                    String monthFirstDay = tt.getNextMonthFirst(selectTime);
                    String monthLastDay = tt.getNextMonthEnd(selectTime);
                    beginTime = monthFirstDay;
                    endTime = monthLastDay;
                    selectTime = beginTime;
                    pageNum = 0;
                    getOrder();
                } else {
                    String before = tt.getSpecifiedDayAfter(selectTime);
                    beginTime = before;
                    endTime = selectTime;
                    selectTime = before;
                    pageNum = 0;
                    getOrder();
                }
            }
        });
    }

    @Override
    public void getData() {
        pageNum = 0;
        getOrder();
    }

    private void getOrder() {
        TreeMap<String, String> map = RequestUtils.orderSettleList(mchtNo, pageNum, pageSize, payChannelType, beginTime, endTime);
        orderPresenter.doGetOrderSettleList(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_week_bill;
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, ElevsMessageDetailActivity.class);
        intent.putExtra("orderNo",mList.get(position).getOrderNo());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        setResult(0);
        super.onDestroy();
    }

    @Override
    public void orderInfoError(String errorMsg) {
        ToastUtils.showShortToast(this, errorMsg);
    }

    @Override
    public void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result) {

    }

    @Override
    public void orderInfoListResult(List<OrderInfoListResult.RowsBean> result) {

    }

    @Override
    public void OrderSettleResult(OrderCategroyResult result) {
        tvTodayCount.setText(result.getOrderCount()+"");
        tvTodayMoney.setText(result.getSumOrderAmt()+"");
        tvTotalMoney.setText(result.getSumOrderAmt()+"");
        tvRefundCount.setText((result.getOrderCount() - result.getRealPayCount())+"");
        tvRefundMoney.setText((result.getSumOrderAmt() - result.getSumRealAmt()) +"");
        tvCouponCount.setText(result.getCouponCount()+"");
        tvCouponMoney.setText(result.getSumPreferentialAmt()+"");
        mList.clear();
        mList.addAll(result.getPageData().getRows());
        billAdapter.setNewData(mList);
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
        if (presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }

}
