package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.widgets.camera.CameraView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeSettingActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_safe_setting;
    }


    @OnClick({R.id.iv_close, R.id.ll_edit_pwd, R.id.ll_edit_login_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.ll_edit_pwd:
                Intent intent = new Intent(this, FacePreviewActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_edit_login_pwd:
                Intent editPwdIntent = new Intent(this,EditPasswordActivity.class);
                startActivity(editPwdIntent);
                break;
        }
    }
}
