package com.cxmedia.goods.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxmedia.goods.R;

import java.util.List;

public class CouponTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> mData;
    private Context context;
    List<String> title;
    public CouponTabAdapter(Context context, FragmentManager fm, List<Fragment> mData, List<String> title) {
        super(fm);
        this.context = context;
        this.mData = mData;
        this.title = title;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_album_title);
        tv.setText(title.get(position));
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
