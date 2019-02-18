package com.example.testing.optimization.entity;

import android.support.v4.util.ArrayMap;

import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.TimeUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 一支股票的情况
 */

public class StockInfo extends BaseResultBean implements Comparable<StockInfo>{

    @Override
    public int compareTo(StockInfo o) {
        return (int)(o.getHotDegree() - this.getHotDegree());
    }

    //持有情况
    public static class StockBuyInfo {
        private float   revenue;   //当前盈利
        private long    buyTime;  //购买时间
        private String   buyTimeStr;    //购买时间
        private float   buyCt;      //购买手数
        private String   recordStatus;  //持有情况
        private String   bullOrBear;    //多空

        public StockBuyInfo(float revenue, String buyTimeStr, float buyCt, String bullOrBear) {
            this.revenue = revenue;
            this.buyTime = TimeUtils.getFromYMDHMS(buyTimeStr);
            this.buyTimeStr = buyTimeStr;
            this.buyCt = buyCt;
            this.bullOrBear = bullOrBear;
            this.recordStatus = InitData.RecordStatusDraft;
        }

        public float getRevenue() {
            return revenue;
        }

        public long getBuyTime() {
            return buyTime;
        }

        public float getBuyCt() {
            return buyCt;
        }

        public String getBullOrBear() {
            return bullOrBear;
        }

        public void setRevenue(float revenue) {
            this.revenue = revenue;
        }

        public String getRecordStatus() {
            return recordStatus;
        }

        public void setSellState(String recordStatus) {
            this.recordStatus = recordStatus;
        }

        public String getBuyTimeStr() {
            return buyTimeStr;
        }
    }

    private String code;        //股票码
    private String name;        //股票名
    private float  price;       //当前价
    private int   peopleCount;           //总持有人数
    private int   peopleBullCount;          //看多人数
    private int   peopleBearCount;          //看空人数
    private int   peopleBullBearCount;      //同时看多看空人数

    private int   bullCount;            //看多次数
    private int   bullProfitCount;      //看多挣钱次数
    private int   bullLossCount;        //看多亏损次数
    private float   bullRevenue;          //看多总盈利
    private float   bullProfitMoney;      //看多挣钱的金额
    private float   bullLossMoney;      //看多亏损的金额
    private float   bullBuyCt;          //看多手数

    private int   bearCount;            //看空次数
    private int   bearProfitCount;      //看空挣钱次数
    private int   bearLossCount;        //看空亏损次数
    private float   bearRevenue;          //看空总盈利
    private float   bearProfitMoney;    //看空挣钱的金额
    private float   bearLossMoney;      //看空亏损的金额
    private float   bearBuyCt;          //看空手数


    private float buyCt;        //总购买手数
    private float sellCt;       //总卖出手数
    private String bullOrBear;      //多空方向
    private float hotDegree;    //推荐度
//    private StockBuyInfo  latestBuyInfo;    //最近时间的购买
    private StockBuyInfo  latestBullInfo;    //最近看多时间的购买
    private StockBuyInfo  latestBearInfo;   //最近看空时间购买
    private Map<String, Map<String, StockBuyInfo>> buyers = new ArrayMap<>();   //购买人id、购买的记录id，是否卖出

    public StockInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void clearData() {
        peopleCount = 0;
        peopleBullCount = 0;
        peopleBearCount = 0;
        peopleBullBearCount = 0;

        bullCount = 0;
        bullProfitCount = 0;
        bullLossCount = 0;
        bullRevenue = 0;
        bullProfitMoney = 0;
        bullLossMoney = 0;
        bullBuyCt = 0;

        bearCount = 0;
        bearProfitCount = 0;
        bearLossCount = 0;
        bearRevenue = 0;
        bearProfitMoney = 0;
        bearLossMoney = 0;
        bearBuyCt = 0;

        buyCt = 0;
        sellCt = 0;


    }
    //添加看涨
    public void addBull(float buyCt) {
        this.bullCount++;
//        this.peopleCount++;
        this.bullBuyCt += buyCt;
    }

    //减少看涨
    public void minusBull(float buyCt) {
        this.bullCount--;
//        this.peopleCount--;
        this.bullBuyCt -= buyCt;
    }

    //添加看空
    public void addBear(float bearCt) {
        this.bearCount++;
//        this.peopleCount++;
        this.bearBuyCt += bearCt;
    }

