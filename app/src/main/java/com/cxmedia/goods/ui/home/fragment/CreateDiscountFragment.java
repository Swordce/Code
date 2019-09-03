package com.cxmedia.goods.ui.home.fragment;

import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.utils.CommonUtils;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class CreateDiscountFragment extends BaseMvpFragment<CouponPresenter> implements ICouponView {

    @BindView(R.id.tv_use_day)
    TextView tvUseDay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_create_coupon)
    TextView tvCreateCoupon;
    @BindView(R.id.et_discount_money)
    EditText etDiscountMoney;
    @BindView(R.id.et_discount_count)
    EditText etDiscountCount;
    @BindView(R.id.et_use_method)
    EditText etUseMethod;
    @BindView(R.id.et_total_money)
    EditText etTotalMoney;

    private BottomSheetDialog useDialog;
    private CouponPresenter couponPresenter;

    public static CreateDiscountFragment newInstance() {
        return new CreateDiscountFragment();
    }

    @Override
    public void registerForContextMenu(View view) {
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_create_discount;
    }


    public void create() {
        String dicountMoney = etDiscountMoney.getText().toString();
        String discountCount = etDiscountCount.getText().toString();
        String totalMoney = etTotalMoney.getText().toString();
        Map<String,String> map = RequestUtils.addCouponStr(Contents.TEST_MCHTNO,"02",
                "100","100","1500","111",
                "2019-08-19","2019-10-1",Contents.TEST_EMPNO);

        RequestBody body = RetrofitFactory.getRequestBody(new Gson().toJson(map));
        couponPresenter.addCoupon(body);
    }

    @OnClick({R.id.ll_use_day, R.id.ll_use_time,R.id.tv_create_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_coupon:
                create();
                break;
            case R.id.ll_use_day:
                useDialog = new BottomSheetDialog(getActivity());
                View refundView = getLayoutInflater().inflate(R.layout.layout_use_dialog, null);
                final TextView tvCurrentDay = refundView.findViewById(R.id.tv_current_day);
                final TextView tvNextDay = refundView.findViewById(R.id.tv_next_day);
                tvCurrentDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        useDialog.dismiss();
                        tvUseDay.setText(tvCurrentDay.getText().toString());
                    }
                });

                tvNextDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        useDialog.dismiss();
                        tvUseDay.setText(tvNextDay.getText().toString());
                    }
                });
                useDialog.setContentView(refundView);
                useDialog.show();
                break;
            case R.id.ll_use_time:
                new SlyCalendarDialog()
                        .setSingle(false)
                        .setHeaderColor(ContextCompat.getColor(getActivity(), R.color.theme_color))
                        .setSelectedColor(ContextCompat.getColor(getActivity(), R.color.theme_color))
                        .setStartDate(Calendar.getInstance().getTime())
                        .setFirstMonday(true)
                        .setCallback(new SlyCalendarDialog.Callback() {
                            @Override
                            public void onCancelled() {

                            }

                            @Override
                            public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
                                String startTime = firstDate.get(Calendar.YEAR) + "-" + (firstDate.get(Calendar.MONTH) + 1) + "-" + firstDate.get(Calendar.DAY_OF_MONTH);
                                String endTime = "";
                                if (secondDate != null) {
                                    endTime = secondDate.get(Calendar.YEAR) + "-" + (secondDate.get(Calendar.MONTH) + 1) + "-" + secondDate.get(Calendar.DAY_OF_MONTH);
                                }
                                if (CommonUtils.compareDate(startTime, CommonUtils.getDay()) < 1) {
                                    if (!TextUtils.isEmpty(endTime)) {
                                        tvTime.setText(startTime + " - " + endTime);
                                    } else {
                                        tvTime.setText(CommonUtils.getDay() + " - " + startTime);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "选择的日期不能小于当前日期", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show(getFragmentManager(), "TAG_SLYCALENDAR");
                break;
        }
    }

    @Override
    public void couponDetailResult(CouponListResult.ListBean result) {

    }

    @Override
    public void couponListResult(List<CouponListResult.ListBean> result) {

    }

    @Override
    public void deleteCouponSuccessResult(String result) {

    }

    @Override
    public void addCouponSuccessResult(String result) {
        Log.e(getClass().getSimpleName(),"result = " + result);
    }

    @Override
    public void couponFailedResult(String errorMsg) {
        Log.e(getClass().getSimpleName(),"result error = " + errorMsg);
    }

    @Override
    public void setPresenter(CouponPresenter presenter) {
        if (presenter == null) {
            couponPresenter = new CouponPresenter();
            couponPresenter.attachView(this);
        }
    }
}
