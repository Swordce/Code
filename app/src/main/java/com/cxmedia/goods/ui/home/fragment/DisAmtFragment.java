package com.cxmedia.goods.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import butterknife.BindView;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class DisAmtFragment extends BaseMvpFragment<CouponPresenter> implements ICouponView {

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

    public static DisAmtFragment getInstance(String couponId) {
        DisAmtFragment fullAmtFragment = new DisAmtFragment();
        Bundle bundle = new Bundle();
        bundle.putString("couponId",couponId);
        fullAmtFragment.setArguments(bundle);
        return fullAmtFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            couponId = bundle.getString("couponId");
        }
    }


    @Override
    public void initView() {
        initChart();
    }

    private void initChart() {
        List<PointValue> values = new ArrayList<PointValue>();
        List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

        for (int i = 0; i < 6; i++) {
            values.add(new PointValue(i, new Random().nextInt(6)));
            mAxisValues.add(new AxisValue(i).setLabel(axisX[i])); //为每个对应的i设置相应的label(显示在X轴)
        }

        Line line = new Line(values).setColor(ContextCompat.getColor(getActivity(), R.color.theme_color)).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);
        axisX.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_black));
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
    public void getData() {
        TreeMap<String, String> map = RequestUtils.couponDetaiStr(couponId, (String) Cache.get("mchtNo"));
        couponPresenter.getCouponDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_disamt_coupon;
    }

    @Override
    public void couponDetailResult(CouponListResult.ListBean result) {
        CalendarUtil util = new CalendarUtil();
        tvCouponCount.setText(result.getCouponNum()+"");
        if(result.getDiscount() == null || TextUtils.isEmpty(result.getDiscount())) {
            tvCouponMoney.setText("0");
        }else {
            tvCouponMoney.setText(result.getDiscount());
        }
        tvUseStartDate.setText(util.getDateString(result.getEffectiveDate()));
        tvUseDate.setText(util.getDateString(result.getEffectiveDate())+"~"+util.getDateString(result.getExpireDate()));
        tvUserRequire.setText("消费满"+ result.getSubtractionAmt()+"元可用");
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
        ToastUtils.showShortToast(getActivity(),errorMsg);
    }

    @Override
    public void uploadFileResult(CommonResult result,int type) {

    }

    @Override
    public void setPresenter(CouponPresenter presenter) {
        if(presenter == null) {
            couponPresenter = new CouponPresenter();
            couponPresenter.attachView(this);
        }
    }
}
