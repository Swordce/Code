package com.cxmedia.goods.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.cxmedia.goods.R;

import java.util.List;

public class TabVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> mData;
    private Context context;

    public TabVpAdapter(Context context, FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.context = context;
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public View getCustomView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_tab_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.tab_iv);
        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
        switch (position) {
            case 0:
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_home_tab));
                tv.setText("首页");
                break;
            case 1:
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_msg_tab));
                tv.setText("服务中心");
                break;
            case 2:
                iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_my_tab));
                tv.setText("我");
                break;
            default:
                tv.setTextColor(ContextCompat.getColor(context, R.color.color_white));
                break;
        }
        return view;
    }

}
