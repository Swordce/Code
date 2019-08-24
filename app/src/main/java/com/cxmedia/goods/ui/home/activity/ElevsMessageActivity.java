package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.ElevsMsgAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ElevsMessageActivity extends BaseActivity implements View.OnClickListener,BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_elevs_msg)
    RecyclerView rvElevsMsg;
    private List<String> mList;
    private ElevsMsgAdapter elevsMsgAdapter;

    @Override
    public void initView() {
        mList = new ArrayList<>();
        mList.add("i");
        elevsMsgAdapter = new ElevsMsgAdapter(mList);
        elevsMsgAdapter.setOnItemClickListener(this);
        rvElevsMsg.setLayoutManager(new LinearLayoutManager(this));
        rvElevsMsg.setAdapter(elevsMsgAdapter);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_elevs_msg;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,ElevsMessageDetailActivity.class);
        startActivity(intent);
    }
}
