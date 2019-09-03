package com.cxmedia.goods.ui.server.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.MVP.model.MessageDetailModel;
import com.cxmedia.goods.MVP.model.MessageModel;
import com.cxmedia.goods.MVP.presenter.MessagePresenter;
import com.cxmedia.goods.MVP.view.IMessageView;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.ui.base.BaseMvpActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.RequestUtils;
import com.cxmedia.goods.utils.RetrofitFactory;
import com.cxmedia.goods.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

public class ServerMsgDetailActivity extends BaseMvpActivity<MessagePresenter> implements IMessageView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private int msgId;
    private MessagePresenter msgPresenter;

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void getData() {
        Intent intent = getIntent();
        msgId = intent.getIntExtra("msgId",-1);
        TreeMap<String,String> map = RequestUtils.messageDetail(msgId+"");
        msgPresenter.doMessageDetail(RetrofitFactory.getRequestBody(new Gson().toJson(map)));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_server_msg_detail;
    }

    @Override
    public void onClick(View view) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void messageInfoError(String error) {
        ToastUtils.showShortToast(this,error);
    }

    @Override
    public void messageListInfo(List<MessageModel.RowsBean> result) {

    }

    @Override
    public void messageDetailInfo(MessageDetailModel.DataBean result) {
        tvTitle.setText(result.getName());
        tvContent.setText(result.getContent());
    }

    @Override
    public void setPresenter(MessagePresenter presenter) {
        if(presenter == null) {
            msgPresenter = new MessagePresenter();
            msgPresenter.attachView(this);
        }
    }
}
