package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.common.EventMsg;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.ui.user.activity.PhoneCodeVerificationActivity;
import com.cxmedia.goods.utils.AppManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

public class ElevsMessageDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_refund)
    TextView tvRefund;

    private BottomSheetDialog refundDialog;

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

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EventMsg msg) {
        switch (msg.getMsgType()) {
            case 0:
                refundDialog = new BottomSheetDialog(this);
                View refundView = getLayoutInflater().inflate(R.layout.layout_refund, null);
                TextView tvForget = refundView.findViewById(R.id.tv_forget_pay_pwd);
                tvForget.setOnClickListener(this);
                refundDialog.setContentView(refundView);
                refundDialog.show();
                EventBus.getDefault().removeStickyEvent(msg);
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_elevs_msg_detail;
    }


    @OnClick({R.id.iv_close,R.id.tv_refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_refund:
                Intent intent = new Intent(this, FacePreviewActivity.class);
                intent.putExtra("refund",true);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pay_pwd:
                Intent intent = new Intent(this, PhoneCodeVerificationActivity.class);
                intent.putExtra("refund",true);
                startActivity(intent);
                refundDialog.dismiss();
                break;
        }
    }
}
