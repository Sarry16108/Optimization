package com.example.testing.optimization.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.testing.optimization.BR;
import com.example.testing.optimization.R;
import com.example.testing.optimization.ToolRequest;
import com.example.testing.optimization.adapters.CommonAdapter;
import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.base.PresenterManager;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.databinding.ActMemberPubBinding;
import com.example.testing.optimization.entity.BaseResultBean;
import com.example.testing.optimization.entity.NetCallback;
import com.example.testing.optimization.entity.NewBuyRecord;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.globaldata.MessageId;
import com.example.testing.optimization.utils.ToastUtils;
import com.example.testing.optimization.utils.ToolLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */

public class MemberPubAct extends BaseActivity implements NetCallback {

    private ActMemberPubBinding  mActBinding;
    private List<PersonalPub.FbbTodayItemListBean.StockItemInfo>    mStocks = new ArrayList<>(10);
    private CommonAdapter<PersonalPub.FbbTodayItemListBean.StockItemInfo>   mAdapter;
    private UserSimpleInfo  mUserInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActBinding = DataBindingUtil.setContentView(this, R.layout.act_member_pub);

        mUserInfo = (UserSimpleInfo) getIntent().getSerializableExtra("userInfo");
        mActBinding.setVariable(BR.personInfo, mUserInfo);

        initViews();
        ToolRequest.getInstance().getMemberPub(mUserInfo.getMemberId(), InitData.ContainAll, this);
    }

    private void initViews() {
        mAdapter = new CommonAdapter<PersonalPub.FbbTodayItemListBean.StockItemInfo>(this, R.layout.layout_item_member_day_pub, mStocks) {
            @Override
            public void convert(NewViewHolder holder, int pos, PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo) {
                holder.mBinding.setVariable(BR.stockInfo, stockInfo);
            }
        };

        mActBinding.showDatas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mActBinding.showDatas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mActBinding.showDatas.setAdapter(mAdapter);
    }


    @Override
    public void onSuccess(String method, BaseResultBean data) {
        if (null == data) {
            ToolLog.e(TAG, "onSuccess", method,  "data is null");
            return;
        }

        PersonalPub personalPub = (PersonalPub) data;
        if (null == personalPub || null == personalPub.getFbbTodayItemList() || null == personalPub.getFbbTodayItemList().getList()) {
            packMsgAndSend(MessageId.TOAST_TIP, "这个人已经离开排行榜前20了！！！");
            mStocks.addAll(BaseApplication.mMemberDetails.get(mUserInfo.getMemberId()));    //查不到信息的，则显示缓存的数据。
        } else {
            mStocks.addAll(personalPub.getFbbTodayItemList().getList());
        }


        mHandler.sendEmptyMessage(MessageId.SHOW_DATA_LIST);
    }

    @Override
    public void onError(String method, int connCode, String data) {
        packMsgAndSend(MessageId.TOAST_TIP, data);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (super.handleMessage(msg)) {
            return true;
        }

        switch (msg.what) {
            case MessageId.SHOW_DATA_LIST:
                mAdapter.update();
                break;
        }

        return true;
    }
}
