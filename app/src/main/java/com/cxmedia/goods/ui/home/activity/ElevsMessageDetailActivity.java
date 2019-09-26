package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
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
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.common.EventMsg;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.ui.user.activity.PhoneCodeVerificationActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.cxmedia.goods.widgets.PayPsdInputView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ElevsMessageDetailActivity extends BaseMvpActivity<OrderPresenter> implements IOrderView, View.OnClickListener {

    @BindView(R.id.tv_refund)
    TextView tvRefund;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_mchtno)
    TextView tvMchtno;
    @BindView(R.id.tv_terminal_no)
    TextView tvTerminalNo;
    @BindView(R.id.tv_money)
    TextView tvMoney;


    private BottomSheetDialog refundDialog;
    private String orderNo;

    private OrderPresenter orderPresenter;
    private String orderId;
    private String orderMoney;
    private String orderTime;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(EventMsg msg) {
        switch (msg.getMsgType()) {
            case 0:
                refundDialog = new BottomSheetDialog(this);
                View refundView = getLayoutInflater().inflate(R.layout.layout_refund, null);
                TextView tvForget = refundView.findViewById(R.id.tv_forget_pay_pwd);
                PayPsdInputView inputView = refundView.findViewById(R.id.tv_refund_password);
                tvForget.setOnClickListener(this);
                refundDialog.setContentView(refundView);
                inputView.setComparePassword(new PayPsdInputView.onPasswordListener(){

                    @Override
                    public void onDifference(String oldPsd, String newPsd) {

                    }

                    @Override
                    public void onEqual(String psd) {

                    }

                    @Override
                    public void inputFinished(String inputPsd) {
                        String empNo = (String) Cache.get("empNo");
                        String mchtNo = (String) Cache.get("mchtNo");
                        TreeMap<String,String> map = RequestUtils.orderPasswordRefund(inputPsd,empNo,mchtNo,orderId,orderMoney,orderTime);
                        orderPresenter.doPasswordRefund(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                    }
                });
                refundDialog.show();
                EventBus.getDefault().removeStickyEvent(msg);
                break;
        }
    }

    @Override
    public void initView() {
        orderNo = getIntent().getStringExtra("orderNo");
    }

    @Override
    public void getData() {
        TreeMap<String, String> map = RequestUtils.orderDetail(orderNo);
        orderPresenter.doGetOrderDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_elevs_msg_detail;
    }


    @OnClick({R.id.iv_close, R.id.tv_refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_refund:
                Intent intent = new Intent(this, FacePreviewActivity.class);
                intent.putExtra("faceType", Contents.START_FACE_REFUND);
                intent.putExtra("orderNo",orderNo);
                intent.putExtra("ordAmt",orderMoney);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pay_pwd:
                Intent intent = new Intent(this, PhoneCodeVerificationActivity.class);
                intent.putExtra("refund", true);
                startActivity(intent);
                refundDialog.dismiss();
                break;
        }
    }

    @Override
    public void orderInfoError(String errorMsg) {
        ToastUtils.showShortToast(this,errorMsg);
    }

    @Override
    public void homeOrderInfoResult(HomeOrderInfo.IndexOrderDataBean result) {

    }

    @Override
    public void orderInfoListResult(List<OrderInfoListResult.RowsBean> result) {

    }

    @Override
    public void OrderSettleResult(OrderCategroyResult result) {

    }

    @Override
    public void orderDetailResult(OrderDetailResult.DataBean result) {
        orderId = result.getOrderNo();
        orderMoney = result.getRealAmt()+"";
        orderTime = result.getOrderTime();

        if(result.getPayChannelType().equals("10")) {
            tvPayType.setText("微信");
        }else {
            tvPayType.setText("支付宝");
        }
        tvMchtno.setText(result.getMemberId());
        tvOrderNo.setText(result.getOrderNo());
        tvOrderTime.setText(result.getOrderTime());
        tvMoney.setText("￥"+result.getRealAmt());
        tvTerminalNo.setText(result.getOutTransId());
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
        ToastUtils.showShortToast(this,result);
        if(refundDialog != null && refundDialog.isShowing()) {
            refundDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(OrderPresenter presenter) {
        if (presenter == null) {
            orderPresenter = new OrderPresenter();
            orderPresenter.attachView(this);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
