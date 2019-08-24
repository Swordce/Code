package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cxmedia.goods.ui.home.FacePreviewActivity.TYPE;

public class OpenFaceVerificationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_open_face;
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        Intent intent = new Intent(this, FacePreviewActivity.class);
        intent.putExtra(TYPE,2);
        startActivity(intent);
    }
}
