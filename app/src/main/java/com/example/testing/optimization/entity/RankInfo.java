package com.example.testing.optimization.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 * 排行榜信息
 * {"rankByType":"REVENUE","timeRangeType":"DATE"}
 */

public class RankInfo extends BaseResultBean {

    /**
     * result : {"myRankInfo":{"map":{"amount":"$0","profitCt":0,"rank":"--","involveCt":0,"userName":"杨会军","memberId":20827,"ratio":"--"}},"isActivityOpen":false,"totalRankInfo":{"list":[{"amount":"$484.00","profitCt":1,"rank":1,"involveCt":1,"userName":"周**","memberId":-9147,"ratio":"100.00%"},{"amount":"$475.00","profitCt":1,"rank":2,"involveCt":2,"userName":"马**","memberId":-9160,"ratio":"50.00%"},{"amount":"$438.60","profitCt":1,"rank":3,"involveCt":2,"userName":"钱**","memberId":-9180,"ratio":"50.00%"},{"amount":"$408.00","profitCt":2,"rank":4,"involveCt":2,"userName":"何*","memberId":14111,"ratio":"100.00%"},{"amount":"$353.00","profitCt":1,"rank":5,"involveCt":1,"userName":"严*","memberId":-9317,"ratio":"100.00%"},{"amount":"$207.60","profitCt":1,"rank":6,"involveCt":2,"userName":"邓*","memberId":-9124,"ratio":"50.00%"},{"amount":"$192.00","profitCt":2,"rank":7,"involveCt":2,"userName":"康**","memberId":5368,"ratio":"100.00%"},{"amount":"$174.50","profitCt":3,"rank":8,"involveCt":5,"userName":"陈**","memberId":13445,"ratio":"60.00%"},{"amount":"$172.00","profitCt":1,"rank":9,"involveCt":1,"userName":"高**","memberId":-9343,"ratio":"100.00%"},{"amount":"$171.70","profitCt":2,"rank":10,"involveCt":2,"userName":"毕*","memberId":-9363,"ratio":"100.00%"}]}}
     */

    /**
     * myRankInfo : {"map":{"amount":"$0","profitCt":0,"rank":"--","involveCt":0,"userName":"杨会军","memberId":20827,"ratio":"--"}}
     * isActivityOpen : false
     * totalRankInfo : {"list":[{"amount":"$484.00","profitCt":1,"rank":1,"involveCt":1,"userName":"周**","memberId":-9147,"ratio":"100.00%"},{"amount":"$475.00","profitCt":1,"rank":2,"involveCt":2,"userName":"马**","memberId":-9160,"ratio":"50.00%"},{"amount":"$438.60","profitCt":1,"rank":3,"involveCt":2,"userName":"钱**","memberId":-9180,"ratio":"50.00%"},{"amount":"$408.00","profitCt":2,"rank":4,"involveCt":2,"userName":"何*","memberId":14111,"ratio":"100.00%"},{"amount":"$353.00","profitCt":1,"rank":5,"involveCt":1,"userName":"严*","memberId":-9317,"ratio":"100.00%"},{"amount":"$207.60","profitCt":1,"rank":6,"involveCt":2,"userName":"邓*","memberId":-9124,"ratio":"50.00%"},{"amount":"$192.00","profitCt":2,"rank":7,"involveCt":2,"userName":"康**","memberId":5368,"ratio":"100.00%"},{"amount":"$174.50","profitCt":3,"rank":8,"involveCt":5,"userName":"陈**","memberId":13445,"ratio":"60.00%"},{"amount":"$172.00","profitCt":1,"rank":9,"involveCt":1,"userName":"高**","memberId":-9343,"ratio":"100.00%"},{"amount":"$171.70","profitCt":2,"rank":10,"involveCt":2,"userName":"毕*","memberId":-9363,"ratio":"100.00%"}]}
     */

    private MyRankInfoBean myRankInfo;
    private boolean isActivityOpen;
    private TotalRankInfoBean totalRankInfo;

    public MyRankInfoBean getMyRankInfo() {
        return myRankInfo;
    }


    public boolean isIsActivityOpen() {
        return isActivityOpen;
    }

    public TotalRankInfoBean getTotalRankInfo() {
        return totalRankInfo;
    }


    public static class MyRankInfoBean {
        /**
         * map : {"amount":"$0","profitCt":0,"rank":"--","involveCt":0,"userName":"杨会军","memberId":20827,"ratio":"--"}
         */

        private UserSimpleInfo map;

        public UserSimpleInfo getMap() {
            return map;
        }

    }

    public static class TotalRankInfoBean {
        private List<UserSimpleInfo> list;

        public List<UserSimpleInfo> getList() {
            return list;
        }
    }
}
