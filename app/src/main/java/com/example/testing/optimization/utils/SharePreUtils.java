package com.example.testing.optimization.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testing.optimization.base.BaseApplication;

/**
 * Created by yanghj on 2017/6/5.
 */

public class SharePreUtils {
    static class Init {
        private static SharedPreferences  mShared = BaseApplication.mContext.getSharedPreferences("optimization", Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {
        if (null == key || null == value) {
            return;
        }

        Init.mShared.edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        return Init.mShared.getString(key, "");
    }


    public static void putLong(String key, long value) {
        Init.mShared.edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return Init.mShared.getLong(key, 0);
    }

    public static <T> T getObject(String key, Class<T> type) {
        return GsonUtils.castJsonObject(getString(key), type);
    }

    public static <T> void putObject(String key, T obj) {
        putString(key, GsonUtils.castObjectJson(obj));
    }
}
