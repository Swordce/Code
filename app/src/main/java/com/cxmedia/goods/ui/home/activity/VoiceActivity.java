package com.cxmedia.goods.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.VoiceAdapter;
import com.cxmedia.goods.ui.base.BaseActivity;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.GlideApp;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBarWrapper;
import com.jaeger.library.StatusBarUtil;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoiceActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    RippleBackground rippleBackground;
    @BindView(R.id.rv_bill)
    RecyclerView rvBill;
    @BindView(R.id.mySeekBar)
    VerticalSeekBar voiceSeekbar;
    @BindView(R.id.seekbar_wrapper)
    VerticalSeekBarWrapper seekBarWrapper;
    @BindView(R.id.iv_voice)
    ImageView llVoice;
    @BindView(R.id.ll_voice_set)
    LinearLayout llVoiceSet;

    private VoiceAdapter voiceAdapter;
    private List<String> mList;

    @Override
    public void initView() {
        StatusBarUtil.setTranslucent(this,50);
        toolbar.setNavigationOnClickListener(this);
        rippleBackground.startRippleAnimation();
        mList = new ArrayList<>();
        mList.add("i");
        voiceAdapter = new VoiceAdapter(mList);
        voiceAdapter.setOnItemClickListener(this);
        rvBill.setLayoutManager(new LinearLayoutManager(this));
        rvBill.setAdapter(voiceAdapter);
        GlideApp.with(this).asDrawable().load(R.drawable.ic_voice_bg).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(llVoice);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_voice;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rippleBackground.stopRippleAnimation();
    }

    @OnClick({R.id.iv_voice_set,R.id.iv_set,R.id.fm_voice})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.fm_voice:
                hideSet();
                break;
            case R.id.iv_voice_set:
                if(seekBarWrapper.getVisibility() == View.GONE) {
                    seekBarWrapper.setVisibility(View.VISIBLE);
                }else {
                    seekBarWrapper.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_set:
                if(llVoiceSet.getVisibility() == View.VISIBLE) {
                    llVoiceSet.setVisibility(View.GONE);
                }else {
                    llVoiceSet.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private void hideSet() {
        if(seekBarWrapper.getVisibility() == View.VISIBLE) {
            seekBarWrapper.setVisibility(View.GONE);
        }
        if(llVoiceSet.getVisibility() == View.VISIBLE) {
            llVoiceSet.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        hideSet();
        Intent intent = new Intent(this,ElevsMessageDetailActivity.class);
        startActivity(intent);
    }
}
