package com.cxmedia.goods.ui.user.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseFragment;
import com.cxmedia.goods.ui.user.activity.CloseAccountActivity;
import com.cxmedia.goods.ui.user.activity.DeveiceInfoActivity;
import com.cxmedia.goods.ui.user.activity.SettingActivity;
import com.cxmedia.goods.ui.user.activity.UserCardActivity;
import com.cxmedia.goods.utils.Cache;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_no)
    TextView tvUserNo;

    @Override
    public void initView() {
        if(Cache.count() != 0) {
            tvUserName.setText(Cache.get("empName").toString());
            tvUserId.setText(Cache.get("id").toString());
            tvUserNo.setText(Cache.get("empNo").toString());
        }

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_user;
    }



    @OnClick({R.id.ll_user_card, R.id.ll_device,R.id.ll_setting,R.id.ll_close_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_card:
                Intent cardIntent = new Intent(getActivity(),UserCardActivity.class);
                startActivity(cardIntent);
                break;
            case R.id.ll_device:
                Intent deviceIntent = new Intent(getActivity(),DeveiceInfoActivity.class);
                startActivity(deviceIntent);
                break;
            case R.id.ll_setting:
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_close_account:
                Intent closeAccount = new Intent(getActivity(),CloseAccountActivity.class);
                startActivity(closeAccount);
                break;
        }
    }
}
