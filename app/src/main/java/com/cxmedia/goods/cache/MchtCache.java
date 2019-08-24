package com.cxmedia.goods.cache;

import com.cxmedia.goods.MVP.model.LoginResult;

import java.util.ArrayList;
import java.util.List;

public class MchtCache {
    private static MchtCache cache;
    private List<LoginResult.MchtListBean> mchtList;

    private MchtCache() {
        mchtList = new ArrayList<>();
    }

    public static void pushCache() {
        cache = null;
        cache = new MchtCache();
        MchtCacheManager.getInstance().push(cache);
    }

    public static void reset() {
        MchtCacheManager.getInstance().pop();
        cache = MchtCacheManager.getInstance().peek();
    }

    public static MchtCache getInstance() {
        if (cache == null) {
            cache = new MchtCache();
        }
        return cache;
    }

    public void addMchtList(List<LoginResult.MchtListBean> mcht) {
        mchtList.clear();
        mchtList.addAll(mcht);
    }

    public List<LoginResult.MchtListBean> getMchtList() {
        return mchtList;
    }

    public String getMchtName(String mchtNo) {
        for (LoginResult.MchtListBean bean : mchtList) {
            if(mchtNo.equals(bean.getMchtNo())) {
                return bean.getMchtName();
            }
        }
        return "";
    }

}
