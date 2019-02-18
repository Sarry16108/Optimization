package com.example.testing.optimization.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/6/2.
 */

public class TimeUtils {
    private static Date mDate = new Date();
    private static SimpleDateFormat sd = new SimpleDateFormat(
            "yyyy-MM-dd kk:mm:ss");
    private static SimpleDateFormat gmtFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.US);
    private static SimpleDateFormat formatMDHMS = new SimpleDateFormat("MM-dd kk:mm:ss");


    public static long getFromYMDHMS(String str) {
        try {
            Date date = sd.parse(str);
            return date.getTime();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    //转化成gmt标准时间
    public static long getFromGmt(String str) {
        try {
            return gmtFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMDHMS(long time) {
        mDate.setTime(time);
        return formatMDHMS.format(mDate).toString();
    }
}
