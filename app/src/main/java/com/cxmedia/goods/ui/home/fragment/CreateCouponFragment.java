package com.cxmedia.goods.ui.home.fragment;

import android.graphics.Point;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseFragment;
import com.cxmedia.goods.utils.CommonUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class CreateCouponFragment extends BaseFragment {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_use_day)
    TextView tvUseDay;
    private BottomSheetDialog useDialog;

    public static CreateCouponFragment newInstance() {
        return new CreateCouponFragment();
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_create_coupon;
    }


    @OnClick({R.id.ll_use_day, R.id.ll_use_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                                String endTime ="";
                                if(secondDate != null) {
                                    endTime = secondDate.get(Calendar.YEAR) + "-" + (secondDate.get(Calendar.MONTH) + 1) +"-" + secondDate.get(Calendar.DAY_OF_MONTH);
                                }
                                if(CommonUtils.compareDate(startTime,CommonUtils.getDay())  < 1) {
                                    if(!TextUtils.isEmpty(endTime)) {
                                        tvTime.setText(startTime + " - " + endTime);
                                    }else {
                                        tvTime.setText(CommonUtils.getDay() + " - " + startTime);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "选择的日期不能小于当前日期", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show(getFragmentManager(), "TAG_SLYCALENDAR");
                break;
        }
    }
}
