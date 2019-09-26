package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.presenter.LoginPresenter;
import com.cxmedia.goods.MVP.view.ILoginView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseMvpActivity<LoginPresenter> implements ILoginView,View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private LoginPresenter loginPresenter;
    private String empNo;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        empNo = (String) Cache.get("empNo");
        if(!TextUtils.isEmpty(empNo)) {
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
                if("退出登录".equals(tvLogin.getText().toString())) {
                    TreeMap<String,String> map = RequestUtils.loginOut(empNo);
                    loginPresenter.doLoginOut(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                }
                break;
        }
    }

    @Override
    public void loginSuccessResult(LoginResult result) {

    }

    @Override
    public void loginFailedResult(String errorMsg) {

    }

    @Override
    public void editPasswordResult(String result) {

    }

    @Override
    public void loginOutSuccess(String result) {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        if(presenter == null) {
            loginPresenter = new LoginPresenter();
            loginPresenter.attachView(this);
        }
    }
}