    //减少看空
    public void minusBear(float bearCt) {
        this.bearCount--;
//        this.peopleCount--;
        this.bearBuyCt -= bearCt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBuyCt() {
        return buyCt;
    }

    public void addBuyCt(float buyCt) {
        this.buyCt += buyCt;
    }
    public void minusBuyCt(float buyCt) {
        this.buyCt -= buyCt;
    }
    public void setBuyCt(float buyCt) {
        this.buyCt = buyCt;
    }

    public void addSellCt(float sellCt) {
        this.sellCt += sellCt;
    }
    public void minusSellCt(float sellCt) {
        this.sellCt -= sellCt;
    }

    public float getSellCt() {
        return sellCt;
    }

    public void setSellCt(float sellCt) {
        this.sellCt = sellCt;
    }


    public int getPeopleCount() {
        return peopleCount;
    }

    public void addPeopleCount(int peopleCount) {
        this.peopleCount += peopleCount;
    }
    public void minusPeopleCount(int peopleCount) {
        this.peopleCount -= peopleCount;
    }
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getBullCount() {
        return bullCount;
    }
    public void addBullCount(int bullCount) {
        this.bullCount += bullCount;
    }
    public void minusBullCount(int bullCount) {
        this.bullCount -= bullCount;
    }
    public void setBullCount(int bullCount) {
        this.bullCount = bullCount;
    }

    public int getBearCount() {
        return bearCount;
    }
    public void addBearCount(int bearCount) {
        this.bearCount += bearCount;
    }
    public void minusBearCount(int bearCount) {
        this.bearCount -= bearCount;
    }
    public void setBearCount(int bearCount) {
        this.bearCount = bearCount;
    }

    public Map<String, Map<String, StockBuyInfo>> getBuyers() {
        return buyers;
    }

    //添加购买人及记录
    public void addBuyer(String memberId, String recordId, StockBuyInfo info) {
        if (buyers.containsKey(memberId)) {
            buyers.get(memberId).put(recordId, info);
        } else {
            Map<String, StockBuyInfo>    map = new ArrayMap<>(1);
            map.put(recordId, info);
            buyers.put(memberId, map);
        }
    }

    public void setBuyers(Map<String, Map<String, StockBuyInfo>> buyers) {
        this.buyers = buyers;
    }

    public void resetHotDegree() {
        this.hotDegree = 0;
    }

    public void setHotDegree(float hotDegree) {
        this.hotDegree = hotDegree;
    }

    public void minusHotDegree(float hotDegree) {
        this.hotDegree -= hotDegree;
    }
    public int getHotDegree() {
        return (int) hotDegree;
    }

    public String getBullOrBear() {
        return bullOrBear;
    }

    public void setBullOrBear(String bullOrBear) {
        this.bullOrBear = bullOrBear;
    }

    public float getBullProfitMoney() {
        return bullProfitMoney;
    }

    public void addBullProfitMoney(float bullProfitMoney) {
        this.bullProfitMoney += bullProfitMoney;
    }

    public float getBullLossMoney() {
        return bullLossMoney;
    }

    public void addBullLossMoney(float bullLossMoney) {
        this.bullLossMoney += bullLossMoney;
    }

    public int getBullProfitCount() {
        return bullProfitCount;
    }

    public void addBullProfitCount(int bullProfitCount) {
        this.bullProfitCount += bullProfitCount;
    }

    public int getBullLossCount() {
        return bullLossCount;
    }

    public void addBullLossCount(int bullLossCount) {
        this.bullLossCount += bullLossCount;
    }

    public float getBullRevenue() {
        return bullRevenue;
    }

    public void setBullRevenue(float bullRevenue) {
        this.bullRevenue = bullRevenue;
    }

    public int getBearProfitCount() {
        return bearProfitCount;
    }

    public void addBearProfitCount(int bearProfitCount) {
        this.bearProfitCount += bearProfitCount;
    }

    public int getBearLossCount() {
        return bearLossCount;
    }

    public void addBearLossCount(int bearLossCount) {
        this.bearLossCount += bearLossCount;
    }

    public float getBearRevenue() {
        return bearRevenue;
    }

    public void setBearRevenue(float bearRevenue) {
        this.bearRevenue = bearRevenue;
    }

    public float getBearProfitMoney() {
        return bearProfitMoney;
    }

    public void addBearProfitMoney(float bearProfitMoney) {
        this.bearProfitMoney += bearProfitMoney;
    }

    public float getBearLossMoney() {
        return bearLossMoney;
    }

    public void addBearLossMoney(float bearLossMoney) {
        this.bearLossMoney += bearLossMoney;
    }

    public float getBearBuyCt() {
        return bearBuyCt;
    }

    public void addBearBuyCt(float bearBuyCt) {
        this.bearBuyCt += bearBuyCt;
    }

    public float getBullBuyCt() {
        return bullBuyCt;
    }

    public void addBullBuyCt(float bullBuyCt) {
        this.bullBuyCt += bullBuyCt;
    }

    public int getPeopleBullCount() {
        return peopleBullCount;
    }

    public void addPeopleBullCount(int peopleBullCount) {
        this.peopleBullCount += peopleBullCount;
    }

    public int getPeopleBearCount() {
        return peopleBearCount;
    }

    public void addPeopleBearCount(int peopleBearCount) {
        this.peopleBearCount += peopleBearCount;
    }

    public int getPeopleBullBearCount() {
        return peopleBullBearCount;
    }

    public void addPeopleBullBearCount(int peopleBullBearCount) {
        this.peopleBullBearCount += peopleBullBearCount;
    }

//    public StockBuyInfo getLatestBuyInfo() {
//        return latestBuyInfo;
//    }
//
//    public void setLatestBuyInfo(StockBuyInfo latestBuyInfo) {
//        this.latestBuyInfo = latestBuyInfo;
//    }


    public StockBuyInfo getLatestBullInfo() {
        return latestBullInfo;
    }

    public void setLatestBullInfo(StockBuyInfo latestBullInfo) {
        this.latestBullInfo = latestBullInfo;
    }

    public StockBuyInfo getLatestBearInfo() {
        return latestBearInfo;
    }

    public void setLatestBearInfo(StockBuyInfo latestBearInfo) {
        this.latestBearInfo = latestBearInfo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
