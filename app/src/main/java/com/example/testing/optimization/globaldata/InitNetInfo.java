package com.example.testing.optimization.globaldata;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface InitNetInfo {

    //post or get
    String  MODE_POST   = "POST";
    String  MODE_GET   = "GET";

    //host
    String  WebHostTuan = "biz-tuan.uspard.com";
    String  WebHostMem = "biz-mem.uspard.com";
    String  WebHostNews = "biz-news.uspard.com";
    String  WebHostActivity = "biz-activity.uspard.com";
    String  WebHostUs = "biz-us.uspard.com";
    String  WebHostHq = "biz-hq.uspard.com";


    //public or private
    String  OpenTypePub = "pub";
    String  OpenTypePri = "pri";

    //功能类型
    String  BullBearPub = "bull-bear-pub";
    String  BullBear     = "bull-bear";
    String  FreeBullBear = "free-bull-bear";
    String  Login = "login";
    String  FuncUser = "user";










    //POST 请求方法
    String MethodLoginCheck = "check";                              //登录
    String MethodLoginInfo = "info";                                //每次打开app的token、cookie信息获取
    String MethodPersonPubInfo = "freeBullBearHoldTodayPub";      //个人信息
    String MethodRankInfo = "userFreeBullBearRankInfo";           //排行榜信息   {"rankByType":"REVENUE","timeRangeType":"MONTH"}

    //GET 请求方法
    String MethodMemberImg = "img";                                 //成员头像  GET /pub/user/img.jhtml?mid=-9362

}
