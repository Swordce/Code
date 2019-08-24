package com.cxmedia.goods.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapder extends FragmentPagerAdapter {
	private List<Fragment> list;
	private List<String> title;
	public MyPagerAdapder(FragmentManager fm, List<Fragment> list, List<String> title) {
		super(fm);
		this.list=list;
		this.title=title;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return title.get(position);
	}


}
