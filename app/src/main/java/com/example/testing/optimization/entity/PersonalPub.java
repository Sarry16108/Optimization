package com.example.testing.optimization.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 * 个人数据请求
 * {"userId":"-9275","containFut":"ALL"}
 */

public class PersonalPub extends BaseResultBean {

    /**
     * curTotalProfit : 560.9
     * fbbTodayItemList : {"list":[{"currencyType":"USD","leftBearAmount":825900,"capital":500,"isBearFull":false,"sellPrice":1.28493,"type":"FOREX","buyCt":0.5,"revenue":70,"price":1.28559,"leftAmount":781700,"id":0,"sellCt":0.5,"userRecordId":373983,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":1.27553,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"英镑兑美元","buyStatus":"SUCCESS","deposit":11,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"GBP.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 17:55:51","usedAmount":62200,"profitPrice":1.285,"countText":"0.5手","sellType":"EARN","profitPercent":14,"startTime":"2017-06-01 05:16:00","isForce":true,"priceText":"1.28353 → 1.28493","createDate":"2017-06-01","buyPrice":1.28353,"isBegin":true,"baseBuyCt":0.5,"currentProfit":70,"costPrice":1.28353,"isFinish":false,"sellDealTime":"2017-06-01 18:49:49","buyAmount":1000,"expEarn":1.285,"serial":"20170601704343","createTime":"2017-06-01 17:55:50","evenUpPrice":1.27553,"revenuePercent":14,"statusText":"已结算","bullBearType":"BULL","baseSellCt":0.5,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":1.289,"ratio":2},{"currencyType":"USD","leftBearAmount":825900,"capital":200,"isBearFull":false,"sellPrice":1.28493,"type":"FOREX","buyCt":0.5,"revenue":32,"price":1.28559,"leftAmount":781700,"id":0,"sellCt":0.5,"userRecordId":373970,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":1.28109,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"英镑兑美元","buyStatus":"SUCCESS","deposit":11,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"GBP.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 17:52:54","usedAmount":62200,"profitPrice":1.285,"countText":"0.5手","sellType":"EARN","profitPercent":16,"startTime":"2017-06-01 05:16:00","isForce":true,"priceText":"1.28429 → 1.28493","createDate":"2017-06-01","buyPrice":1.28429,"isBegin":true,"baseBuyCt":0.5,"currentProfit":32,"costPrice":1.28429,"isFinish":false,"sellDealTime":"2017-06-01 18:49:49","buyAmount":1000,"expEarn":1.285,"serial":"20170601704343","createTime":"2017-06-01 17:52:54","evenUpPrice":1.28109,"revenuePercent":16,"statusText":"已结算","bullBearType":"BULL","baseSellCt":0.5,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":1.289,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":40,"isBearFull":false,"sellPrice":1.28652,"type":"FOREX","buyCt":0.1,"revenue":23.5,"price":1.28559,"leftAmount":781700,"id":0,"sellCt":0.1,"userRecordId":373779,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":1.29207,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"英镑兑美元","buyStatus":"SUCCESS","deposit":2.2,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"GBP.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 16:49:34","usedAmount":62200,"profitPrice":1.2865,"countText":"0.1手","sellType":"EARN","profitPercent":58.75,"startTime":"2017-06-01 05:16:00","isForce":true,"priceText":"1.28887 → 1.28652","createDate":"2017-06-01","buyPrice":1.28887,"isBegin":true,"baseBuyCt":0.1,"currentProfit":23.5,"costPrice":1.28887,"isFinish":false,"sellDealTime":"2017-06-01 17:16:09","buyAmount":200,"expEarn":1.2865,"serial":"20170601704343","createTime":"2017-06-01 16:49:32","evenUpPrice":1.29207,"revenuePercent":58.75,"statusText":"已结算","bullBearType":"BEAR","baseSellCt":0.1,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":1.289,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":40,"isBearFull":false,"sellPrice":1.28652,"type":"FOREX","buyCt":0.1,"revenue":9.4,"price":1.28559,"leftAmount":781700,"id":0,"sellCt":0.1,"userRecordId":373742,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":1.29066,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"英镑兑美元","buyStatus":"SUCCESS","deposit":2.2,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"GBP.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 16:32:19","usedAmount":62200,"profitPrice":1.2865,"countText":"0.1手","sellType":"EARN","profitPercent":23.5,"startTime":"2017-06-01 05:16:00","isForce":true,"priceText":"1.28746 → 1.28652","createDate":"2017-06-01","buyPrice":1.28746,"isBegin":true,"baseBuyCt":0.1,"currentProfit":9.4,"costPrice":1.28746,"isFinish":false,"sellDealTime":"2017-06-01 17:16:08","buyAmount":200,"expEarn":1.2865,"serial":"20170601704343","createTime":"2017-06-01 16:32:18","evenUpPrice":1.29066,"revenuePercent":23.5,"statusText":"已结算","bullBearType":"BEAR","baseSellCt":0.1,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":1.289,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":0.74062,"type":"FOREX","buyCt":2,"revenue":360,"price":0.73985,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":372360,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":0.73562,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"澳元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"AUD.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 10:44:10","usedAmount":7100,"profitPrice":0.741,"countText":"2.0手","sellType":"MANUAL","profitPercent":45,"startTime":"2017-06-01 05:16:00","priceText":"0.73882 → 0.74062","createDate":"2017-06-01","buyPrice":0.73882,"isBegin":true,"baseBuyCt":2,"currentProfit":360,"costPrice":0.73882,"isFinish":false,"sellDealTime":"2017-06-01 14:42:01","buyAmount":4000,"expEarn":0.741,"serial":"20170601915049","createTime":"2017-06-01 10:44:09","evenUpPrice":0.73562,"revenuePercent":45,"statusText":"已结算","bullBearType":"BULL","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":0.74305,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":0.74063,"type":"FOREX","buyCt":2,"revenue":74,"price":0.73985,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":372197,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":0.73706,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"澳元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"AUD.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 10:11:12","usedAmount":7100,"profitPrice":0.741,"countText":"2.0手","sellType":"MANUAL","profitPercent":9.25,"startTime":"2017-06-01 05:16:00","priceText":"0.74026 → 0.74063","createDate":"2017-06-01","buyPrice":0.74026,"isBegin":true,"baseBuyCt":2,"currentProfit":74,"costPrice":0.74026,"isFinish":false,"sellDealTime":"2017-06-01 14:41:28","buyAmount":4000,"expEarn":0.741,"serial":"20170601915049","createTime":"2017-06-01 10:11:12","evenUpPrice":0.73706,"revenuePercent":9.25,"statusText":"已结算","bullBearType":"BULL","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":0.74305,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":0.74064,"type":"FOREX","buyCt":2,"revenue":-50,"price":0.73985,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":372145,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":0.73769,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"澳元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"AUD.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 10:02:43","usedAmount":7100,"profitPrice":0.741,"countText":"2.0手","sellType":"MANUAL","profitPercent":-6.25,"startTime":"2017-06-01 05:16:00","priceText":"0.74089 → 0.74064","createDate":"2017-06-01","buyPrice":0.74089,"isBegin":true,"baseBuyCt":2,"currentProfit":-50,"costPrice":0.74089,"isFinish":false,"sellDealTime":"2017-06-01 14:41:40","buyAmount":4000,"expEarn":0.741,"serial":"20170601915049","createTime":"2017-06-01 10:02:42","evenUpPrice":0.73769,"revenuePercent":-6.25,"statusText":"已结算","bullBearType":"BULL","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":0.74305,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":0.74058,"type":"FOREX","buyCt":2,"revenue":-90,"price":0.73985,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":372126,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":0.73783,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"澳元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"AUD.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 10:01:09","usedAmount":7100,"profitPrice":0.741,"countText":"2.0手","sellType":"MANUAL","profitPercent":-11.25,"startTime":"2017-06-01 05:16:00","priceText":"0.74103 → 0.74058","createDate":"2017-06-01","buyPrice":0.74103,"isBegin":true,"baseBuyCt":2,"currentProfit":-90,"costPrice":0.74103,"isFinish":false,"sellDealTime":"2017-06-01 14:41:16","buyAmount":4000,"expEarn":0.741,"serial":"20170601915049","createTime":"2017-06-01 10:01:09","evenUpPrice":0.73783,"revenuePercent":-11.25,"statusText":"已结算","bullBearType":"BULL","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":0.74305,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":1.12499,"type":"FOREX","buyCt":2,"revenue":-14,"price":1.12267,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":371994,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":1.12812,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"欧元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"EUR.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 09:39:37","usedAmount":19000,"countText":"2.0手","sellType":"MANUAL","profitPercent":-1.75,"startTime":"2017-06-01 05:16:00","priceText":"1.12492 → 1.12499","createDate":"2017-06-01","buyPrice":1.12492,"isBegin":true,"baseBuyCt":2,"currentProfit":-14,"costPrice":1.12492,"isFinish":false,"sellDealTime":"2017-06-01 10:21:05","buyAmount":4000,"serial":"20170601322743","createTime":"2017-06-01 09:39:37","evenUpPrice":1.12812,"revenuePercent":-1.75,"statusText":"已结算","bullBearType":"BEAR","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":1.1243,"ratio":5},{"currencyType":"USD","leftBearAmount":825900,"capital":800,"isBearFull":false,"sellPrice":0.74301,"type":"FOREX","buyCt":2,"revenue":146,"price":0.73985,"leftAmount":781700,"id":0,"sellCt":2,"userRecordId":371950,"sellRevokable":false,"buyRevokable":false,"isHold":true,"sysEvenUpPrice":0.74694,"market":"IDEALPRO","bearCt":0,"sellStatus":"SUCCESS","name":"澳元兑美元","buyStatus":"SUCCESS","deposit":44,"bullCt":0,"userRecordStatus":"FINISH","status":"BID","mutipler":100000,"code":"AUD.USD","settleStatus":"SUCCESS","buyDealTime":"2017-06-01 09:31:32","usedAmount":7100,"countText":"2.0手","sellType":"MANUAL","profitPercent":18.25,"startTime":"2017-06-01 05:16:00","priceText":"0.74374 → 0.74301","createDate":"2017-06-01","buyPrice":0.74374,"isBegin":true,"baseBuyCt":2,"currentProfit":146,"costPrice":0.74374,"isFinish":false,"sellDealTime":"2017-06-01 09:50:22","buyAmount":4000,"serial":"20170601915049","createTime":"2017-06-01 09:31:31","evenUpPrice":0.74694,"revenuePercent":18.25,"statusText":"已结算","bullBearType":"BEAR","baseSellCt":2,"endTime":"2017-06-02 04:50:00","isFull":false,"payStatus":"FINISH","lastPrice":0.74305,"ratio":5}]}
     * myFbbTodayRankInfo : {"map":{"amount":"$560.90","profitCt":7,"rank":5,"involveCt":10,"userName":"康**","memberId":5368,"ratio":"70.00%"}}
     */

