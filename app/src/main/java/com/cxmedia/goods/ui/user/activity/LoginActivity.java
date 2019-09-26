package com.cxmedia.goods.ui.user.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.Jpush.JPushService;
import com.cxmedia.goods.MVP.model.LoginResult;
import com.cxmedia.goods.MVP.presenter.LoginPresenter;
import com.cxmedia.goods.MVP.view.ILoginView;
import com.cxmedia.goods.MainActivity;
import com.cxmedia.goods.R;
import com.cxmedia.goods.cache.MchtCache;
import com.cxmedia.goods.common.Contents;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.SharedPreferencesUtil;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements ILoginView, View.OnClickListener {

    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_login_type)
    TextView tvLoginType;
    @BindView(R.id.rl_login_account)
    LinearLayout rlLoginAccount;
    @BindView(R.id.rl_login_face)
    LinearLayout rlLoginFace;
    @BindView(R.id.v_account_line)
    View vLine;
    @BindView(R.id.btn_login)
    ImageView btnLogin;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    private boolean isFace = false;
    private BottomSheetDialog bsDialog;
    private LoginPresenter loginPresenter;

    @Override
    public void initView() {
        StatusBarUtil.setTranslucent(this);
        MchtCache.pushCache();
    }

    @Override
    public void getData() {
        String name = SharedPreferencesUtil.getInstance().getString("name");
        if(!TextUtils.isEmpty(name)) {
            llLogin.setVisibility(View.GONE);
            vLine.setVisibility(View.GONE);
            tvName.setText(name);
            tvLoginType.setText("切换登录方式");
        }else {
            llLogin.setVisibility(View.VISIBLE);
            vLine.setVisibility(View.VISIBLE);
            tvName.setText("尊敬的用户，您好");
            tvLoginType.setText("注册开通");
        }

    }


    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.tv_forget_pwd, R.id.btn_login,R.id.tv_login_type,R.id.rl_login_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_login_face:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.CAMERA, Permission.READ_PHONE_STATE, Permission.ACCESS_FINE_LOCATION,Permission.ACCESS_COARSE_LOCATION)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                Intent loginIntent = new Intent(LoginActivity.this, FacePreviewActivity.class);
                                loginIntent.putExtra("faceType", Contents.START_FACE_LOGIN);
                                loginIntent.putExtra("registrationId",JPushInterface.getRegistrationID(LoginActivity.this));
                                startActivity(loginIntent);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                Toast.makeText(LoginActivity.this, "请设置相机权限", Toast.LENGTH_SHORT).show();
                            }
                        }).start();

                break;
            case R.id.tv_forget_pwd:
                Intent pwdForget = new Intent(this,ForgetPwdActivity.class);
                startActivity(pwdForget);
                break;
            case R.id.btn_login:
                String loginName = etLoginName.getText().toString();
                String loginPassword = etLoginPassword.getText().toString();
                if(!TextUtils.isEmpty(loginName)) {
                    if(!TextUtils.isEmpty(loginPassword)) {
                        Map<String,String> map = RequestUtils.loginStr(loginName,loginPassword,JPushInterface.getRegistrationID(this));
                        loginPresenter.doLogin(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
                    }else {
                        ToastUtils.showShortToast(this,"登录密码不能为空");
                    }
                }else {
                    ToastUtils.showShortToast(this,"登录账号不能为空");
                }
                break;
            case R.id.tv_login_type:
                String text = tvLoginType.getText().toString();
                if("注册开通".equals(text)) {

                }else {
                    if(!isFace) {
                        isFace = true;
                        rlLoginFace.setVisibility(View.VISIBLE);
                        rlLoginAccount.setVisibility(View.INVISIBLE);
                        btnLogin.setVisibility(View.INVISIBLE);
                    }else {
                        bsDialog = new BottomSheetDialog(this);
                        View view1 = getLayoutInflater().inflate(R.layout.layout_login,null);
                        TextView tvCommonPwd = view1.findViewById(R.id.tv_common_pwd);
                        TextView tvCancel = view1.findViewById(R.id.tv_cancel);
                        tvCommonPwd.setOnClickListener(this);
                        tvCancel.setOnClickListener(this);
                        bsDialog.setContentView(view1);
                        bsDialog.show();
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_common_pwd:
                isFace = false;
                rlLoginFace.setVisibility(View.INVISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
                rlLoginAccount.setVisibility(View.VISIBLE);
                bsDialog.dismiss();
                break;
            case R.id.tv_cancel:
                bsDialog.dismiss();
                break;
        }
    }

    @Override
    public void loginSuccessResult(LoginResult result) {
        if(result.getIsFirst() != 0) {
            Intent intent = new Intent(this,EditPasswordActivity.class);
            startActivity(intent);
        }else {
            ToastUtils.showShortToast(this,"登录成功");
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @Override
    public void loginFailedResult(String errorMsg) {
        ToastUtils.showShortToast(this,errorMsg);
    }

    @Override
    public void editPasswordResult(String result) {

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
