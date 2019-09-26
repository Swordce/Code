package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.presenter.LoginPresenter;
import com.cxmedia.goods.MVP.view.ILoginView;
import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class EditPasswordActivity extends BaseMvpActivity<LoginPresenter> implements TextWatcher, ILoginView {

    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.tv_confirm_edit)
    TextView tvConfirmEdit;

    private LoginPresenter loginPresenter;

    @Override
    public void initView() {
        etNewPassword.addTextChangedListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_edit_password;
    }

    @OnClick({R.id.iv_close,R.id.tv_confirm_edit})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_confirm_edit:
                String strNewPassword = etNewPassword.getText().toString();
                if(strNewPassword.length() > 5 && strNewPassword.length() < 21) {
                    TreeMap<String,String> map = RequestUtils.editPasswordStr((String)Cache.get("empNo"),strNewPassword);
                    loginPresenter.doEditPassword(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String value = s.toString();
        if(value.length() > 5 && value.length() < 21) {
            tvConfirmEdit.setBackgroundColor(ContextCompat.getColor(this,R.color.theme_color));
        }else {
            tvConfirmEdit.setBackgroundColor(ContextCompat.getColor(this,R.color.color_b2b2b2));
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
        ToastUtils.showShortToast(this,result);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void loginOutSuccess(String result) {

    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        if(presenter == null) {
            loginPresenter = new LoginPresenter();
            loginPresenter.attachView(this);
        }
    }
}
