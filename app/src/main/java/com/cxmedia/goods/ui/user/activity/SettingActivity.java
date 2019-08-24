package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        String name = SharedPreferencesUtil.getInstance().getString("name");
        if(!TextUtils.isEmpty(name)) {
            tvLogin.setText("退出登录");
        }else {
            tvLogin.setText("登录");
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }


    @OnClick({R.id.ll_safe_setting,R.id.ll_face_setting,R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_safe_setting:
                Intent intent = new Intent(this,SafeSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_face_setting:
                Intent intent1 = new Intent(this,LifeSettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_login:
                if(tvLogin.getText().toString().equals("登录")) {
                    Intent loginIntent = new Intent(this,LoginActivity.class);
                    startActivity(loginIntent);
                }else {
                    tvLogin.setText("登录");
                }

                break;
        }
    }
}
