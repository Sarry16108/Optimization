package com.example.testing.optimization.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.example.testing.optimization.BR;
import com.example.testing.optimization.R;
import com.example.testing.optimization.ToolRequest;
import com.example.testing.optimization.adapters.BaseViewpagerAdapter;
import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.base.BaseFragment;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.databinding.ActMainBinding;
import com.example.testing.optimization.dialogs.DialogUtils;
import com.example.testing.optimization.entity.BaseResultBean;
import com.example.testing.optimization.entity.NetCallback;
import com.example.testing.optimization.entity.NewBuyRecord;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.RankInfo;
import com.example.testing.optimization.entity.StockInfo;
import com.example.testing.optimization.entity.UserImg;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.fragments.NewBuyFrag;
import com.example.testing.optimization.fragments.RecommandFrag;
import com.example.testing.optimization.fragments.RevenueRankFrag;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.globaldata.InitNetInfo;
import com.example.testing.optimization.globaldata.MessageId;
import com.example.testing.optimization.utils.FileUtils;
import com.example.testing.optimization.utils.GsonUtils;
import com.example.testing.optimization.utils.ImageUtils;
import com.example.testing.optimization.utils.NetUtils;
import com.example.testing.optimization.utils.NotificationUtils;
import com.example.testing.optimization.utils.PermissionsCheckerUtils;
import com.example.testing.optimization.utils.RingtoneUtls;
import com.example.testing.optimization.utils.SharePreUtils;
import com.example.testing.optimization.utils.ToastUtils;
import com.example.testing.optimization.utils.ToolLog;
import com.example.testing.optimization.utils.UserCheckUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainAct extends BaseActivity implements View.OnClickListener, NetCallback {

    private ActMainBinding mActBinding;
    private Timer           mTimer;     //排行榜定时
    private Thread          mPersonThread;  //个人信息线程
    private long            mRefreshDelay = 5 * 1000;      //刷新频率
    private int             mUpdateSelfInfoTimes = 0;       //个人信息获取次数

    //排行榜人员列表
    private Map<String, UserSimpleInfo> mRankInfos = new ArrayMap<>(15);        //当天排行榜人员

    //个人信息
    private Map<String, PersonalPub.FbbTodayItemListBean.StockItemInfo> mPersonalInfos = new ArrayMap<>(15);
    //推荐股票持仓情况
    private Map<String, StockInfo>  mStockInfos = new ArrayMap<>(10);
    //排序过的股票信息
    private List<StockInfo> mOrderedStockInfos = new ArrayList<>(10);
    //买卖记录
    private List<NewBuyRecord>  mNewBuyRecords = new ArrayList<>(20);

    private int mAnalyzedPersonCount = 0;   //已分析的人数，减少排序及界面刷新频率
    private int mUpdateTimes = 0;           //排行榜获取次数

    //几个子页面
    private RecommandFrag mRecommandFra;
    private NewBuyFrag mNewBuyFra;
    private RevenueRankFrag mRevenueRankFrag;
    private Dialog          mDialog;
    private boolean         mIsRunning  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActBinding = (ActMainBinding)DataBindingUtil.setContentView(this, R.layout.act_main);

        initFilePath();
        initView();
        initDataBind();
        if (ToolLog.isDebug) {
//            testInfo();
        }
    }

    private void testInfo() {
        mRankInfos.put("26582", new UserSimpleInfo("26582", "$999", 1, "3", 9, "test", "35%"));
    }


    private void initView() {
        mRecommandFra = new RecommandFrag();
        mNewBuyFra = new NewBuyFrag();
        mRevenueRankFrag = new RevenueRankFrag();

        List<BaseFragment> fragments = new ArrayList<>(3);
        fragments.add(0, mRecommandFra);
        fragments.add(1, mNewBuyFra);
        fragments.add(2, mRevenueRankFrag);

        mActBinding.dataContainer.setOffscreenPageLimit(3);
        mActBinding.dataContainer.setAdapter(new BaseViewpagerAdapter<BaseFragment>(getSupportFragmentManager(), fragments));
        mActBinding.dataContainer.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (0 == position || 2 == position) {
                    packMsgAndSend(MessageId.AMOUNT_UPDATE, mRankInfos.size());
                } else {
                    packMsgAndSend(MessageId.AMOUNT_UPDATE, mNewBuyRecords.size());
                }
            }
        });
        mHandler.sendEmptyMessageDelayed(MessageId.INIT_DATA, 1500);
    }

    private void initRequestServer() {
        if (!UserCheckUtils.isRight()) {
            ToastUtils.shortToast(this, "设备信息不匹配");
            finish();
            return;
        }

        if (TextUtils.isEmpty(BaseApplication.mUserLoginInfo.getUsername()) || TextUtils.isEmpty(BaseApplication.mUserLoginInfo.getPassword())) {
            mHandler.sendEmptyMessage(MessageId.ACCOUNT_LOGIN);
        } else {
            //过期了就重新登录
            if (BaseApplication.mUserLoginInfo.getExpires() < System.currentTimeMillis()) {
                ToolRequest.getInstance().getLoginByAccount(this, BaseApplication.mUserLoginInfo.getUsername(), BaseApplication.mUserLoginInfo.getPassword());
            } else {
                ToolRequest.getInstance().getLoginInfo();
            }
        }
    }

    private void initFilePath() {
        PermissionsCheckerUtils utils = new PermissionsCheckerUtils();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && utils.checkImportantPermissions()) {
            utils.requestPermissions(this);
        } else {
            FileUtils.initAppDir();
            initRequestServer();
        }

    }

    private void initDataBind() {
        mActBinding.setVariable(BR.onBindClick, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTimer:
                if (!BaseApplication.mIsLogined) {
                    mHandler.sendEmptyMessage(MessageId.ACCOUNT_LOGIN);
                    return;
                }
                if (!mIsRunning) {
                    startTimer();
                    mActBinding.setVariable(BR.isRunning, true);
                } else {
                    stopRefresh();
                    mActBinding.setVariable(BR.isRunning, false);
                }
                mIsRunning = !mIsRunning;
        }
    }
    private void startTimer() {
        if (null != mTimer) {
            return;
        }

        //排行榜两分钟刷一次，个人信息5s一次
        //两分钟会刷新几次
        final int secondTimes = (int) (120 * 1000 / mRefreshDelay);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                if (NetUtils.checkNetwork()) {
                    if (mActBinding.netState.isSelected()) {
                        mRefreshDelay = 5 * 1000;
                        Message message = Message.obtain();
                        message.what = MessageId.REFRESH_NETWORK_STATE;
                        message.obj = false;
                        mHandler.sendMessage(message);
                    }

                    if (0 == mUpdateSelfInfoTimes % secondTimes) {  //
                        ToolLog.d("interestInfo 排行榜：" + mUpdateSelfInfoTimes);
                        startRefresh();
                    }

                    ToolLog.d("interestInfo 个人信息：" + mUpdateSelfInfoTimes);
                    callRankPerson();
                    mUpdateSelfInfoTimes++;
                } else {
                    if (!mActBinding.netState.isSelected()) {
                        mRefreshDelay = 60 * 1000;
                        Message message = Message.obtain();
                        message.what = MessageId.REFRESH_NETWORK_STATE;
                        message.obj = true;
                        mHandler.sendMessage(message);
                    }
                }
            }
        }, 1000, mRefreshDelay);
    }


    private void stopRefresh() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
    }

    private void startRefresh() {
        ToolRequest.getInstance().getRankInfo(InitData.RankTypeRevenue, InitData.TimeTypeDate, this);
    }

    @Override
    public void onSuccess(String method, BaseResultBean data) {
        if (null == data) {
            ToolLog.e(TAG, "onSuccess", method,  "data is null");
            return;
        }

        switch (method) {
            case InitNetInfo.MethodRankInfo:
                RankInfo rankInfo = (RankInfo)data;
                saveNewInfo(rankInfo);
                break;
            case InitNetInfo.MethodPersonPubInfo:
                PersonalPub personalPub = (PersonalPub) data;
                analyzeStockBuy(personalPub);
                break;
        }
    }

    @Override
    public void onError(String method, int connCode, String data) {
        ToolLog.e(TAG, "onError", method, connCode + "data is " + (null != data ? data : "null"));
    }

    private synchronized void saveNewInfo(RankInfo rankInfo) {
        mHandler.sendEmptyMessage(MessageId.REFRESH_TIMES);
        if (null == rankInfo || null == rankInfo.getTotalRankInfo() || null == rankInfo.getTotalRankInfo().getList()) {
            ToolLog.e(TAG, "saveNewInfo", "rank list is null");
            return;
        }


        for (UserSimpleInfo item : rankInfo.getTotalRankInfo().getList()) {

            //包含过滤的人，则不再请求，显示在列表中
            if (BaseApplication.mFilterId.contains(item.getMemberId())) {
                continue;
            }

            //不重复设置图片信息及请求
            if (BaseApplication.mSavedUserImgs.containsKey(item.getMemberId())) {
                if (!mRankInfos.containsKey(item.getMemberId())) {
                    UserImg beforeInfo = BaseApplication.mSavedUserImgs.get(item.getMemberId());
                    item.setUserName(beforeInfo.getNickname());
                    item.setHeadImg(beforeInfo.getHeadImg());
                    item.setInteresting(beforeInfo.isInterest());

                    //是否上过历史排行榜
                    if (BaseApplication.mRankLongPeriod.containsKey(item.getMemberId())) {
                        item.setRankType(BaseApplication.mRankLongPeriod.get(item.getMemberId()).getRankType());
                    }
                } else {
                    //本地未存储过这个人的头像，刚下载好
                    if (null == item.getHeadImg()) {
                        UserImg beforeInfo = BaseApplication.mSavedUserImgs.get(item.getMemberId());
                        item.setUserName(beforeInfo.getNickname());
                        item.setHeadImg(beforeInfo.getHeadImg());

                        //是否上过历史排行榜
                        if (BaseApplication.mRankLongPeriod.containsKey(item.getMemberId())) {
                            item.setRankType(BaseApplication.mRankLongPeriod.get(item.getMemberId()).getRankType());
                        }
                    }
                }
            } else {
                ToolRequest.getInstance().getMemberImgInfo(item.getMemberId());
            }

            //之前不存在则加入，存在的则更新数据
            item.resetUpdated();    //清空更新过的标记
            //关注标识，用于排行榜显示
            item.setInteresting(BaseApplication.mIntrestUsers.containsKey(item.getMemberId()));

            mRankInfos.put(item.getMemberId(), item);
        }

        //及时更新，不判断当前页是否在显示
        int position = mActBinding.dataContainer.getCurrentItem();
        if (0 == position || 2 == position) {
            packMsgAndSend(MessageId.AMOUNT_UPDATE, mRankInfos.size());
        } else {
            packMsgAndSend(MessageId.AMOUNT_UPDATE, mNewBuyRecords.size());
        }

        //刷新收益排行榜：找不到的那些人，收益降为0
        for (UserSimpleInfo userSimpleInfo : BaseApplication.mIntrestUsers.values()) {
            if (!userSimpleInfo.isUpdated()) {
                userSimpleInfo.setAmount("$0");  //获取不到人员信息，清零收益
            }
        }

        //刷新排行榜
        mHandler.sendEmptyMessage(MessageId.UPDATE_REVENUE_RANK);
    }

    //请求个人信息并处理排序，最后更新
    private void callRankPerson() {
        if (0 == BaseApplication.mIntrestUsers.size()) {
            return;
        }

        mAnalyzedPersonCount = 0;
        mPersonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (UserSimpleInfo simpleInfo : BaseApplication.mIntrestUsers.values()) {
                    //停止就不再进行了
                    if (!mIsRunning || null == simpleInfo) {
                        return;
                    }

                    //重置获取信息成功标识
                    simpleInfo.resetUpdated();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ToolRequest.getInstance().getMemberPub(simpleInfo.getMemberId(), InitData.ContainAll, MainAct.this);
                }
            }
        });
        mPersonThread.start();
    }

    //分析整理
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public synchronized void analyzeStockBuy(PersonalPub personalPub) {
        if (null == personalPub || null == personalPub.getFbbTodayItemList() || null == personalPub.getFbbTodayItemList().getList()) {
            ToolLog.e(TAG, "analyzeStockBuy", "person buy list is null");
            recordAnalyzedCound();  //必须加上，否则会因为列表为空时候，次数记录异常，界面刷新不了
            return;
        }

        UserSimpleInfo personInfo = personalPub.getMyFbbTodayRankInfo().getMap();
        String memberId = personInfo.getMemberId();

        //排行榜中这个人数据更新
        UserSimpleInfo tmpPersonInfo = mRankInfos.get(memberId);
        personInfo.setUpdated();    //该次已更新标记
        personInfo.setLastUpdateTime(System.currentTimeMillis());   //最近更新时间
        personInfo.setHeadImg(tmpPersonInfo.getHeadImg());
        personInfo.setInteresting(tmpPersonInfo.isInteresting());
        personInfo.setUserName(tmpPersonInfo.getUserName());      //todo:不显示真实昵称
        personInfo.setRankType(tmpPersonInfo.getRankType());
        mRankInfos.put(memberId, personInfo);
        BaseApplication.mMemberDetails.put(memberId, personalPub.getFbbTodayItemList().getList());  //已有则更新数据

        //遍历个人选购列表
        for (PersonalPub.FbbTodayItemListBean.StockItemInfo item : personalPub.getFbbTodayItemList().getList()) {

            //推荐列表已经包含该code，说明已经购买了
            if (mStockInfos.containsKey(item.getCode())) {
                StockInfo stockInfo = mStockInfos.get(item.getCode());
                stockInfo.setPrice(item.getPrice());

                //这个人已经购买了这个code
                if (stockInfo.getBuyers().containsKey(memberId)) {
                    //持有的人的情况
                    Map<String, StockInfo.StockBuyInfo> buyer = stockInfo.getBuyers().get(memberId);

                    //code是否添加过
                    if (buyer.containsKey(item.getUserRecordId())) {  //购买项已存在于列表中，更新信息
                        StockInfo.StockBuyInfo stockBuyInfo = buyer.get(item.getUserRecordId());
                        if (InitData.RecordStatusFinish.equals(item.getUserRecordStatus())) {   //这个人已卖出
                            packMsgAndSend(MessageId.ADD_NEW_RECORD, item, personInfo, false);
                            buyer.remove(item.getUserRecordId());
                            if (0 == buyer.size()) {
                                stockInfo.getBuyers().remove(memberId);
                            }
                        } else {    //刷新信息
                            stockBuyInfo.setRevenue(item.getCurrentProfit());
                            buyer.put(item.getUserRecordId(), stockBuyInfo);
                            stockInfo.getBuyers().put(memberId, buyer);
                        }

                    } else {    //购买项不存在列表中，这人又买了
//                        ToolLog.i(TAG, "analyzeStockBuy", memberId + " ：(" + item.getBullBearType() +")"+item.getName());
                        if (InitData.RecordStatusDraft.equals(item.getUserRecordStatus()) && !TextUtils.isEmpty(item.getBuyDealTime())) {   //持有中
                            packMsgAndSend(MessageId.ADD_NEW_RECORD, item, personInfo, true);
                        } else {    //已经结束的就不管了
                            continue;
                        }

                        buyer.put(item.getUserRecordId(), new StockInfo.StockBuyInfo(item.getCurrentProfit(), item.getBuyDealTime(), item.getBuyCt(), item.getBullBearType()));
                        stockInfo.getBuyers().put(memberId, buyer);
                    }

                } else {    //首次购买该code的人
                    if (InitData.RecordStatusDraft.equals(item.getUserRecordStatus())) {   //这个人买了
                        packMsgAndSend(MessageId.ADD_NEW_RECORD, item, personInfo, true);
                    } else {
                        continue;
                    }

                    Map<String, StockInfo.StockBuyInfo> buyer = new ArrayMap<>(1);
                    buyer.put(item.getUserRecordId(), new StockInfo.StockBuyInfo(item.getCurrentProfit(), item.getBuyDealTime(), item.getBuyCt(), item.getBullBearType()));
                    stockInfo.getBuyers().put(memberId, buyer);
                }
                mStockInfos.put(item.getCode(), stockInfo);

            } else {    //首次购买该code的人
                //已经结束的不加入
                if (InitData.RecordStatusFinish.equals(item.getUserRecordStatus())) {
                    continue;
                }

                //通知有新买的
                packMsgAndSend(MessageId.ADD_NEW_RECORD, item, personInfo, true);

                //新买记录
                StockInfo stockInfo = new StockInfo(item.getCode(), item.getName());
                stockInfo.addBuyer(memberId, item.getUserRecordId(), new StockInfo.StockBuyInfo(item.getCurrentProfit(),
                        item.getBuyDealTime(), item.getBuyCt(), item.getBullBearType()));
                mStockInfos.put(item.getCode(), stockInfo);
            }
        }

        recordAnalyzedCound();
    }

    private void setRankTypeAndUserImg(UserSimpleInfo personInfo) {
        if (BaseApplication.mSavedUserImgs.containsKey(personInfo.getMemberId())) {
            UserImg beforeInfo = BaseApplication.mSavedUserImgs.get(personInfo.getMemberId());
            personInfo.setUserName(beforeInfo.getNickname());     //todo:
            personInfo.setHeadImg(beforeInfo.getHeadImg());

            //是否上过历史排行榜
            if (BaseApplication.mRankLongPeriod.containsKey(personInfo.getMemberId())) {
                personInfo.setRankType(BaseApplication.mRankLongPeriod.get(personInfo.getMemberId()).getRankType());
            }
        }
    }

    private void packMsgAndSend(int what, PersonalPub.FbbTodayItemListBean.StockItemInfo stockItemInfo, UserSimpleInfo userSimpleInfo, boolean isBuy) {
        //设置历史排行、名字，头像
        setRankTypeAndUserImg(userSimpleInfo);

        //发送通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (isBuy) {
                NotificationUtils.getInstance().showNotification(userSimpleInfo.getUserName(), userSimpleInfo.getRank(), stockItemInfo.getBuyCt(),
                        stockItemInfo.getBullBearType(), stockItemInfo.getName(), true, stockItemInfo.getBuyPrice());
            } else {    //卖出
                RingtoneUtls.playRing();
                NotificationUtils.getInstance().showNotification(userSimpleInfo.getUserName(), userSimpleInfo.getRank(), stockItemInfo.getSellCt(),
                        stockItemInfo.getBullBearType(), stockItemInfo.getName(), false, stockItemInfo.getSellPrice());
            }
        }

        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = new NewBuyRecord(stockItemInfo, userSimpleInfo);
        mHandler.sendMessage(msg);
        if (1 == mActBinding.dataContainer.getCurrentItem()) {
            packMsgAndSend(MessageId.AMOUNT_UPDATE, mNewBuyRecords.size());
        }
    }

    //当排行列表中人都分析过了，排序刷新
    private void recordAnalyzedCound() {
        mAnalyzedPersonCount++;
        if (mAnalyzedPersonCount == BaseApplication.mIntrestUsers.size()) {
            sortByHotDegree2();
        }
    }

    //计算并排序
    private void sortByHotDegree2() {
        //计算热度
        Iterator<Map.Entry<String, StockInfo>> iterator = mStockInfos.entrySet().iterator();
        while (iterator.hasNext()) {
            StockInfo stockInfo = iterator.next().getValue();

            //返回false，说明没人买这个股票，如果没有人买了就把这条给移除
            if (!resetStockInfos(stockInfo)) {
                iterator.remove();
            }
        }

        //排序刷新热度
        mOrderedStockInfos.clear();
        mOrderedStockInfos.addAll(mStockInfos.values());
        Collections.sort(mOrderedStockInfos);
        mHandler.sendEmptyMessage(MessageId.REFRESH_HOTDEGREE_LIST);
    }

    //重新计算mStockInfos的数据
    private boolean resetStockInfos(StockInfo stockInfo) {
        if (null == stockInfo) {
            return false;
        }

        stockInfo.clearData();
        float revenue = 0;
        boolean buyBull = false, buyBear = false;
        long latestBullTime = 0, latestBearTime = 0;
        Iterator<Map.Entry<String, Map<String, StockInfo.StockBuyInfo>>> iterator = stockInfo.getBuyers().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, StockInfo.StockBuyInfo>> item = iterator.next();

            //没有更新过，跳过对它的统计,如果删除则使用iterator的remove操作
            UserSimpleInfo userSimpleInfo = mRankInfos.get(item.getKey());
            if (!userSimpleInfo.isUpdated()) {
                iterator.remove();
                continue;
            }

            //这人当前持有的
            for (StockInfo.StockBuyInfo buyInfo : item.getValue().values()) {
                //最近购买情况

                revenue = buyInfo.getRevenue();
                buyBull = buyBear = false;
//                ToolLog.i("当前盈利----id：" + item.getKey() + "(" + userSimpleInfo.getUserName() + ") " + buyInfo.getBullOrBear() + " " + stockInfo.getName() + "  revenue:" + revenue);

                //看多的
                if (InitData.BuyTypeBull.equals(buyInfo.getBullOrBear())) {
                    buyBull = true;
                    if (0 < revenue) {
                        stockInfo.addBullProfitMoney(revenue);
                        stockInfo.addBullProfitCount(1);
                    } else {
                        stockInfo.addBullLossMoney(revenue);
                        stockInfo.addBullLossCount(1);
                    }
                    stockInfo.addBullBuyCt(buyInfo.getBuyCt());
                    stockInfo.addBullCount(1);
                    if (buyInfo.getBuyTime() > latestBullTime) {
                        stockInfo.setLatestBullInfo(buyInfo);
                    }
                } else {    //看空的
                    buyBear = true;
                    if (0 < revenue) {
                        stockInfo.addBearProfitMoney(revenue);
                        stockInfo.addBearProfitCount(1);
                    } else {
                        stockInfo.addBearLossMoney(revenue);
                        stockInfo.addBearLossCount(1);
                    }
                    stockInfo.addBearBuyCt(buyInfo.getBuyCt());
                    stockInfo.addBearCount(1);
                    if (buyInfo.getBuyTime() > latestBearTime) {
                        stockInfo.setLatestBearInfo(buyInfo);
                    }
                }
            }

            //看多、看空、同时看多空
            if (buyBull && buyBear) {
                stockInfo.addPeopleBullBearCount(1);
            } else if (buyBull) {
                stockInfo.addPeopleBullCount(1);
            } else {
                stockInfo.addPeopleBearCount(1);
            }
        }

        //表明这支股票已经没有人买了
        if (0 == stockInfo.getBuyers().size()) {
            return false;
        }

        stockInfo.setPeopleCount(stockInfo.getBuyers().size());
        stockInfo.setBuyCt(stockInfo.getBullBuyCt() + stockInfo.getBearBuyCt());
