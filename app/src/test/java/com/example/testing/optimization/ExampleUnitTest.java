package com.example.testing.optimization;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Base64;

import com.example.testing.optimization.entity.BaseResultBean;
import com.example.testing.optimization.entity.NetCallback;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.FileUtils;
import com.example.testing.optimization.utils.GsonUtils;
import com.example.testing.optimization.utils.ImageUtils;
import com.example.testing.optimization.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public class Stock implements Comparable<Stock>{
        int hotDegree;

        public Stock(int hotDegree) {
            this.hotDegree = hotDegree;
        }

        @Override
        public int compareTo(@NonNull Stock o) {    //倒序
            return o.hotDegree - this.hotDegree;
        }
    }

    @Test
    public void comparation() {

        List<Stock> stockInfoList = new ArrayList<>(10);
        stockInfoList.add(new Stock(10));
        stockInfoList.add(new Stock(8));
        stockInfoList.add(new Stock(5));
        stockInfoList.add(new Stock(11));
        stockInfoList.add(new Stock(10));
        stockInfoList.add(new Stock(29));
        stockInfoList.add(new Stock(50));
        stockInfoList.add(new Stock(100));
        Collections.sort(stockInfoList);
        System.out.println();
    }

    @Test
    public void testPersonInfo() {
        ToolRequest.getInstance().getMemberPub("5368", InitData.ContainAll, new NetCallback() {
            @Override
            public void onSuccess(String method, BaseResultBean data) {
                System.out.println(method + data.toString());
            }

            @Override
            public void onError(String method, int connCode, String data) {
                System.out.println(method + data.toString());
            }
        });
    }

    @Test
    public void testRankInfo() {
        ToolRequest.getInstance().getRankInfo(InitData.RankTypeRevenue, InitData.TimeTypeDate, new NetCallback() {
            @Override
            public void onSuccess(String method, BaseResultBean data) {
                System.out.println(method + data.toString());
            }

            @Override
            public void onError(String method, int connCode, String data) {
                System.out.println(method + data.toString());
            }
        });
    }

    @Test
    public void sortArrayMap() {
        Map<String, UserSimpleInfo> map = new ArrayMap<>(10);
        map.put("1", new UserSimpleInfo("1", "$300", 1, "1", 2, "111", "50%"));
        map.put("2", new UserSimpleInfo("2", "$400", 1, "1", 2, "111", "50%"));
        map.put("3", new UserSimpleInfo("3", "$300", 1, "1", 2, "111", "50%"));
        map.put("4", new UserSimpleInfo("4", "$800", 1, "1", 2, "111", "50%"));
        map.put("5", new UserSimpleInfo("5", "$320", 1, "1", 2, "111", "50%"));
        map.put("6", new UserSimpleInfo("6", "$30", 1, "1", 2, "111", "50%"));
        map.put("7", new UserSimpleInfo("7", "$50", 1, "1", 2, "111", "50%"));
        map.put("8", new UserSimpleInfo("8", "$900", 1, "1", 2, "111", "50%"));
        map.put("9", new UserSimpleInfo("9", "$100", 1, "1", 2, "111", "50%"));

        Iterator<Map.Entry<String, UserSimpleInfo>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            UserSimpleInfo stockInfo = iterator.next().getValue();

            //如果没有人买了就把这条给移除
            if (null == stockInfo || stockInfo.getAmount().equals("$300")) {
                iterator.remove();
                continue;
            }
            System.out.println(stockInfo.toString());
        }

        String json = GsonUtils.castMapToJson(map);

        System.out.println(json);

//        Map<String, UserSimpleInfo> map2 = new Gson().fromJson(json, new TypeToken<Map<String, UserSimpleInfo>>(){}.getType());
        Map<String, UserSimpleInfo> map2 = GsonUtils.castJsonToMapUserSimpleInfo(json);

        String value = map2.get("1").getAmount();
        System.out.println(json);
    }



    @Test
    public void testExpiredExtract() {
        String value = "t=1497242751119; Domain=uspard.com; Expires=Mon, 19-Jun-2017 04:45:51 GMT; Path=/";
        long time = StringUtils.takeExpiredTimeFromCookie(value);
        System.out.println("time=" + time);
    }

    @Test
    public void testSaveBase64() {
        String value = "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\\u003d\\u003d";

        System.out.println("path=" );
    }
}