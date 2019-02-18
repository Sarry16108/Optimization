package com.example.testing.optimization.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.testing.optimization.base.BaseApplication;

/**
 * Created by yanghj on 2017/6/7.
 */

public class NetUtils {

    public static boolean checkNetwork() {
        ConnectivityManager connMgr = (ConnectivityManager) BaseApplication.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取ConnectivityManager对象对应的NetworkInfo对象
        //获取WIFI连接的信息
        NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //获取移动数据连接的信息
        NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return dataNetworkInfo.isConnected() || wifiNetworkInfo.isConnected();
    }
}
