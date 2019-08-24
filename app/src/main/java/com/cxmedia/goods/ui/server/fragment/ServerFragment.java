package com.cxmedia.goods.ui.server.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.ServerMsgAdapter;
import com.cxmedia.goods.ui.base.BaseFragment;
import com.cxmedia.goods.ui.server.activity.ServerMsgDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ServerFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_server)
    RecyclerView rvServer;

    private ServerMsgAdapter msgAdapter;
    private List<String> mList;

    @Override
    public void initView() {
        mList = new ArrayList<>();
        mList.add("i");
        rvServer.setLayoutManager(new LinearLayoutManager(getActivity()));
        msgAdapter = new ServerMsgAdapter(mList);
        msgAdapter.setOnItemClickListener(this);
        rvServer.setAdapter(msgAdapter);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_server;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(),ServerMsgDetailActivity.class);
        startActivity(intent);
    }
}
