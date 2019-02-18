package com.example.testing.optimization.entity;

import com.example.testing.optimization.globaldata.InitAppConstant;


import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 * 用户简单信息
 */
public class UserSimpleInfo   implements Serializable, Comparable<UserSimpleInfo> {
    /**
     * amount : $560.90
     * profitCt : 7
     * rank : 5
     * involveCt : 10
     * userName : 康**
     * memberId : 5368
     * ratio : 70.00%
     */
    private String memberId;
    private String amount;
    private String credentialId;    //凭证id
    private String mobile;      //电话号码
    private String rank;
    private String userName;
    private String ratio;
    private String headImg;
    private float profitCt;
    private float involveCt;
    private int    rankType;    //进入的排行榜类型：InitAppConstant.RankTypeWeek
    private boolean isUpdated;  //上次是否更新，如果更新则计入有效，否则不计入推荐、排序后置。请求排行榜后重置false，请求到个人信息置true，计算热度时候判断true才进行计算。
    private long  lastUpdateTime;     //最后一次更新这个人信息的时间
    private boolean isInteresting;      //true：关注、感兴趣

    public UserSimpleInfo(String memberId, String amount, float profitCt,
            String rank, float involveCt, String userName, String ratio) {
        this.memberId = memberId;
        this.amount = amount;
        this.profitCt = profitCt;
        this.rank = rank;
        this.involveCt = involveCt;
        this.userName = userName;
        this.ratio = ratio;

    }

    public UserSimpleInfo() {
    }

    public UserSimpleInfo(String memberId) {
        this.memberId = memberId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public float getProfitCt() {
        return profitCt;
    }

    public void setProfitCt(float profitCt) {
        this.profitCt = profitCt;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public float getInvolveCt() {
        return involveCt;
    }

    public void setInvolveCt(float involveCt) {
        this.involveCt = involveCt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getRankType() {
        return rankType;
    }

    public void setRankType(int rankType) {
        this.rankType = (this.rankType | rankType);     //排行榜设置
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isUpdated() {
        return isUpdated;
    }
    public void setUpdated() {
        this.isUpdated = true;
    }
    public void resetUpdated() {
        isUpdated = false;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean isInteresting() {
        return this.isInteresting;
    }

    public void setInteresting(boolean interesting) {
        this.isInteresting = interesting;
    }

    @Override
    public String toString() {
        return "UserSimpleInfo{" +
                "memberId='" + memberId + '\'' +
                ", amount='" + amount + '\'' +
                ", credentialId='" + credentialId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", rank='" + rank + '\'' +
                ", userName='" + userName + '\'' +
                ", ratio='" + ratio + '\'' +
                ", headImg='" + headImg + '\'' +
                ", profitCt=" + profitCt +
                ", involveCt=" + involveCt +
                ", rankType=" + rankType +
                ", isUpdated=" + isUpdated +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isInteresting=" + isInteresting +
                '}';
    }

    @Override
    public int compareTo(UserSimpleInfo o) {
        return (int)((Float.parseFloat(o.amount.substring(1)) - Float.parseFloat(this.amount.substring(1))) * 10);
    }
}
