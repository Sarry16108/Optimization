package com.example.testing.optimization.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.testing.optimization.R;
import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.globaldata.InitData;

/**
 * Created by Administrator on 2017/6/5.
 */

public class NotificationUtils {
    private int mNotifications = 0;
    private final int MAX_COUNT = 10;

    private static class Init{
        public static NotificationUtils mIntance = new NotificationUtils();
    }

    public static NotificationUtils getInstance() {
        return Init.mIntance;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String content) {
        NotificationManager notificationManager = (NotificationManager) BaseApplication.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(BaseApplication.mContext)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notificationManager.notify((mNotifications++) % MAX_COUNT, notification);
    }

    /**
     *
     * @param name      谁
     * @param bullbear  多空
     * @param codeName  code名
     * @param rank  排行
     * @param buyCt 手
     * @param buyOrSell  买or卖出
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String name, String rank, float buyCt, String bullbear, String codeName, boolean buyOrSell, float price) {
        String title = (InitData.BuyTypeBull.equals(bullbear) ? "看多" : "看空…") + codeName;
        String content = "排行第" + rank + "的<" + name + (buyOrSell ? ">购买了" : ">卖出了") + buyCt + "手，价格:" + price;
        showNotification(title, content);
    }
}
