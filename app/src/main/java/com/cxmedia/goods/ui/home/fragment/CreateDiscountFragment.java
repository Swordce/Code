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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.CouponListResult;
import com.cxmedia.goods.MVP.presenter.CouponPresenter;
import com.cxmedia.goods.MVP.view.ICouponView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CommonUtils;
import com.cxmedia.goods.utils.GlideApp;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.Glide4Engine;
import com.google.gson.Gson;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

import static android.app.Activity.RESULT_OK;

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
    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.iv_detail)
    ImageView ivDetail;

    private BottomSheetDialog useDialog;
    private CouponPresenter couponPresenter;
    private String thumbnail;
    private String poster;
    private String mchtNo;
    private String empNo;
    private int imageType = 1;//1---缩略图，2---详情
    private String expirdate;
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

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_create_discount;
    }


    @OnClick({R.id.ll_use_day, R.id.ll_use_time,R.id.iv_thumb,R.id.iv_detail})
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
                                String h = "";
                                String m = "";
                                if (hours == 0) {
                                    h = "00";
                                } else {
                                    h = hours + "";
                                }
                                if (minutes == 0) {
                                    m = "00";
                                } else {
                                    m = minutes + "";
                                }
                                String startTime = firstDate.get(Calendar.YEAR) + "-" + (firstDate.get(Calendar.MONTH) + 1) + "-" + firstDate.get(Calendar.DAY_OF_MONTH) + " " + h + ":" + m + ":00";
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
                                String startTime = "";
                                String endTime = "";
                                String h = "";
                                String m = "";
                                if (hours == 0) {
                                    h = "00";
                                } else {
                                    h = hours + "";
                                }
                                if (minutes == 0) {
                                    m = "00";
                                } else {
                                    m = minutes + "";
                                }
                                startTime = firstDate.get(Calendar.YEAR) + "-" + (firstDate.get(Calendar.MONTH) + 1) + "-" + firstDate.get(Calendar.DAY_OF_MONTH) + " " + h + ":" + m + ":00";
                                if (secondDate != null) {
                                    endTime = secondDate.get(Calendar.YEAR) + "-" + (secondDate.get(Calendar.MONTH) + 1) + "-" + secondDate.get(Calendar.DAY_OF_MONTH) + " " + h + ":" + m + ":00";
                                }
                                if (CommonUtils.compareDate(startTime, CommonUtils.getDay()) < 1) {
                                    if (!TextUtils.isEmpty(endTime)) {
                                        expirdate = endTime;
                                        tvTime.setText(startTime + " - " + endTime);
                                    } else {
                                        expirdate = startTime;
                                        tvTime.setText(CommonUtils.getDay() + " - " + startTime);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "选择的日期不能小于当前日期", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show(getFragmentManager(), "TAG_SLYCALENDAR");
                break;
            case R.id.iv_thumb:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                imageType = 1;
                                selectImage(1);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                            }
                        }).start();
                break;
            case R.id.iv_detail:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                imageType = 2;
                                selectImage(2);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                            }
                        }).start();
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
        ToastUtils.showShortToast(getActivity(),result);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void couponFailedResult(String errorMsg) {
        Log.e(getClass().getSimpleName(),"result error = " + errorMsg);
        ToastUtils.showShortToast(getActivity(),errorMsg);
    }

    @Override
    public void uploadFileResult(CommonResult result,int type) {
        if (type == 1) {
            thumbnail = result.getPath();
            GlideApp.with(this).load(thumbnail).into(ivThumb);

        } else {
            poster = result.getPath();
            GlideApp.with(this).load(poster).into(ivDetail);
        }
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
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<String> pathList = Matisse.obtainPathResult(data);
            if (pathList.size() > 0) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(pathList.get(0)));
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", System.currentTimeMillis() + ".png", requestFile);
                couponPresenter.uploadFile(body, imageType);
            }
        }
    }

    private void selectImage(final int type) {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(1)
                .originalEnable(false)
                .maxOriginalSize(10)
                .imageEngine(new Glide4Engine())
                .forResult(1);
    }

    public void uploadCoupon() {
        String dicountMoney = etDiscountMoney.getText().toString();
        String discountCount = etDiscountCount.getText().toString();
        String userMethod = etUseMethod.getText().toString();
        String useDay = tvUseDay.getText().toString();
        Map<String, String> map = RequestUtils.addCouponStr(mchtNo, "1",
                "0", dicountMoney, discountCount, userMethod,
                useDay, expirdate, thumbnail, poster, empNo);

        RequestBody body = RetrofitFactory.getRequestBody(new Gson().toJson(map));
        couponPresenter.addCoupon(body);
    }
}
