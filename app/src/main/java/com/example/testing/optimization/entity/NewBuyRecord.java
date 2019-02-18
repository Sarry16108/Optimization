package com.example.testing.optimization.entity;

/**
 * Created by Administrator on 2017/6/6.
 */

public class NewBuyRecord {
    private PersonalPub.FbbTodayItemListBean.StockItemInfo   stockInfo;
    private UserSimpleInfo  personInfo;

    public NewBuyRecord(PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo, UserSimpleInfo personInfo) {
        this.stockInfo = stockInfo;
        this.personInfo = personInfo;
    }

    public PersonalPub.FbbTodayItemListBean.StockItemInfo getStockInfo() {
        return stockInfo;
    }

    public UserSimpleInfo getPersonInfo() {
        return personInfo;
    }
}
