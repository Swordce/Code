package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.BillAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeekBillActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_week_bill)
    RecyclerView rvWeekBill;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_previous_period)
    TextView tvPreviousPeriod;
    @BindView(R.id.tv_next_period)
    TextView tvNextPeriod;

    private BillAdapter billAdapter;

    public static final String BILL_TYPE = "bill_type";//0---周账单,1---月账单
    private int billType = 0;

    @Override
    public void initView() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        billType = intent.getIntExtra(BILL_TYPE,0);
        tvTitle.setText(title);

        if(billType == 0) {
            tvNextPeriod.setText("下一周");
            tvPreviousPeriod.setText("上一周");
        }else if(billType == 1) {
            tvPreviousPeriod.setText("上一月");
            tvNextPeriod.setText("下一月");
        }else {
            tvPreviousPeriod.setText("上一日");
            tvNextPeriod.setText("下一日");
        }

        List<Integer> mList = new ArrayList<>();
        mList.add(R.drawable.ic_wechat_pay);
        mList.add(R.drawable.ic_alipay);
        billAdapter = new BillAdapter(mList,false);
        billAdapter.setOnItemClickListener(this);
        rvWeekBill.setLayoutManager(new LinearLayoutManager(this));
        rvWeekBill.setAdapter(billAdapter);
    }

    @Override
    public void getData() {

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
        Intent intent = new Intent(this,ElevsMessageDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        setResult(0);
        super.onDestroy();
    }
}
