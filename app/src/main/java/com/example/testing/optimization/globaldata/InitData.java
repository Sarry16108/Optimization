package com.example.testing.optimization.globaldata;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface InitData {

    //排行榜类型
    String RankTypeRevenue  = "REVENUE";     //收益
    String RankTypeWinRatio = "WINRATIO";   //胜率

    //时间范围
    String TimeTypeDate    = "DATE";     //按日
    String TimeTypeWeek    = "WEEK";     //按周
    String TimeTypeMonth   = "MONTH";   //按月
    String TimeTypeWhole   = "WHOLE";    //30天

    //信息值：containFut
    String ContainAll = "ALL";   //所有


    String RecordStatusDraft  = "DRAFT";    //持有
    String RecordStatusFinish  = "FINISH";    //卖出

    //多空
    String BuyTypeBull = "BULL";    //多
    String BuyTypeBear = "BEAR";    //空



    //sharedPreferences name
    String  SpKeyCookie = "Cookie";         //cookie
    String  SpKeyPerioRank = "perioRank";   //历史收益排行榜
    String  SpKeyUserImg = "userImg";       //用户头像
    String  SpKeyUserLoginInfo = "userLoginInfo";       //用户账号信息



    //页面跳转标记
    String  ACT_MARK_MAIN_ACT   =   "act_main";
}
