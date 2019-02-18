package com.example.testing.optimization.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testing.optimization.BR;
import com.example.testing.optimization.R;
import com.example.testing.optimization.ToolRequest;
import com.example.testing.optimization.adapters.CommonAdapter;
import com.example.testing.optimization.base.BaseApplication;
import com.example.testing.optimization.base.BaseFragment;
import com.example.testing.optimization.base.PresenterManager;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.databinding.FragRevenueRankBinding;
import com.example.testing.optimization.entity.BaseResultBean;
import com.example.testing.optimization.entity.NetCallback;
import com.example.testing.optimization.entity.NewBuyRecord;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.RankInfo;
import com.example.testing.optimization.entity.UserImg;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.globaldata.MessageId;
import com.example.testing.optimization.utils.ToastUtils;
import com.example.testing.optimization.utils.ToolLog;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.example.testing.optimization.globaldata.InitNetInfo.MethodPersonPubInfo;

/**
 * Created by Administrator on 2017/6/8.
 */

public class RevenueRankFrag extends BaseFragment implements View.OnClickListener, NetCallback{

    private FragRevenueRankBinding  mBinding;
    private CommonAdapter<UserSimpleInfo> mAdapter;
    private String  mUserId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_revenue_rank, null, false);
        return mBinding.getRoot();
    }


    public void initData(Context context) {
        mBinding.setVariable(BR.onBindClick, this);

        mAdapter = new CommonAdapter<UserSimpleInfo>(context, R.layout.layout_item_revenue_rank, null) {
            @Override
            public void convert(NewViewHolder holder, int pos, UserSimpleInfo userSimpleInfo) {
                holder.mBinding.setVariable(BR.personInfo, userSimpleInfo);
                holder.mBinding.setVariable(BR.rank, pos + 1);
                holder.mBinding.setVariable(BR.isInteresting, userSimpleInfo.isInteresting());
            }
        };

        mAdapter.setOnClickListener(new CommonAdapter.OnClickListener<UserSimpleInfo>() {
            @Override
            public void onClick(CommonAdapter.NewViewHolder holder, int postion, UserSimpleInfo userSimpleInfo) {
                userSimpleInfo.setRank(String.valueOf(postion + 1));
                PresenterManager.toPersonDetail((BaseActivity) getActivity(), userSimpleInfo);
            }
        });

        mAdapter.setOnLongClickListener(new CommonAdapter.OnLongClickListener<UserSimpleInfo>() {
            @Override
            public void onClick(CommonAdapter.NewViewHolder holder, int postion, UserSimpleInfo userSimpleInfo) {
                boolean isInteresting = !userSimpleInfo.isInteresting();
                userSimpleInfo.setInteresting(isInteresting);
                ToastUtils.longToast(isInteresting ? "关注成功" : "取消关注成功");
                holder.mBinding.setVariable(BR.isInteresting, isInteresting);

                //保存状态
                updateInterest(isInteresting, userSimpleInfo);
            }
        });

        mBinding.showDatas.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mBinding.showDatas.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBinding.showDatas.setAdapter(mAdapter);
    }

    private void updateInterest(boolean isInteresting, UserSimpleInfo userSimpleInfo) {
        if (BaseApplication.mSavedUserImgs.containsKey(userSimpleInfo.getMemberId())) {
            BaseApplication.mSavedUserImgs.get(userSimpleInfo.getMemberId()).setInterest(isInteresting);
        } else {
            UserImg userImg = new UserImg(userSimpleInfo.getHeadImg(), userSimpleInfo.getUserName(),
                        userSimpleInfo.getMobile(), userSimpleInfo.getCredentialId(), isInteresting, userSimpleInfo.getMemberId());
            BaseApplication.mSavedUserImgs.put(userSimpleInfo.getMemberId(), userImg);
        }

        if (isInteresting) {
            BaseApplication.mIntrestUsers.put(userSimpleInfo.getMemberId(), userSimpleInfo);
        } else {
            BaseApplication.mIntrestUsers.remove(userSimpleInfo.getMemberId());
        }
    }

    public void updateData(Collection<UserSimpleInfo> records) {
        List<UserSimpleInfo>    list = mAdapter.getDatas();
        list.clear();
        list.addAll(records);
        Collections.sort(list);
        mAdapter.update();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addPerson:
                mUserId = mBinding.personId.getText().toString();
                if (TextUtils.isEmpty(mUserId)) {
                    ToastUtils.longToast("id不能为空");
                    return;
                }

                ToolRequest.getInstance().getMemberPub(mUserId, InitData.ContainAll, this);
                break;
        }
    }

    @Override
    public void onError(String method, int connCode, String data) {
        ((BaseActivity)getActivity()).packMsgAndSend(MessageId.TOAST_TIP, "关注的人不存在");
    }

    @Override
    public void onSuccess(String method, BaseResultBean data) {
        switch (method) {
            case MethodPersonPubInfo:
                UserSimpleInfo userSimpleInfo = new UserSimpleInfo(mUserId);
                BaseApplication.mIntrestUsers.put(userSimpleInfo.getMemberId(), userSimpleInfo);
                UserImg userImg = new UserImg(userSimpleInfo.getHeadImg(), userSimpleInfo.getUserName(),
                        userSimpleInfo.getMobile(), userSimpleInfo.getCredentialId(), true, userSimpleInfo.getMemberId());
                BaseApplication.mSavedUserImgs.put(userSimpleInfo.getMemberId(), userImg);
                ((BaseActivity)getActivity()).packMsgAndSend(MessageId.TOAST_TIP, "关注成功");
                break;
        }
    }
}
