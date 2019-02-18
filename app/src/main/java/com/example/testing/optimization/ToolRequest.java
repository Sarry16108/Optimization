package com.example.testing.optimization;

import android.content.Context;
import android.text.TextUtils;

import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.entity.BaseResponse;
import com.example.testing.optimization.entity.BaseResultBean;
import com.example.testing.optimization.entity.NetCallback;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.RankInfo;
import com.example.testing.optimization.entity.UserImg;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitAppConstant;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.globaldata.InitNetInfo;
import com.example.testing.optimization.globaldata.MessageId;
import com.example.testing.optimization.utils.GsonUtils;
import com.example.testing.optimization.utils.ImageUtils;
import com.example.testing.optimization.utils.SharePreUtils;
import com.example.testing.optimization.utils.StringUtils;
import com.example.testing.optimization.utils.TimeUtils;
import com.example.testing.optimization.utils.ToastUtils;
import com.example.testing.optimization.utils.ToolLog;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ToolRequest {
    private final String TAG = "ToolRequest";
    private static ToolRequest mInstance = new ToolRequest();
    private UspardCache mUspardCache = new UspardCache();


    private class UspardCache {
        public String uspardToken = "";
        public String uspardSid;//Uspard-Sid
        public String setCookie = "";
        public String sessionId = "";   //JSESSIONID
        public String uspardSsid;   //Uspard-Ssid
        public String s = "";
        public String t = "";
        public String m = "";
        public String u = "";
        public String rn = "";
        public String lid = "";
        public String wx = "\"\"";
        public String bs = "";
        public String uspardver = "206012:2.6.2:20601";//"206012:2.6.2:";

        public void initHost(String type) {
            switch (type) {
                case InitNetInfo.WebHostActivity:
                    sessionId = "286E6145C2ED7C849BD07FE0EE1C59CF";
                    break;
                case InitNetInfo.WebHostHq:
                    uspardSid = "A6A2EAFE05D41D6BEB4B93F443DD5A4E";
                    uspardSsid = "0a1fc80ea82fbe656c01e6293d6dd48c";
                    break;
                case InitNetInfo.WebHostMem:
                    uspardSid = "6CDF206BA7CB0921FDC18C9742CEB084";
                    uspardSsid = "9c659c901f51f90bf4353d1ff53e0e71";
                    break;
                case InitNetInfo.WebHostNews:
                    uspardSid = "C1A040EB8FCD715979E7B10B54243A26";
                    uspardSsid = "f41cf60b358ef770c6915dcaa0c357a4";
                    break;
                case InitNetInfo.WebHostTuan:
                    uspardSid = "405B7616F6A6FFB7F001DEAE4A122BF7";
                    uspardSsid = "9c659c901f51f90bf4353d1ff53e0e71";
                    break;
                case InitNetInfo.WebHostUs:
                    uspardSid = "0E97DE7187F0C0ECDA283D521C1F9573";
                    uspardSsid = "43539bcf6c9a5566c466f7a81753595c";
                    break;
            }
        }

        public String getSetCookie() {
            if (TextUtils.isEmpty(setCookie)) {
                setCookie = SharePreUtils.getString(InitData.SpKeyCookie);
            }
            return setCookie;
        }
    }

    private String uspardreq = "";

    private ToolRequest() {
    }

    public static ToolRequest getInstance() {
        return mInstance;
    }

    /*
    https://biz-tuan.uspard.com/pri/free-bull-bear/userFreeBullBearRankInfo.jhtml
{"rankByType":"REVENUE","timeRangeType":"DATE"}
     */
    public void getRankInfo(String rankType, String rangeType, NetCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rankByType", rankType);
        jsonObject.addProperty("timeRangeType", rangeType);
        uspardreq = "/bullbearfree/leverprofitranknew";
        threadRequest(InitNetInfo.MODE_POST, InitNetInfo.WebHostTuan, InitNetInfo.OpenTypePri, InitNetInfo.FreeBullBear, InitNetInfo.MethodRankInfo, jsonObject.toString(), callback, RankInfo.class);
    }

    /*
    https://biz-tuan.uspard.com/pub/bull-bear-pub/freeBullBearHoldTodayPub.jhtml
{"userId":"-9275","containFut":"ALL"}
     */
    public void getMemberPub(String userId, String containFut, NetCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("containFut", containFut);
        uspardreq = "/bullbearfree/leverfreedomotherincome";
        threadRequest(InitNetInfo.MODE_POST, InitNetInfo.WebHostTuan, InitNetInfo.OpenTypePub, InitNetInfo.BullBearPub, InitNetInfo.MethodPersonPubInfo, jsonObject.toString(), callback, PersonalPub.class);
    }

    //获取登录的信息
    public void getLoginInfo() {
        uspardreq = "/index";
        threadRequest(InitNetInfo.MODE_POST, InitNetInfo.WebHostMem, InitNetInfo.OpenTypePub, InitNetInfo.Login, InitNetInfo.MethodLoginInfo, null, new NetCallback() {
            @Override
            public void onError(String method, int connCode, String data) {
                ToolLog.e("getLoginInfo", method, "<" + connCode + ">" + data);
            }
            @Override
            public void onSuccess(String method, BaseResultBean data) {
                updateLongTimeProfit(InitData.TimeTypeWhole);   //获取长期排行榜信息
            }
        }, null);
    }


    //获取登录的信息
    public void getLoginByAccount(final BaseActivity activity, final String username, final String password) {
        uspardreq = "/user/login";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        threadRequest(InitNetInfo.MODE_POST, InitNetInfo.WebHostMem, InitNetInfo.OpenTypePub, InitNetInfo.Login, InitNetInfo.MethodLoginCheck, jsonObject.toString(), new NetCallback() {
            @Override
            public void onError(String method, int connCode, String data) {
                ToolLog.e("getLoginByAccount", method, "<" + connCode + ">" + data);
                activity.packMsgAndSend(MessageId.TOAST_TIP, "登录失败：" + data);
            }
            @Override
            public void onSuccess(String method, BaseResultBean data) {
                activity.packMsgAndSend(MessageId.TOAST_TIP, "登录成功");
                BaseApplication.mUserLoginInfo.setUsername(username);
                BaseApplication.mUserLoginInfo.setPassword(password);

                SharePreUtils.putObject(InitData.SpKeyUserLoginInfo, BaseApplication.mUserLoginInfo);
                updateLongTimeProfit(InitData.TimeTypeWhole);   //获取长期排行榜信息
            }
        }, null);
    }

    private <T extends BaseResultBean> void threadRequest(final String mode, final String host, final String openType, final String subType, final String method,
                                                          final String requestData, final NetCallback callback, final Class<T> classType) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if (InitNetInfo.MODE_POST.equals(mode)) {
                    doPostRequest(host, openType, subType, method, requestData, callback, classType);
                } else {
                    doGetRequest(host, openType, subType, method, requestData, callback, classType);
                }

            }
        });
    }

    /**
     * https://biz-tuan.uspard.com/pub/bull-bear-pub/freeBullBearHoldTodayPub.jhtml
     *
     * @param host       biz-tuan.uspard.com
     * @param openType   pub
     * @param subType    bull-bear-pub
     * @param method     freeBullBearHoldTodayPub
     * @param requestData
     * @param callback
     * @param classType
     * @param <T>
     */
    private <T extends BaseResultBean> void doPostRequest(String host, String openType, String subType, String method, String requestData, NetCallback callback, Class<T> classType) {
        HttpURLConnection connection = null;
        try {
            StringBuilder urlString = new StringBuilder("https://");
            urlString.append(host).append('/').append(openType).append('/').append(subType).append('/').append(method).append(".jhtml");
            URL url = new URL(urlString.toString());//"https://biz-tuan.uspard.com/pub/bull-bear-pub/freeBullBearHoldTodayPub.jhtml");
            connection = (HttpURLConnection) url.openConnection();

            //cookie必须放在最开始，否则会在设置Cookie时候出现异常
            //String cookie = "Uspard-Sid=405B7616F6A6FFB7F001DEAE4A122BF7; m=20827; u=" + URLEncoder.encode("就认识这几个字", "UTF-8") + "; rn=" + URLEncoder.encode("杨会军", "UTF-8") + "; lid=38266; wx=\"\"; bs=800301%2C800201; t=" + "1496579039974" + "; s=1aa2712445c8ee01d3065c47b26cf9b6; Uspard-Ssid=9c659c901f51f90bf4353d1ff53e0e71";
            mUspardCache.initHost(host);
//            String cookie = "Uspard-Sid=" + mUspardCache.uspardSid + "; m=" + mUspardCache.m + "; u=" + URLEncoder.encode(mUspardCache.u, "UTF-8")
//                    + "; rn=" + URLEncoder.encode(mUspardCache.rn, "UTF-8") + "; lid=" + mUspardCache.lid + "; wx=" + mUspardCache.wx
//                    + "; bs=" + mUspardCache.bs + "; t=" + mUspardCache.t + "; s=" + mUspardCache.s + "; Uspard-Ssid=" + mUspardCache.uspardSsid;
            StringBuffer buffer = new StringBuffer(mUspardCache.getSetCookie());
            if (0 == buffer.length()) {
                String cookie = "m=" + mUspardCache.m + "; u=" + URLEncoder.encode(mUspardCache.u, "UTF-8")
                    + "; rn=" + URLEncoder.encode(mUspardCache.rn, "UTF-8") + "; lid=" + mUspardCache.lid + "; wx=" + mUspardCache.wx
                    + "; bs=" + mUspardCache.bs + "; t=" + mUspardCache.t + "; s=" + mUspardCache.s + ";";
                buffer.append(cookie);
            }

            buffer.append("Uspard-Sid=" + mUspardCache.uspardSid + ";");
            buffer.append("Uspard-Ssid=" + mUspardCache.uspardSsid);
            ToolLog.i(TAG, "doPostRequest", "===================" + url + "========================");
            ToolLog.i(TAG, "doPostRequest", "cookie" + buffer.toString());
            connection.setRequestProperty("Cookie", buffer.toString());

            connection.setRequestMethod(InitNetInfo.MODE_POST);
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Origin", "file://");
            connection.setRequestProperty("uspardnw", "wifi");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; Redmi 3S Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 Uspard");
            connection.setRequestProperty("uspardver", mUspardCache.uspardver);
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Uspard-Token", mUspardCache.uspardToken);
            connection.setRequestProperty("uspardlid", mUspardCache.lid);
            connection.setRequestProperty("usparddev", "Android");
            connection.setRequestProperty("uspardreq", System.currentTimeMillis() + ":" + uspardreq);
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(2 * 1000);
            connection.setReadTimeout(2 * 1000);

            if (!TextUtils.isEmpty(requestData)) {
                ToolLog.i(TAG, "doPostRequest", "content:" + requestData);
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");    //针对info接口，无数据不传递该字段
                connection.setRequestProperty("Content-Length", String.valueOf(requestData.length()));

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(requestData);
                dataOutputStream.flush();
                dataOutputStream.close();
            } else {
                connection.setRequestProperty("Content-Length", "0");
            }

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "UTF-8"));

                StringBuilder builder = new StringBuilder();
                char[] value = new char[2048];
                int len = 0;
                while (-1 != (len = bufferedReader.read(value))) {
                    builder.append(value, 0, len);
                }

                try {
                    baseProcess(connection, method, builder.toString(), callback, classType);
                } catch (Exception e) {
                    ToolLog.e(e.getMessage());
                }
            } else {
                if (null != callback) {
                    callback.onError(method, connection.getResponseCode(), connection.getResponseMessage());
                } else {
                    ToolLog.e("doPostRequest", method, "errorCode:" + connection.getResponseCode() + " message:" + connection.getResponseMessage());
                }
            }
            ToolLog.i(TAG, "doPostRequest", "response code:" + connection.getResponseCode());
        } catch (JsonParseException e) {
            if (null != callback) {
                callback.onError(method, 0, e.getMessage());
            }else {
                ToolLog.e("doPostRequest", method, "message:" + e.getMessage());
            }
        } catch (IOException e) {
            if (null != callback) {
                callback.onError(method, 0, e.getMessage());
            }else {
                ToolLog.e("doPostRequest", method, "message:" + e.getMessage());
            }
        } finally {
            connection.disconnect();
        }
        ToolLog.i(TAG, "doPostRequest", "===========================================================");
    }

    private <T extends BaseResultBean> void doGetRequest(String host, String openType, String subType, String method, String requestData, NetCallback callback, Class<T> classType) {
        HttpURLConnection connection = null;
        try {
            StringBuilder urlString = new StringBuilder("https://");
            urlString.append(host).append('/').append(openType).append('/').append(subType).append('/').append(method).append(".jhtml");

            //请求的数据
            if (!TextUtils.isEmpty(requestData)) {
                urlString.append('?').append(requestData);
            }

            URL url = new URL(urlString.toString());
            connection = (HttpURLConnection) url.openConnection();

            //cookie必须放在最开始，否则会在设置Cookie时候出现异常
            mUspardCache.initHost(host);
            StringBuffer buffer = new StringBuffer(mUspardCache.getSetCookie());
            if (0 == buffer.length()) {
                String cookie = "m=" + mUspardCache.m + "; u=" + URLEncoder.encode(mUspardCache.u, "UTF-8")
                        + "; rn=" + URLEncoder.encode(mUspardCache.rn, "UTF-8") + "; lid=" + mUspardCache.lid + "; wx=" + mUspardCache.wx
                        + "; bs=" + mUspardCache.bs + "; t=" + mUspardCache.t + "; s=" + mUspardCache.s + ";";
                buffer.append(cookie);
            }

            buffer.append("Uspard-Sid=" + mUspardCache.uspardSid + ";");
            buffer.append("Uspard-Ssid=" + mUspardCache.uspardSsid);
            ToolLog.i(TAG, "doGetRequest", "===================" + url + "========================");
            ToolLog.i(TAG, "doGetRequest", "cookie" + buffer.toString());
            connection.setRequestProperty("Cookie", buffer.toString());

            connection.setRequestMethod(InitNetInfo.MODE_GET);
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Origin", "file://");
            connection.setRequestProperty("uspardnw", "wifi");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; Redmi 3S Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 Uspard");
            connection.setRequestProperty("uspardver", mUspardCache.uspardver);
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Uspard-Token", mUspardCache.uspardToken);
            connection.setRequestProperty("uspardlid", mUspardCache.lid);
            connection.setRequestProperty("usparddev", "Android");
            connection.setRequestProperty("uspardreq", System.currentTimeMillis() + ":" + uspardreq);
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(2 * 1000);

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "UTF-8"));

                StringBuilder builder = new StringBuilder();
                char[] value = new char[2048];
                int len = 0;
                while (-1 != (len = bufferedReader.read(value))) {
                    builder.append(value, 0, len);
                }

                try {
                    baseProcess(connection, method, builder.toString(), callback, classType);
                } catch (Exception e) {
                    ToolLog.e(e.getMessage());
                }
            } else {
                if (null != callback) {
                    callback.onError(method, connection.getResponseCode(), connection.getResponseMessage());
                } else {
                    ToolLog.e("doGetRequest", method, "errorCode:" + connection.getResponseCode() + " message:" + connection.getResponseMessage());
                }
            }
            ToolLog.i(TAG, "doGetRequest", "response code:" + connection.getResponseCode());
        } catch (JsonParseException e) {
            if (null != callback) {
                callback.onError(method, 0, e.getMessage());
            }else {
                ToolLog.e("doGetRequest", method, "message:" + e.getMessage());
            }
        } catch (IOException e) {
            if (null != callback) {
                callback.onError(method, 0, e.getMessage());
            }else {
                ToolLog.e("doGetRequest", method, "message:" + e.getMessage());
            }
        } finally {
            connection.disconnect();
        }
        ToolLog.i(TAG, "doGetRequest", "===========================================================");
    }

    private <T extends BaseResultBean> void baseProcess(HttpURLConnection connection, String method, String json, NetCallback callback, Class<T> classType) throws IOException {
        ToolLog.i(TAG, "doPostRequest", "result:" + json);
        switch (method) {
            case InitNetInfo.MethodLoginCheck:
                BaseResponse baseLogin = GsonUtils.castJsonObject(json, BaseResponse.class);
                if (!baseLogin.isSuccess()) {
                    BaseApplication.mIsLogined = false;
                    if (null != callback){
                        callback.onError(method, connection.getResponseCode(), baseLogin.getMessage());
                    } else {
                        ToolLog.e("doPostRequest success: 200  values: " + json);
                    }
                    return;
                }
            case InitNetInfo.MethodLoginInfo:
                getCookie(connection);
                BaseApplication.mIsLogined = true;
                if (null != callback) {
                    callback.onSuccess(method, null);
                }
                break;
            case InitNetInfo.MethodPersonPubInfo:
            case InitNetInfo.MethodRankInfo:
            case InitNetInfo.MethodMemberImg:
                BaseResponse baseResponse = GsonUtils.castJsonObject(json, BaseResponse.class);
                if (baseResponse.isSuccess()) {
                    if (null != callback) {
                        if (null != classType) {
                            callback.onSuccess(method, baseResponse.getResult(classType));
                        } else {
                            callback.onSuccess(method, null);
                        }
                    } else {
                        ToolLog.e("doPostRequest success: 200  values: " + json);
                    }
                } else if (null != callback){
                    callback.onError(method, connection.getResponseCode(), baseResponse.getMessage());
                } else {
                    ToolLog.e("doPostRequest success: 200  values: " + json);
                }
                break;

        }
    }

    private void getCookie(URLConnection connection) {
        mUspardCache.uspardToken = connection.getHeaderField("Uspard-Token");
        Map<String, List<String>> headers = connection.getHeaderFields();
        if (headers.containsKey("Set-Cookie")) {
            mUspardCache.setCookie = "";
            for (String item : headers.get("Set-Cookie")) {
                String keyValue = StringUtils.takeBeforeFirstSperator(item, ";");
                String []str = keyValue.split("=");
                switch (str[0]) {
                    case "m":
                        mUspardCache.m = str[1];
                        BaseApplication.mFilterId.add(str[1]);
                        break;
                    case "u":
                        mUspardCache.u = str[1];
                        break;
                    case "rn":
                        mUspardCache.rn = str[1];
                        break;
                    case "lid":
                        mUspardCache.lid = str[1];
                        break;
                    case "wx":
                        mUspardCache.wx = str[1];
                        break;
                    case "bs":
                        mUspardCache.bs = str[1];
                        break;
                    case "t":
                        mUspardCache.t = str[1];
                        BaseApplication.mUserLoginInfo.setExpires(StringUtils.takeExpiredTimeFromCookie(item));
                        break;
                    case "s":
                        mUspardCache.s = str[1];
                        break;
                    default:
                        continue;
                }
                mUspardCache.setCookie +=keyValue + ";";
            }

            //save cookie
            SharePreUtils.putString(InitData.SpKeyCookie, mUspardCache.setCookie);
        }
    }

    //循环获取30日，月、周排行并记录
    private void updateLongTimeProfit(final String period) {
        ToolRequest.getInstance().getRankInfo(InitData.RankTypeRevenue, period, new NetCallback() {
            @Override
            public void onError(String method, int connCode, String data) {
                ToolLog.e("updateLongTimeProfit", method, "<" + connCode + ">" + data);
            }
            @Override
            public void onSuccess(String method, BaseResultBean data) {
                switch (period) {
                    case InitData.TimeTypeWhole:
                        updateLongTimeRank(InitAppConstant.RankTypeWhole, BaseApplication.mRankLongPeriod, (RankInfo) data);
                        updateLongTimeProfit(InitData.TimeTypeMonth);
                        break;
                    case InitData.TimeTypeMonth:
                        updateLongTimeRank(InitAppConstant.RankTypeMonth, BaseApplication.mRankLongPeriod, (RankInfo) data);
                        updateLongTimeProfit(InitData.TimeTypeWeek);
                        break;
                    case InitData.TimeTypeWeek:
                        updateLongTimeRank(InitAppConstant.RankTypeWeek, BaseApplication.mRankLongPeriod, (RankInfo) data);
                        updateRankImgInfo();
                        break;
                }
            }
        });
    }


    private void updateLongTimeRank(int rankType, Map<String, UserSimpleInfo> ranks, RankInfo rankInfo) {
        if (null == rankInfo || null == rankInfo.getTotalRankInfo() || null == rankInfo.getTotalRankInfo().getList()) {
            ToolLog.e(TAG, "saveNewInfo", "rank list is null");
            return;
        }

        for (UserSimpleInfo item : rankInfo.getTotalRankInfo().getList()) {
            if (BaseApplication.mFilterId.contains(item.getMemberId())) {
                continue;
            }

            //添加所在排行榜类型
            item.setRankType(rankType);

            //之前不存在则加入，存在的则更新数据
            ranks.put(item.getMemberId(), item);
        }
    }

    private void updateRankImgInfo() {
        for (final UserSimpleInfo simpleInfo : BaseApplication.mRankLongPeriod.values()) {
            ToolLog.d("memberId:" + simpleInfo.getMemberId() + "  img:" + simpleInfo.getHeadImg());
            if (BaseApplication.mSavedUserImgs.containsKey(simpleInfo.getMemberId())) {     //存储过就不再请求头像,todo:判断一下headImg是否为空最好
                continue;
            }

            getMemberImgInfo(simpleInfo.getMemberId());
        }
    }

    /**
     * 获取头像（包括昵称等）
     */
    public void getMemberImgInfo(final String memberId) {
        uspardreq = "/bullbearfree/leverprofitranknew"; //或者"/bullbearfree/leverfreedomlist"

        String data = "mid=" + memberId;
        threadRequest(InitNetInfo.MODE_GET, InitNetInfo.WebHostMem, InitNetInfo.OpenTypePub, InitNetInfo.FuncUser, InitNetInfo.MethodMemberImg, data, new NetCallback() {
            @Override
            public void onError(String method, int connCode, String data) {

            }

            @Override
            public void onSuccess(String method, BaseResultBean data) {
                UserImg userImg = (UserImg) data;
                if (userImg.getHeadImg().contains("data:image")) {   //保存到本地然后设置本地的url
                    String path = ImageUtils.saveImgByBase64(userImg.getHeadImg(), userImg.getMemberId());
                    if (!TextUtils.isEmpty(path)) {
                        userImg.setHeadImg("file://" + path);
                    }
                }

                ToolLog.d("memberId:" + memberId + "  img:" + userImg.getHeadImg());
                BaseApplication.mSavedUserImgs.put(memberId, userImg);
            }
        }, UserImg.class);
    }
}
