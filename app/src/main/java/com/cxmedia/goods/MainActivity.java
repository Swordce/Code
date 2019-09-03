package com.cxmedia.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmedia.goods.ui.adapter.TabVpAdapter;
import com.cxmedia.goods.ui.home.fragment.HomeFragment;
import com.cxmedia.goods.ui.server.fragment.ServerFragment;
import com.cxmedia.goods.ui.user.fragment.UserFragment;
import com.cxmedia.goods.utils.AppManager;
import com.cxmedia.goods.utils.Cache;
import com.cxmedia.goods.widgets.viewpager.MyViewPager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_vp)
    MyViewPager homeVp;
    @BindView(R.id.home_tab)
    TabLayout homeTab;
    private List<Fragment> fragmentList;
    private TabVpAdapter tabAdapter;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,ContextCompat.getColor(this,R.color.theme_color));
        ButterKnife.bind(this);
        addFragment();
        initTab();
    }

    private void initTab() {
        tabAdapter = new TabVpAdapter(this, getSupportFragmentManager(), fragmentList);
        homeVp.setAdapter(tabAdapter);
        homeVp.setOffscreenPageLimit(4);
        homeTab.setupWithViewPager(homeVp);
        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab tab = homeTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(tabAdapter.getCustomView(i));
                if (i == 0) {
                    ((ImageView) tab.getCustomView().findViewById(R.id.tab_iv)).setSelected(true);
                    ((TextView) tab.getCustomView().findViewById(R.id.tab_tv)).setSelected(true);
                }
            }
        }

        homeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.tab_iv)).setSelected(true);
                ((TextView) tab.getCustomView().findViewById(R.id.tab_tv)).setSelected(true);
                homeVp.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.tab_iv)).setSelected(false);
                ((TextView) tab.getCustomView().findViewById(R.id.tab_tv)).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void addFragment() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ServerFragment());
        fragmentList.add(new UserFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                Cache.deleteAll();
                AppManager.getAppManager().finishAllActivity();
                AppManager.getAppManager().AppExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
