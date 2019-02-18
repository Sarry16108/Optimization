package com.example.testing.optimization.utils;


import android.util.Log;

/**
 * 日志工具类
 */
public class ToolLog {
    public static boolean isDebug = true;
    private static String TAG = "优选";

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.i(TAG, tag1 + ":" + tag2 + "---" + msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.d(TAG, tag1 + ":" + tag2 + "---" + msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.e(TAG, tag1 + ":" + tag2 + "---" + msg);
        }
    }


    public static void e(String tag1, String tag2, String tag3, String msg) {
        if (isDebug) {
            Log.e(TAG, tag1 + ":<" + tag2 + ">" + tag3 + "---" + msg);
        }
    }
}