//        stockInfo.setSellCt();
        stockInfo.setBullRevenue(stockInfo.getBullProfitMoney() + stockInfo.getBullLossMoney());
        stockInfo.setBearRevenue(stockInfo.getBearProfitMoney() + stockInfo.getBearLossMoney());


        //计算热度:(多总收益/多总手数 -  空总收益/空总手数)绝对值
        float hotDegree = stockInfo.getPeopleBullCount() > stockInfo.getPeopleBearCount() ? stockInfo.getPeopleBullCount() : stockInfo.getPeopleBearCount();
        stockInfo.setHotDegree(hotDegree);

        return true;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (super.handleMessage(msg)) {
            return true;
        }

        switch (msg.what) {
            case MessageId.INIT_DATA:
                mRecommandFra.initData(this, mOrderedStockInfos);
                mNewBuyFra.initData(this, mNewBuyRecords);
                mRevenueRankFrag.initData(this);
                break;
            case MessageId.REFRESH_HOTDEGREE_LIST:
                mRecommandFra.update();
                break;
            case MessageId.REFRESH_TIMES:
                mActBinding.setVariable(BR.updateTimes, ++mUpdateTimes);
                break;
            case MessageId.REFRESH_NETWORK_STATE:
                mActBinding.netState.setSelected((Boolean) msg.obj);
                break;
            case MessageId.ADD_NEW_RECORD:
                mNewBuyFra.addNewRecord((NewBuyRecord) msg.obj);
                break;
            case MessageId.AMOUNT_UPDATE:       //人数/记录条数刷新
                mActBinding.setVariable(BR.amount, (int)msg.obj);
                break;
            case MessageId.UPDATE_REVENUE_RANK:
                mRevenueRankFrag.updateData(mRankInfos.values());
                break;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {   //给权限就保存本地，否则不缓存
//            return;
//        }

        if (MessageId.RequestMainPermission == requestCode) {
            FileUtils.initAppDir();
            initRequestServer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        //保存排行榜及用户头像，昵称等
        String periodRank = GsonUtils.castMapToJson(BaseApplication.mRankLongPeriod);
        String userImg = GsonUtils.castMapToJson(BaseApplication.mSavedUserImgs);

        SharePreUtils.putString(InitData.SpKeyPerioRank, periodRank);
        SharePreUtils.putString(InitData.SpKeyUserImg, userImg);

        ToolLog.i("保存数据完成-----排行榜：" + BaseApplication.mRankLongPeriod.size() + "  images:" + BaseApplication.mSavedUserImgs.size());
    }

    @Override
    public void onBackPressed() {

    }
}
