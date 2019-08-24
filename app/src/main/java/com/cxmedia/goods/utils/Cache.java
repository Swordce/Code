package com.cxmedia.goods.utils;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

/**
 * 缓存类<br>
 * <p>目前使用Hawk(轻量级缓存)处理缓存</p>
 * Created by wk
 */
public final class Cache {

    public static void init(Context context){
        Hawk.init(context).build();
    }

    public static <T> boolean put(String key, T value) {
        return Hawk.put(key, value);
    }

    public static <T> T get(String key) {
        return Hawk.get(key);
    }

    public static <T> T get(String key, T defaultValue) {
        return Hawk.get(key, defaultValue);
    }

    public static boolean contains(String key){
        return Hawk.contains(key);
    }

    public static long count() {
        return Hawk.count();
    }

    public static boolean deleteAll() {
        return Hawk.deleteAll();
    }

    public static boolean delete(String key) {
        return Hawk.delete(key);
    }
}
