package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.BillAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class BillActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
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
    @Override
    public void initView() {
        List<String> type = new LinkedList<>(Arrays.asList("消费订单", "微信", "支付宝"));
        List<String> cutomer = new LinkedList<>(Arrays.asList("店员/门店", "店员", "门店"));
        List<String> other = new LinkedList<>(Arrays.asList("其他"));
        period = new LinkedList<>(Arrays.asList("账单查询","日账单", "周账单","月账单"));
        spinnerBillType.attachDataSource(type);
        spinnerBillCustomer.attachDataSource(cutomer);
        spinnerBillPeriod.attachDataSource(period);
        spinnerBillPeriod.setOnItemSelectedListener(this);
        toolbar.setNavigationOnClickListener(this);

        List<Integer> mList = new ArrayList<>();
        mList.add(R.drawable.ic_wechat_pay);
        mList.add(R.drawable.ic_alipay);
        billAdapter = new BillAdapter(mList,true);
        billAdapter.setOnItemClickListener(this);
        billAdapter.setOnItemChildClickListener(this);
        rvBill.setLayoutManager(new LinearLayoutManager(this));
        rvBill.setAdapter(billAdapter);
    }

    @Override
    public void getData() {

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
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
}
