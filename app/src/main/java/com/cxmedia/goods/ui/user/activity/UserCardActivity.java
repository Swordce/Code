package com.cxmedia.goods.ui.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.UserCardAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCardActivity extends BaseActivity {


    @BindView(R.id.rv_card)
    RecyclerView rvCard;

    private List<String> mList;
    private UserCardAdapter cardAdapter;

    @Override
    public void initView() {
        mList = new ArrayList<>();
        mList.add("i");
        cardAdapter = new UserCardAdapter(mList);
        rvCard.setLayoutManager(new LinearLayoutManager(this));
        rvCard.setAdapter(cardAdapter);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_card;
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity();
    }
}
