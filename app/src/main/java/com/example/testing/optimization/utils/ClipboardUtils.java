package com.example.testing.optimization.utils;

import android.content.ClipboardManager;
import android.content.Context;

import com.example.testing.optimization.base.BaseApplication;

/**
 * Created by yanghj on 2017/6/21.
 */

public class ClipboardUtils {

    public static boolean copyToClipboard(String text) {
        try {
            ClipboardManager cm = (ClipboardManager) BaseApplication.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(text);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
