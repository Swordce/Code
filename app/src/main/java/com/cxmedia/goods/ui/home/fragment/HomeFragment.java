package com.cxmedia.goods.ui.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.CommonResult;
import com.cxmedia.goods.MVP.model.HomeOrderInfo;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.model.OrderCategroyResult;
import com.cxmedia.goods.MVP.model.OrderDetailResult;
import com.cxmedia.goods.MVP.model.OrderInfoListResult;
import com.cxmedia.goods.MVP.presenter.OrderPresenter;
import com.cxmedia.goods.MVP.view.IOrderView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.ui.home.activity.BillActivity;
import com.cxmedia.goods.ui.home.activity.CouponActivity;
import com.cxmedia.goods.ui.home.activity.CustomerManagerActivity;
import com.cxmedia.goods.ui.home.activity.ElevsMessageActivity;
import com.cxmedia.goods.ui.home.activity.ElevsMessageDetailActivity;
import com.cxmedia.goods.ui.home.activity.ShopIntroduceActivity;
import com.cxmedia.goods.ui.home.activity.ShopManagerActivity;
import com.cxmedia.goods.ui.home.activity.VoiceActivity;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.CalendarUtil;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseMvpFragment<OrderPresenter> implements IOrderView {
    @BindView(R.id.tv_today_deal_count)
    TextView tvTodayDealCount;
    @BindView(R.id.tv_today_deal_money)
    TextView tvTodayDealMoney;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_refund_count)
    TextView tvRefundCount;
    @BindView(R.id.tv_refund_money)
    TextView tvRefundMoney;
    @BindView(R.id.tv_coupon_count)
    TextView tvCouponCount;
    @BindView(R.id.tv_coupon_money)
    TextView tvCouponMoney;
    @BindView(R.id.tv_elve_money)
    TextView tvElveMoney;
    @BindView(R.id.tv_elve_type)
    TextView tvElveType;
    @BindView(R.id.tv_elve_date)
    TextView tvElveDate;
    Unbinder unbinder;

    private OrderPresenter orderPresenter;
    private String empNo;
    private String mchtNo;
    private String orderNo;

    @Override
    public void initView() {
        empNo = (String) Cache.get("empNo");
        mchtNo = (String) Cache.get("mchtNo");
    }

    @Override
    public void getData() {
        TreeMap<String, String> map = RequestUtils.homeOrderInfo(empNo, mchtNo);
        orderPresenter.doGetHomeOrderInfo(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
        TreeMap<String,String> map1 = RequestUtils.orderInfoList(mchtNo,0,1,0,"","");
        orderPresenter.doGetOrderInfoList(RetrofitFactory.getRequestBody(new Gson().toJson(map1)));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.ll_customer_manager, R.id.ll_voice, R.id.ll_jump_bill, R.id.ll_shop_manager, R.id.ll_shop_marketing, R.id.ll_introduce, R.id.ll_elevs_msg, R.id.ll_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_voice:
                Intent voiceIntent = new Intent(getActivity(), VoiceActivity.class);
                startActivity(voiceIntent);
                break;
            case R.id.ll_customer_manager:
                Intent intent = new Intent(getActivity(), CustomerManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_jump_bill:
                Intent billIntent = new Intent(getActivity(), BillActivity.class);
                startActivity(billIntent);
                break;
            case R.id.ll_shop_manager:
                Intent shop = new Intent(getActivity(), ShopManagerActivity.class);
                startActivity(shop);
                break;
            case R.id.ll_shop_marketing:
                Intent shopMarketing = new Intent(getActivity(), CouponActivity.class);
                startActivity(shopMarketing);
                break;
            case R.id.ll_introduce:
                Intent shopIntroduce = new Intent(getActivity(), ShopIntroduceActivity.class);
                startActivity(shopIntroduce);
                break;
            case R.id.ll_elevs_msg:
                Intent elevsMsg = new Intent(getActivity(), ElevsMessageDetailActivity.class);
                elevsMsg.putExtra("orderNo",orderNo);
                startActivity(elevsMsg);
                break;
            case R.id.ll_face:
                Intent intent1 = new Intent(getActivity(), FacePreviewActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void orderInfoError(String errorMsg) {
        ToastUtils.showShortToast(getActivity(), errorMsg);
    }

    @Override
    public void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result) {
        tvTodayDealCount.setText(result.getOrderCount()+"");
        tvRefundCount.setText(result.getOrderCount() - result.getRealPayCount() +"");
        tvCouponCount.setText(result.getCouponCount()+"");
    }

    @Override
    public void orderInfoListResult(List<OrderInfoListResult.RowsBean> result) {
        for (int i = 0; i <result.size() ; i++) {
            orderNo = result.get(i).getOrderNo();
            tvElveMoney.setText(result.get(i).getOrderAmt()+"");
            String type = result.get(i).getPayChannelType();
            if(type.equals("10")) {
                tvElveType.setText("微信");
            }else {
                tvElveType.setText("支付宝");
            }
            CalendarUtil util = new CalendarUtil();
            String date = util.getDateMonthString(result.get(i).getOrderTime());
            tvElveDate.setText(date);
        }
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
        if (presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }

}
