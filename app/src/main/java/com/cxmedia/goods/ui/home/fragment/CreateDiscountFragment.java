package com.cxmedia.goods.ui.home.fragment;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.utils.Cache;
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
    @BindView(R.id.et_discount_money)
    EditText etDiscountMoney;
    @BindView(R.id.et_discount_count)
    EditText etDiscountCount;
    @BindView(R.id.et_use_method)
    EditText etUseMethod;

    private BottomSheetDialog useDialog;
    private CouponPresenter couponPresenter;
    private String thumbnail;
    private String poster;
    private String mchtNo;
    private String empNo;

    public static CreateDiscountFragment newInstance() {
        return new CreateDiscountFragment();
    }

    @Override
    public void registerForContextMenu(View view) {
    }

    @Override
    public void initView() {
        mchtNo = (String) Cache.get("mchtNo");
        empNo = (String)Cache.get("empNo");
        etUseMethod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                etUseMethod.setText(text +"元");
            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_create_discount;
    }


    @OnClick({R.id.ll_use_day, R.id.ll_use_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_use_day:
                new SlyCalendarDialog()
                        .setSingle(true)
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
                                tvUseDay.setText(startTime);
                            }
                        })
                        .show(getFragmentManager(), "TAG_SLYCALENDAR");
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
    public void uploadFileResult(CommonResult result,int type) {

    }


    @Override
    public void setPresenter(CouponPresenter presenter) {
        if (presenter == null) {
            couponPresenter = new CouponPresenter();
            couponPresenter.attachView(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadCoupon() {
        String dicountMoney = etDiscountMoney.getText().toString();
        String discountCount = etDiscountCount.getText().toString();
        String userMethod = etUseMethod.getText().toString();
        String useDay = tvUseDay.getText().toString();
        String useEndDay = tvTime.getText().toString();
        Map<String,String> map = RequestUtils.addCouponStr(mchtNo,"1",
                dicountMoney,discountCount,"0",userMethod,
                useDay,useEndDay,thumbnail,poster,empNo);

        RequestBody body = RetrofitFactory.getRequestBody(new Gson().toJson(map));
        couponPresenter.addCoupon(body);
    }
}
