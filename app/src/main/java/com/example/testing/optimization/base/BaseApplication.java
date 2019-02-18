package com.example.testing.optimization.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Base64;

import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.UserImg;
import com.example.testing.optimization.entity.UserLoginInfo;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.FileUtils;
import com.example.testing.optimization.utils.GsonUtils;
import com.example.testing.optimization.utils.ImageUtils;
import com.example.testing.optimization.utils.SharePreUtils;
import com.example.testing.optimization.utils.ToolLog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yanghj on 2017/6/4.
 */

public class BaseApplication extends Application {

    public static Context  mContext;

    public static Map<String, UserSimpleInfo> mRankLongPeriod;   //周、月、30天上榜人员
    public static Map<String, UserImg>  mSavedUserImgs;        //已经获取到的人的头像url
    public static Map<String, List<PersonalPub.FbbTodayItemListBean.StockItemInfo>>   mMemberDetails = new ArrayMap<>(15); //memberId，购买股票详情。

    //关注的人列表
    public static Map<String, UserSimpleInfo> mIntrestUsers = new ArrayMap<>(10);

    public static boolean       mIsLogined = false;

    //过滤掉的人的id
    public static Set<String> mFilterId = new ArraySet<>(2);
    public static UserLoginInfo mUserLoginInfo = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        initData();
    }

    private void initData() {
        mFilterId.add("20827");

        //读取排行榜及用户头像昵称等
        mUserLoginInfo = SharePreUtils.getObject(InitData.SpKeyUserLoginInfo, UserLoginInfo.class);
        if (null == mUserLoginInfo) {
            mUserLoginInfo = new UserLoginInfo();
        }

        try {
            String periodRank = SharePreUtils.getString(InitData.SpKeyPerioRank);
            BaseApplication.mRankLongPeriod = GsonUtils.castJsonToMapUserSimpleInfo(periodRank);
        } catch (Exception ex) {
            SharePreUtils.putString(InitData.SpKeyPerioRank, "");
            ToolLog.e("初始化排行榜数据出现异常");
        }

        if (null == BaseApplication.mRankLongPeriod) {
            BaseApplication.mRankLongPeriod  = new ArrayMap<>(10);
        }

        try {
            String userImg = SharePreUtils.getString(InitData.SpKeyUserImg);
            BaseApplication.mSavedUserImgs = GsonUtils.castJsonToMapUserImg(userImg);
        } catch (Exception ex) {        //数据出现了异常，清空保存的数据
            SharePreUtils.putString(InitData.SpKeyUserImg, "");
            ToolLog.e("初始化头像数据出现异常");
        }

        if (null == BaseApplication.mSavedUserImgs) {
            BaseApplication.mSavedUserImgs  = new ArrayMap<>(20);
        }

        initInterest();

        ToolLog.i("读取数据完成-----排行榜：" + BaseApplication.mRankLongPeriod.size() + "  images:" + BaseApplication.mSavedUserImgs.size());
    }

    //把关注的人提前加到排行榜中去请求
    private void initInterest() {
        for (UserImg userImg : BaseApplication.mSavedUserImgs.values()) {
            if (userImg.isInterest()) {
                UserSimpleInfo info = new UserSimpleInfo();
                info.setMemberId(userImg.getMemberId());
                info.setUserName(userImg.getNickname());
                info.setHeadImg(userImg.getHeadImg());
                info.setMobile(userImg.getMobile());
                info.setInteresting(true);
                info.setCredentialId(userImg.getCredentialId());
                mIntrestUsers.put(userImg.getMemberId(), info);
            }
        }
    }
}
