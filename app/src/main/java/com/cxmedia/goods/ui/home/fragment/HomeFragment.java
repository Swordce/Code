package com.cxmedia.goods.ui.home.fragment;

import android.content.Intent;
import android.graphics.Camera;
import android.view.View;
import android.widget.TextView;

import com.cxmedia.goods.R;
import com.cxmedia.goods.ui.adapter.ShopManagerAdapter;
import com.cxmedia.goods.ui.base.BaseFragment;
import com.cxmedia.goods.ui.home.FacePreviewActivity;
import com.cxmedia.goods.ui.home.activity.BillActivity;
import com.cxmedia.goods.ui.home.activity.CouponActivity;
import com.cxmedia.goods.ui.home.activity.CustomerManagerActivity;
import com.cxmedia.goods.ui.home.activity.ElevsMessageActivity;
import com.cxmedia.goods.ui.home.activity.ShopIntroduceActivity;
import com.cxmedia.goods.ui.home.activity.ShopManagerActivity;
import com.cxmedia.goods.ui.home.activity.VoiceActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_today_deal_count)
    TextView tvTodayDealCount;
    @BindView(R.id.tv_today_deal_money)
    TextView tvTodayDealMoney;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.ll_customer_manager,R.id.ll_voice, R.id.ll_jump_bill,R.id.ll_shop_manager, R.id.ll_shop_marketing, R.id.ll_introduce,R.id.ll_elevs_msg,R.id.ll_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_voice:
                Intent voiceIntent = new Intent(getActivity(), VoiceActivity.class);
                startActivity(voiceIntent);
                break;
            case R.id.ll_customer_manager:
                Intent intent = new Intent(getActivity(),CustomerManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_jump_bill:
                Intent billIntent = new Intent(getActivity(), BillActivity.class);
                startActivity(billIntent);
                break;
            case R.id.ll_shop_manager:
                Intent shop = new Intent(getActivity(), ShopManagerActivity.class);
                startActivity(shop);
                break;
            case R.id.ll_shop_marketing:
                Intent shopMarketing =  new Intent(getActivity(),CouponActivity.class);
                startActivity(shopMarketing);
                break;
            case R.id.ll_introduce:
                Intent shopIntroduce = new Intent(getActivity(),ShopIntroduceActivity.class);
                startActivity(shopIntroduce);
                break;
            case R.id.ll_elevs_msg:
                Intent elevsMsg = new Intent(getActivity(),ElevsMessageActivity.class);
                startActivity(elevsMsg);
                break;
            case R.id.ll_face:
                Intent intent1 = new Intent(getActivity(), FacePreviewActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
