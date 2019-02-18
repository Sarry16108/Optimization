package com.example.testing.optimization.databindings;

import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.StockInfo;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.TimeUtils;
import com.example.testing.optimization.utils.ToolLog;

/**
 * Created by Administrator on 2017/6/5.
 */

public class DatabindingUtls {
    private final static String TAG = "DatabindingUtls";


    //推荐首页code+关注度
    public static String getRecommandCodeInfo(StockInfo stockInfo) {
        return stockInfo.getCode() + "    关注度:" + stockInfo.getPeopleCount()/* + "\n最近时间：" + stockInfo.getLatestBuyInfo().getBuyTimeStr()*/;
    }

    //推荐首页：看多
    public static String getBullData(StockInfo stockInfo) {
        StringBuilder builder = new StringBuilder("看多(" + stockInfo.getBullBuyCt() + "手)");
        if (null != stockInfo.getLatestBullInfo()) {
            builder.append("            " + stockInfo.getLatestBullInfo().getBuyTimeStr() + "\n");
        } else {
            builder.append("\n");
        }

        builder.append("比数：" + stockInfo.getBullCount() + "比(盈利：" + stockInfo.getBullProfitCount() + "比 亏损：" + stockInfo.getBullLossCount() + "比)\n");
        builder.append("金额：" + stockInfo.getBullRevenue() + "(盈利：" + stockInfo.getBullProfitMoney() + " 亏损：" + stockInfo.getBullLossMoney() + ")");

        return builder.toString();
    }


    public static String getBearData(StockInfo stockInfo) {
        StringBuilder builder = new StringBuilder("看空(" + stockInfo.getBearBuyCt() + "手)");
        if (null != stockInfo.getLatestBearInfo()) {
            builder.append("            " + stockInfo.getLatestBearInfo().getBuyTimeStr() + "\n");
        } else {
            builder.append("\n");
        }
        builder.append("比数：" + stockInfo.getBearCount() + "比(盈利：" + stockInfo.getBearProfitCount() + "比 亏损：" + stockInfo.getBearLossCount() + "比)\n");
        builder.append("金额：" + stockInfo.getBearRevenue() + "(盈利：" + stockInfo.getBearProfitMoney() + " 亏损：" + stockInfo.getBearLossMoney() + ")");

        return builder.toString();
    }


    public static String getBuyDetail(UserSimpleInfo personInfo, PersonalPub.FbbTodayItemListBean.StockItemInfo itemInfo) {
        StringBuilder builder = new StringBuilder("\n");
        FormatNewlineItem(builder, "用户名", personInfo.getUserName(), " ——:", personInfo.getMemberId());  //// TODO: 2017/6/27 id不展示
        FormatNewlineItem(builder, "排名第", personInfo.getRank());
        FormatNewlineItem(builder, "总收益", personInfo.getAmount());
        FormatNewlineItem(builder, "成功率", personInfo.getRatio(), "盈利", personInfo.getProfitCt(), "总数", personInfo.getInvolveCt());
        builder.append("\n");
        FormatNewlineItem(builder, "种类", itemInfo.getName(), "代码", itemInfo.getCode());
        FormatNewlineItem(builder, "多空类型", InitData.BuyTypeBull.equals(itemInfo.getBullBearType()) ? "看多" : "看空");
        FormatNewlineItem(builder, "收益：", itemInfo.getCurrentProfit());
        FormatNewlineItem(builder, "数量", itemInfo.getCountText());
        FormatNewlineItem(builder, "购买时间", itemInfo.getCreateTime());
        FormatNewlineItem(builder, "成交时间", itemInfo.getBuyDealTime());
        FormatNewlineItem(builder, "购买价", itemInfo.getBuyPrice());
        FormatNewlineItem(builder, "成本价", itemInfo.getCostPrice());
        FormatNewlineItem(builder, "类型", itemInfo.getType());
        FormatNewlineItem(builder, "结束时间", itemInfo.getSellDealTime());
        FormatNewlineItem(builder, "卖出价", itemInfo.getSellPrice());
        FormatNewlineItem(builder, "收益总占比", itemInfo.getProfitPercent() + '%');
        FormatNewlineItem(builder, "记录id", itemInfo.getUserRecordId());   //// TODO: 2017/6/27 没必要展示

        return builder.toString();
    }

    private static void FormatNewlineItem(StringBuilder builder, String tip, String value) {
        if (null == value) {
            value = "";
        }
        builder.append(tip + " :  " + value + '\n');
    }

    private static void FormatNewlineItem(StringBuilder builder, String tip, float value) {
        builder.append(tip + " :  " + value + '\n');
    }

    private static void FormatNewlineItem(StringBuilder builder, String tip, int value) {
        builder.append(tip + " :  " + value + '\n');
    }

    private static void FormatNewlineItem(StringBuilder builder, Object ...values) {
        if (0 == values.length || 0 != (values.length % 2)) {
            ToolLog.e(TAG, "FormatNewlineItem", "values is error :" + values.toString());
            return;
        }

        for (int i = 0; i < values.length; i+=2) {
            if (null == values[i + 1]) {
                values[i + 1] = "";
            }
            builder.append(values[i] + " :  " + values[i + 1] + "  ");
        }
        builder.append('\n');
    }

    public static String getRecordItemName(UserSimpleInfo personInfo) { //todo:memberid去掉
        return personInfo.getUserName() + "(排名第" + personInfo.getRank() + ", Id=" + personInfo.getMemberId() +")";
    }

    public static String getRecordItemContent(PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo) {
        return (InitData.BuyTypeBull.equals(stockInfo.getBullBearType()) ? "《看多》" : "《看空》--") + stockInfo.getName() + stockInfo.getCountText()
                + "\n购买价:" + stockInfo.getBuyPrice() + "  收益：" + stockInfo.getCurrentProfit();
    }

    public static String getRecordItemTime(PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo) {
        if (InitData.RecordStatusDraft.equals(stockInfo.getUserRecordStatus())) {
            return "买 " + stockInfo.getBuyDealTime();
        } else {
            return "卖 " + stockInfo.getSellDealTime();
        }
    }


    public static String getStartStopText(boolean isRunning) {
        return isRunning ? "结束" : "开始";
    }

    public static String getRankInfo(int rank, UserSimpleInfo personInfo) { //todo:memberid去掉
        return rank + "      " + personInfo.getUserName() + "(" + personInfo.getMemberId() + ")       " + personInfo.getAmount() + "    " + personInfo.getRatio();
    }


    public static String getMemberItemName(PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append(InitData.BuyTypeBull.equals(stockInfo.getBullBearType()) ? "《看多》" : "《看空》");
        builder.append("       ($" + stockInfo.getCapital() + ")\n");
        builder.append(stockInfo.getName());
        builder.append("  (" + stockInfo.getCode() + ")");

        return builder.toString();
    }

    public static String getMemberItemCountPrice(String count, String price) {
        return "手数：" + count + "\r\r\r价格：" + price;
    }

    public static String getRankType(int rankType) {
        StringBuilder builder = new StringBuilder("[");
        switch (rankType) {
            case 1:
                builder.append("周");
                break;
            case 2:
                builder.append("月");
                break;
            case 3:
                builder.append("周,月");
                break;
            case 4:
                builder.append("30");
                break;
            case 5:
                builder.append("周,30");
                break;
            case 6:
                builder.append("月,30");
                break;
            case 7:
                builder.append("周,月,30");
                break;
        }

        return builder.append("]").toString();
    }

    public static String getLastUpdateTime(long time) {
        return "最近更新时间:" + TimeUtils.getMDHMS(time);
    }
}
