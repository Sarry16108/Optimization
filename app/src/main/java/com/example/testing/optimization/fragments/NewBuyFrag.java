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
import com.example.testing.optimization.adapters.CommonAdapter;
import com.example.testing.optimization.base.BaseFragment;
import com.example.testing.optimization.base.PresenterManager;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.databinding.FragNewBuyBinding;
import com.example.testing.optimization.entity.NewBuyRecord;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.ClipboardUtils;
import com.example.testing.optimization.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class NewBuyFrag extends BaseFragment {

    private FragNewBuyBinding mBinding;
    private CommonAdapter<NewBuyRecord> mAdapter;
    private List<NewBuyRecord>  mRecords;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_new_buy, container, false);
        return mBinding.getRoot();
    }

    public void initData(Context context, List<NewBuyRecord>  records) {
        mAdapter = new CommonAdapter<NewBuyRecord>(context, R.layout.layout_item_buy_record, records) {
            @Override
            public void convert(NewViewHolder holder, int pos, NewBuyRecord stockInfo) {
                holder.mBinding.setVariable(BR.personInfo, stockInfo.getPersonInfo());
                holder.mBinding.setVariable(BR.stockInfo, stockInfo.getStockInfo());
            }
        };
        mAdapter.setMaxSize(150);

        mAdapter.setOnClickListener(new CommonAdapter.OnClickListener<NewBuyRecord>() {
            @Override
            public void onClick(CommonAdapter.NewViewHolder holder, int postion, NewBuyRecord stockInfo) {
                PresenterManager.toStockDetail((BaseActivity) getActivity(), stockInfo.getStockInfo(), stockInfo.getPersonInfo());
            }
        });
        mAdapter.setOnLongClickListener(new CommonAdapter.OnLongClickListener<NewBuyRecord>() {
            @Override
            public void onClick(CommonAdapter.NewViewHolder holder, int postion, NewBuyRecord stockInfo) {
                UserSimpleInfo userSimpleInfo = stockInfo.getPersonInfo();
                PersonalPub.FbbTodayItemListBean.StockItemInfo stockItemInfo = stockInfo.getStockInfo();
                StringBuilder value = new StringBuilder(userSimpleInfo.getUserName() + "(排名第" + userSimpleInfo.getRank() + ")");
                if (TextUtils.isEmpty(stockItemInfo.getBuyDealTime())) {
                    value.append("挂单");
                } else {
                    value.append("在");
                    value.append(InitData.RecordStatusDraft.equals(stockItemInfo.getUserRecordStatus()) ? (stockItemInfo.getBuyDealTime() + "买入") : (stockItemInfo.getSellDealTime() + "卖出"));
                }

                value.append(InitData.BuyTypeBull.equals(stockItemInfo.getBullBearType()) ? "看多" : "看空");
                value.append(stockItemInfo.getName() + stockItemInfo.getCountText());
                value.append("，收益：$" + stockItemInfo.getCurrentProfit());
                if (ClipboardUtils.copyToClipboard(value.toString())) {
                    ToastUtils.longToast("复制成功");
                }
            }
        });

        mBinding.showDatas.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mBinding.showDatas.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBinding.showDatas.setAdapter(mAdapter);
    }

    public void addNewRecord(NewBuyRecord newBuyRecord) {
        mAdapter.addHeadItem(newBuyRecord);
    }
}