    private double curTotalProfit;      //当天收益
    private FbbTodayItemListBean fbbTodayItemList;      //用户持股列表
    private MyFbbTodayRankInfoBean myFbbTodayRankInfo;  //用户信息

    public double getCurTotalProfit() {
        return curTotalProfit;
    }

    public void setCurTotalProfit(double curTotalProfit) {
        this.curTotalProfit = curTotalProfit;
    }

    public FbbTodayItemListBean getFbbTodayItemList() {
        return fbbTodayItemList;
    }

    public void setFbbTodayItemList(FbbTodayItemListBean fbbTodayItemList) {
        this.fbbTodayItemList = fbbTodayItemList;
    }

    public MyFbbTodayRankInfoBean getMyFbbTodayRankInfo() {
        return myFbbTodayRankInfo;
    }

    public void setMyFbbTodayRankInfo(MyFbbTodayRankInfoBean myFbbTodayRankInfo) {
        this.myFbbTodayRankInfo = myFbbTodayRankInfo;
    }

    public static class FbbTodayItemListBean {
        private List<StockItemInfo> list;

        public List<StockItemInfo> getList() {
            return list;
        }

        public void setList(List<StockItemInfo> list) {
            this.list = list;
        }

        //持股的详情
        public static class StockItemInfo  implements Serializable {
            /**
             * currencyType : USD
             * leftBearAmount : 825900
             * capital : 500
             * isBearFull : false
             * sellPrice : 1.28493
             * type : FOREX
             * buyCt : 0.5
             * revenue : 70
             * price : 1.28559
             * leftAmount : 781700
             * id : 0
             * sellCt : 0.5
             * userRecordId : 373983
             * sellRevokable : false
             * buyRevokable : false
             * isHold : true
             * sysEvenUpPrice : 1.27553
             * market : IDEALPRO
             * bearCt : 0
             * sellStatus : SUCCESS
             * name : 英镑兑美元
             * buyStatus : SUCCESS
             * deposit : 11
             * bullCt : 0
             * userRecordStatus : FINISH
             * status : BID
             * mutipler : 100000
             * code : GBP.USD
             * settleStatus : SUCCESS
             * buyDealTime : 2017-06-01 17:55:51
             * usedAmount : 62200
             * profitPrice : 1.285
             * countText : 0.5手
             * sellType : EARN
             * profitPercent : 14
             * startTime : 2017-06-01 05:16:00
             * isForce : true
             * priceText : 1.28353 → 1.28493
             * createDate : 2017-06-01
             * buyPrice : 1.28353
             * isBegin : true
             * baseBuyCt : 0.5
             * currentProfit : 70
             * costPrice : 1.28353
             * isFinish : false
             * sellDealTime : 2017-06-01 18:49:49
             * buyAmount : 1000
             * expEarn : 1.285
             * serial : 20170601704343
             * createTime : 2017-06-01 17:55:50
             * evenUpPrice : 1.27553
             * revenuePercent : 14
             * statusText : 已结算
             * bullBearType : BULL
             * baseSellCt : 0.5
             * endTime : 2017-06-02 04:50:00
             * isFull : false
             * payStatus : FINISH
             * lastPrice : 1.289
             * ratio : 2
             */

