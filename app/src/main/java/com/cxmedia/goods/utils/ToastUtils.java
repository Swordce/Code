package com.cxmedia.goods.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showShortToast(Context context,String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
