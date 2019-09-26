package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.common.EventMsg;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.SelectButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class LifeSettingActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.select_button_login)
    SelectButton sbLogin;
    @BindView(R.id.select_button_refund)
    SelectButton sbRefund;


    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(EventMsg msg) {
        if(msg.getMsgType() == Contents.OPEN_FACE_LOGIN) {
            sbLogin.setOffline(true);
        }else if(msg.getMsgType() == Contents.OPEN_FACE_REFUND) {
            sbRefund.setOffline(true);
        }
        EventBus.getDefault().removeStickyEvent(msg);
    }

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        sbLogin.setOffline(false);
        sbLogin.setOnButtonClickListener(new SelectButton.OnButtonClickListener() {
            @Override
            public void onClick(boolean click) {
                if(click) {
                    Intent intent = new Intent(LifeSettingActivity.this,BindPhoneActivity.class);
                    intent.putExtra("faceType", Contents.OPEN_FACE_LOGIN);
                    startActivity(intent);
                }
            }
        });
        sbRefund.setOffline(false);
        sbRefund.setOnButtonClickListener(new SelectButton.OnButtonClickListener() {
            @Override
            public void onClick(boolean click) {
                if(click) {
                    Intent intent = new Intent(LifeSettingActivity.this,BindPhoneActivity.class);
                    intent.putExtra("faceType", Contents.OPEN_FACE_REFUND);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_life_setting;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }
}