            private String currencyType;
            private int leftBearAmount;
            private float capital;
            private boolean isBearFull;
            private float sellPrice;
            private String type;
            private float buyCt;            //购买手数
            private float revenue;          //收益
            private float price;
            private int leftAmount;
            private String id;
            private float sellCt;
            private String userRecordId;            //记录id，唯一标识
            private boolean sellRevokable;
            private boolean buyRevokable;
            private boolean isHold;
            private float sysEvenUpPrice;
            private String market;
            private float bearCt;
            private String sellStatus;
            private String name;
            private String buyStatus;
            private float deposit;
            private float bullCt;
            private String userRecordStatus;        //是否持有中：DRAFT、FINISH
            private String status;
            private int mutipler;
            private String code;
            private String settleStatus;
            private String buyDealTime;         //购买成功时间，如果为空，说明正在挂单
            private int usedAmount;
            private float profitPrice;
            private String countText;
            private String sellType;            //卖出类型：MANUAL、
            private float profitPercent;        //收益占比
            private String startTime;
            private boolean isForce;
            private String priceText;
            private String createDate;
            private float buyPrice;
            private boolean isBegin;
            private float baseBuyCt;
            private float currentProfit;        //当前盈利
            private float costPrice;
            private boolean isFinish;
            private String sellDealTime;        //结算时间
            private int buyAmount;                  //购买数
            private float expEarn;
            private String serial;              //非唯一标识，存在相同
            private String createTime;              //下单时间
            private float evenUpPrice;
            private float revenuePercent;
            private String statusText;              //持仓状态:持仓中/已结算
            private String bullBearType;            //多空：BEAR、BULL
            private float baseSellCt;
            private String endTime;
            private boolean isFull;
            private String payStatus;
            private float lastPrice;
            private float ratio;

