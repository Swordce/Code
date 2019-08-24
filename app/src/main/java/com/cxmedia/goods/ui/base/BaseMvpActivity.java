package com.cxmedia.goods.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cxmedia.goods.MVP.BasePresenter;
import com.cxmedia.goods.MVP.IBaseView;
import com.cxmedia.goods.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Activity的基
 * Created by wangwenzhang on 2017/11/9.
 */

public abstract class BaseMvpActivity<P extends BasePresenter>extends AppCompatActivity implements IBaseView<P> {
    protected P presenter;
    protected String TAG=getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        setPresenter(presenter);
        initView();
        getData();

    }

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 获取数据
     */
    public abstract void getData();

    /**
     * 设置布局文件id
     * @return
     */
    public abstract int getLayout();

    /**
     * 布局销�调用presenter置空view，防止内存溢�
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
