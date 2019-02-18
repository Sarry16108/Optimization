package com.example.testing.optimization.utils;

/**
 * Created by yanghj on 2017/6/4.
 */

public class StringUtils {

    public static String takeBeforeFirstSperator(String value, String spe) {
        int pos = value.indexOf(spe);
        if (0 < pos) {
            return value.substring(0, pos);
        }

        return "";
    }

    //t=1497242751119; Domain=uspard.com; Expires=Mon, 19-Jun-2017 04:45:51 GMT; Path=/
    public static long takeExpiredTimeFromCookie(String value) {
        int startPos = value.indexOf("Expires");
        if (0 < startPos) {
            int endPos = value.indexOf("GMT");
            if (endPos > startPos) {
                String expires = value.substring(startPos + 8, endPos + 3);
                return TimeUtils.getFromGmt(expires);
            }
        }

        return 0;
    }

    public static String intToString(int value) {
        return String.valueOf(value);
    }
}