            public String getCurrencyType() {
                return currencyType;
            }

            public void setCurrencyType(String currencyType) {
                this.currencyType = currencyType;
            }

            public int getLeftBearAmount() {
                return leftBearAmount;
            }

            public void setLeftBearAmount(int leftBearAmount) {
                this.leftBearAmount = leftBearAmount;
            }

            public float getCapital() {
                return capital;
            }

            public void setCapital(float capital) {
                this.capital = capital;
            }

            public boolean isIsBearFull() {
                return isBearFull;
            }

            public void setIsBearFull(boolean isBearFull) {
                this.isBearFull = isBearFull;
            }

            public float getSellPrice() {
                return sellPrice;
            }

            public void setSellPrice(float sellPrice) {
                this.sellPrice = sellPrice;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public float getBuyCt() {
                return buyCt;
            }

            public void setBuyCt(float buyCt) {
                this.buyCt = buyCt;
            }

            public float getRevenue() {
                return revenue;
            }

            public void setRevenue(float revenue) {
                this.revenue = revenue;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public int getLeftAmount() {
                return leftAmount;
            }

            public void setLeftAmount(int leftAmount) {
                this.leftAmount = leftAmount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public float getSellCt() {
                return sellCt;
            }

            public void setSellCt(float sellCt) {
                this.sellCt = sellCt;
            }

            public String getUserRecordId() {
                return userRecordId;
            }

            public void setUserRecordId(String userRecordId) {
                this.userRecordId = userRecordId;
            }

            public boolean isSellRevokable() {
                return sellRevokable;
            }

            public void setSellRevokable(boolean sellRevokable) {
                this.sellRevokable = sellRevokable;
            }

            public boolean isBuyRevokable() {
                return buyRevokable;
            }

            public void setBuyRevokable(boolean buyRevokable) {
                this.buyRevokable = buyRevokable;
            }

            public boolean isIsHold() {
                return isHold;
            }

            public void setIsHold(boolean isHold) {
                this.isHold = isHold;
            }

            public float getSysEvenUpPrice() {
                return sysEvenUpPrice;
            }

            public void setSysEvenUpPrice(float sysEvenUpPrice) {
                this.sysEvenUpPrice = sysEvenUpPrice;
            }

            public String getMarket() {
                return market;
            }

            public void setMarket(String market) {
                this.market = market;
            }

            public float getBearCt() {
                return bearCt;
            }

            public void setBearCt(float bearCt) {
                this.bearCt = bearCt;
            }

            public String getSellStatus() {
                return sellStatus;
            }

            public void setSellStatus(String sellStatus) {
                this.sellStatus = sellStatus;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBuyStatus() {
                return buyStatus;
            }

            public void setBuyStatus(String buyStatus) {
                this.buyStatus = buyStatus;
            }

            public float getDeposit() {
                return deposit;
            }

            public void setDeposit(float deposit) {
                this.deposit = deposit;
            }

            public float getBullCt() {
                return bullCt;
            }

            public void setBullCt(float bullCt) {
                this.bullCt = bullCt;
            }

            public String getUserRecordStatus() {
                return userRecordStatus;
            }

            public void setUserRecordStatus(String userRecordStatus) {
                this.userRecordStatus = userRecordStatus;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getMutipler() {
                return mutipler;
            }

            public void setMutipler(int mutipler) {
                this.mutipler = mutipler;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getSettleStatus() {
                return settleStatus;
            }

            public void setSettleStatus(String settleStatus) {
                this.settleStatus = settleStatus;
            }

            public String getBuyDealTime() {
                return buyDealTime;
            }

            public void setBuyDealTime(String buyDealTime) {
                this.buyDealTime = buyDealTime;
            }

            public int getUsedAmount() {
                return usedAmount;
            }

            public void setUsedAmount(int usedAmount) {
                this.usedAmount = usedAmount;
            }

            public float getProfitPrice() {
                return profitPrice;
            }

            public void setProfitPrice(float profitPrice) {
                this.profitPrice = profitPrice;
            }

            public String getCountText() {
                return countText;
            }

            public void setCountText(String countText) {
                this.countText = countText;
            }

            public String getSellType() {
                return sellType;
            }

            public void setSellType(String sellType) {
                this.sellType = sellType;
            }

            public float getProfitPercent() {
                return profitPercent;
            }

            public void setProfitPercent(float profitPercent) {
                this.profitPercent = profitPercent;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public boolean isIsForce() {
                return isForce;
            }

            public void setIsForce(boolean isForce) {
                this.isForce = isForce;
            }

            public String getPriceText() {
                return priceText;
            }

            public void setPriceText(String priceText) {
                this.priceText = priceText;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public float getBuyPrice() {
                return buyPrice;
            }

            public void setBuyPrice(float buyPrice) {
                this.buyPrice = buyPrice;
            }

            public boolean isIsBegin() {
                return isBegin;
            }

            public void setIsBegin(boolean isBegin) {
                this.isBegin = isBegin;
            }

            public float getBaseBuyCt() {
                return baseBuyCt;
            }

            public void setBaseBuyCt(float baseBuyCt) {
                this.baseBuyCt = baseBuyCt;
            }

            public float getCurrentProfit() {
                return currentProfit;
            }

            public void setCurrentProfit(float currentProfit) {
                this.currentProfit = currentProfit;
            }

            public float getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(float costPrice) {
                this.costPrice = costPrice;
            }

            public boolean isIsFinish() {
                return isFinish;
            }

            public void setIsFinish(boolean isFinish) {
                this.isFinish = isFinish;
            }

            public String getSellDealTime() {
                return sellDealTime;
            }

            public void setSellDealTime(String sellDealTime) {
                this.sellDealTime = sellDealTime;
            }

            public int getBuyAmount() {
                return buyAmount;
            }

            public void setBuyAmount(int buyAmount) {
                this.buyAmount = buyAmount;
            }

            public float getExpEarn() {
                return expEarn;
            }

            public void setExpEarn(float expEarn) {
                this.expEarn = expEarn;
            }

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public float getEvenUpPrice() {
                return evenUpPrice;
            }

            public void setEvenUpPrice(float evenUpPrice) {
                this.evenUpPrice = evenUpPrice;
            }

            public float getRevenuePercent() {
                return revenuePercent;
            }

            public void setRevenuePercent(float revenuePercent) {
                this.revenuePercent = revenuePercent;
            }

            public String getStatusText() {
                return statusText;
            }

            public void setStatusText(String statusText) {
                this.statusText = statusText;
            }

            public String getBullBearType() {
                return bullBearType;
            }

            public void setBullBearType(String bullBearType) {
                this.bullBearType = bullBearType;
            }

            public float getBaseSellCt() {
                return baseSellCt;
            }

            public void setBaseSellCt(float baseSellCt) {
                this.baseSellCt = baseSellCt;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public boolean isIsFull() {
                return isFull;
            }

            public void setIsFull(boolean isFull) {
                this.isFull = isFull;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public float getLastPrice() {
                return lastPrice;
            }

            public void setLastPrice(float lastPrice) {
                this.lastPrice = lastPrice;
            }

            public float getRatio() {
                return ratio;
            }

            public void setRatio(float ratio) {
                this.ratio = ratio;
            }
        }
    }

    public static class MyFbbTodayRankInfoBean {
        /**
         * map : {"amount":"$560.90","profitCt":7,"rank":5,"involveCt":10,"userName":"康**","memberId":5368,"ratio":"70.00%"}
         */
        //用户详情:同排行榜信息
        private UserSimpleInfo map;

        public UserSimpleInfo getMap() {
            return map;
        }

        public void setMap(UserSimpleInfo map) {
            this.map = map;
        }


    }
}
