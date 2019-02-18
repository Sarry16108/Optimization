package com.example.testing.optimization.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.testing.optimization.base.BaseApplication;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ToastUtils {

    public static void shortToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }


    public static void longToast(String toast) {
        Toast.makeText(BaseApplication.mContext, toast, Toast.LENGTH_LONG).show();
    }

    public static void shortToast(int res) {
        Toast.makeText(BaseApplication.mContext, res, Toast.LENGTH_SHORT).show();
    }
}
