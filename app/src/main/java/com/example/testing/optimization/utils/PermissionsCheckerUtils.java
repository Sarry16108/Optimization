package com.example.testing.optimization.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.globaldata.MessageId;

/**
 * Created by Administrator on 2017/6/12.
 */

public class PermissionsCheckerUtils {

    private String [] permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public PermissionsCheckerUtils() {

    }

    public boolean checkImportantPermissions() {
        return lacksPermissions(permissions);
    }

    // 判断权限集合
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(BaseApplication.mContext, permission) != PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(AppCompatActivity activity) {
        activity.requestPermissions(permissions, MessageId.RequestMainPermission);
    }
}
