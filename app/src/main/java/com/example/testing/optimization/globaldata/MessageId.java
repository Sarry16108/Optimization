package com.example.testing.optimization.globaldata;

/**
 * Created by yanghj on 2017/6/4.
 */

public interface MessageId {

    //base activity 0 ~ 100;
    int     REFRESH_NETWORK_STATE = 1;      //网络状态
    int     TOAST_TIP               = 2;      //toast类型提示信息
    int     ACCOUNT_LOGIN           = 3;      //账号登录


    //main
    int   REFRESH_HOTDEGREE_LIST = 100;     //刷新推荐榜
    int   REQUEST_PERSON_INFO   = 101;      //请求个人信息
    int   REFRESH_TIMES         = 102;          //请求排行榜次数
    int   ADD_NEW_RECORD        = 103;          //新纪录添加
    int   AMOUNT_UPDATE         = 104;          //总数刷新：推荐页是总排行榜人数，新纪录页是新纪录条数
    int   UPDATE_REVENUE_RANK     = 105;        //排行榜刷新
    int   INIT_DATA                 = 106;      //初始化数据

    //member 当前购买项页 :130~
    int   SHOW_DATA_LIST        = 130;      //刷新数据


    //request code
    //main activity
    int  RequestMainPermission      = 1;    //请求权限
}
