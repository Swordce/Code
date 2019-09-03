package com.cxmedia.goods.ui.server.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.MVP.model.MessageDetailModel;
import com.cxmedia.goods.MVP.model.MessageModel;
import com.cxmedia.goods.MVP.presenter.MessagePresenter;
import com.cxmedia.goods.MVP.view.IMessageView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.ServerMsgAdapter;
import com.cxmedia.goods.ui.base.BaseFragment;
import com.cxmedia.goods.ui.base.BaseMvpFragment;
import com.cxmedia.goods.ui.server.activity.ServerMsgDetailActivity;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ServerFragment extends BaseMvpFragment<MessagePresenter> implements IMessageView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_server)
    RecyclerView rvServer;

    private ServerMsgAdapter msgAdapter;
    private List<MessageModel.RowsBean> mList;

    private MessagePresenter msgPresenter;

    private int pageNum = 0;
    private int limit = 20;


    @Override
    public void initView() {
        mList = new ArrayList<>();
        rvServer.setLayoutManager(new LinearLayoutManager(getActivity()));
        msgAdapter = new ServerMsgAdapter(mList);
        msgAdapter.setOnItemClickListener(this);
        rvServer.setAdapter(msgAdapter);
    }

    @Override
    public void getData() {
        TreeMap<String,String> map = RequestUtils.messageInfoList(pageNum+"",limit+"");
        msgPresenter.doGetMessageList(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_server;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(),ServerMsgDetailActivity.class);
        intent.putExtra("msgId",mList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void messageInfoError(String error) {
        ToastUtils.showShortToast(getActivity(),error);
    }

    @Override
    public void messageListInfo(List<MessageModel.RowsBean> result) {
        mList.clear();
        mList.addAll(result);
        msgAdapter.setNewData(mList);
    }

    @Override
    public void messageDetailInfo(MessageDetailModel.DataBean result) {

    }

    @Override
    public void setPresenter(MessagePresenter presenter) {
        if(presenter == null) {
            this.msgPresenter = new MessagePresenter();
            this.msgPresenter.attachView(this);
        }
    }
}
