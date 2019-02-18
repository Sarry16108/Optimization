package com.example.testing.optimization.entity;

/**
 * Created by Administrator on 2017/6/9.
 */

public class UserImg extends BaseResultBean {
    /**
     * headImg : http://wx.qlogo.cn/mmopen/ajNVdqHZLLDOlUeA1MvpXOqDRbmia1Q1cngTiaLUWqobiaDPdHdIFI91qzwbT3RoyDddLpE7ywvDKB5O9hibianmb3g/0
     * nickname : 潋滟湖色
     * mobile : 131*****999
     * credentialId : 28133
     * memberId : 12234
     */

    private String headImg;
    private String nickname;
    private String mobile;
    private String credentialId;
    private String memberId;
    private boolean isInterest;     //true：关注，感兴趣

    public UserImg() {
    }

    public UserImg(String headImg, String nickname, String mobile, String credentialId, boolean isInterest, String memberId) {
        this.headImg = headImg;
        this.nickname = nickname;
        this.mobile = mobile;
        this.credentialId = credentialId;
        this.isInterest = isInterest;
        this.memberId = memberId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean isInterest() {
        return isInterest;
    }

    public void setInterest(boolean interest) {
        isInterest = interest;
    }

    @Override
    public String toString() {
        return "UserImg{" +
                "headImg='" + headImg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", credentialId='" + credentialId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
