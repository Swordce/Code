package com.cxmedia.goods.ui.home.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class CouponDetailActivity extends BaseMvpActivity<CouponPresenter> implements View.OnClickListener, ICouponView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.chart)
    LineChartView chartView;
    @BindView(R.id.tv_coupon_money)
    TextView tvCouponMoney;
    @BindView(R.id.tv_coupon_count)
    TextView tvCouponCount;
    @BindView(R.id.tv_coupon_total_money)
    TextView tvCouponTotalMoney;
    @BindView(R.id.tv_user_require)
    TextView tvUserRequire;
    @BindView(R.id.tv_use_start_date)
    TextView tvUseStartDate;
    @BindView(R.id.tv_use_date)
    TextView tvUseDate;
    @BindView(R.id.tv_have_use)
    TextView tvHaveUse;
    @BindView(R.id.tv_have_share)
    TextView tvHaveShare;
    @BindView(R.id.progress)
    SeekBar progress;
    private String[] axisX = {"02/28", "03/01", "03/02", "03/03", "03/04", "03/05"};

    private CouponPresenter couponPresenter;

    private String couponId;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);

        initChart();
    }

    @Override
    public void getData() {
        couponId = getIntent().getStringExtra("couponId");
        TreeMap<String, String> map = RequestUtils.couponDetaiStr(couponId, (String) Cache.get("mchtNo"));
        couponPresenter.getCouponDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }


    @Override
    public int getLayout() {
        return R.layout.activity_coupon_detail;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    private void initChart() {
        List<PointValue> values = new ArrayList<PointValue>();
        List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

        for (int i = 0; i < 6; i++) {
            values.add(new PointValue(i, new Random().nextInt(6)));
            mAxisValues.add(new AxisValue(i).setLabel(axisX[i])); //为每个对应的i设置相应的label(显示在X轴)
        }

        Line line = new Line(values).setColor(ContextCompat.getColor(this, R.color.theme_color)).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);
        axisX.setTextColor(ContextCompat.getColor(this, R.color.color_black));
        axisX.setMaxLabelChars(6);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();//Y轴
        axisY.setMaxLabelChars(1); //默认是3,只能看最后三个数字
        data.setAxisYLeft(axisY);
        chartView.setZoomEnabled(false);
        chartView.setInteractive(true);
        chartView.setZoomType(ZoomType.HORIZONTAL);
        chartView.setLineChartData(data);
    }

    @Override
    public void couponDetailResult(CouponListResult.ListBean result) {
        tvCouponCount.setText(result.getCouponNum());
        tvUseStartDate.setText(result.getEffectiveDate());
        tvUseDate.setText(result.getEffectiveDate()+"~"+result.getExpireDate());
        tvUserRequire.setText("消费满"+ result.getFullAmt()+"元可用");
        tvHaveUse.setText(result.getUseNum() +"张");
        tvHaveShare.setText(result.getShareNum()+"次");
    }

    @Override
    public void couponListResult(List<CouponListResult.ListBean> result) {

    }

    @Override
    public void deleteCouponSuccessResult(String result) {

    }

    @Override
    public void addCouponSuccessResult(String result) {

    }

    @Override
    public void couponFailedResult(String errorMsg) {

    }

    @Override
    public void setPresenter(CouponPresenter presenter) {
        if (presenter == null) {
            couponPresenter = new CouponPresenter();
            couponPresenter.attachView(this);
        }
    }
}

