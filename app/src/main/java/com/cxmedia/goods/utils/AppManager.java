package com.cxmedia.goods.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by zwj on 2016/8/18.
 */
public class AppManager {
    private Stack<WeakReference<Activity>> activityStack;

    private  static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if(instance == null){
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<WeakReference<Activity>>();
        }
        activityStack.add(new WeakReference<Activity>(activity));
    }

    public boolean isEmptyStack() {
        if(activityStack == null) {
            return true;
        }
        return false;
    }

    public int activityStackCount() {
        if(activityStack == null) {
            return 0;
        }
        return activityStack.size();
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        WeakReference<Activity> activity = activityStack.lastElement();
        return activity.get();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        WeakReference<Activity> activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(WeakReference<Activity> activity) {
        if (activity.get() != null) {
            activityStack.remove(activity);
            activity.get().finish();
//            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : activityStack) {
            if (activity.get().getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     *
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i).get()) {
                activityStack.get(i).get().finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            if(activityMgr != null) {
                activityMgr.killBackgroundProcesses(context.getPackageName());
            }
            System.exit(0);
        } catch (Exception e) {
            Log.e("fdsf",e.getMessage()+" ");
        }
    }

}
