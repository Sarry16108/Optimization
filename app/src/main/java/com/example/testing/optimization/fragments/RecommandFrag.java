package com.example.testing.optimization.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.testing.optimization.entity.StockInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class RecommandFrag extends BaseFragment {

    private FragNewBuyBinding mBinding;
    private CommonAdapter<StockInfo> mAdapter;
    private List<StockInfo>  mStocks;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_new_buy, null, false);
        return mBinding.getRoot();
    }

    public void initData(Context context, List<StockInfo>  stocks) {
        mAdapter = new CommonAdapter<StockInfo>(context, R.layout.layout_item_recommand, stocks) {
            @Override
            public void convert(NewViewHolder holder, int pos, StockInfo stockInfo) {
                holder.mBinding.setVariable(BR.stockInfo, stockInfo);
            }
        };

        mAdapter.setOnClickListener(new CommonAdapter.OnClickListener<StockInfo>() {
            @Override
            public void onClick(CommonAdapter.NewViewHolder holder, int postion, StockInfo stockInfo) {
//                PresenterManager.toStockDetail((BaseActivity) getActivity(), stockInfo.getStockInfo(), stockInfo.getPersonInfo());
            }
        });

        mBinding.showDatas.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mBinding.showDatas.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mBinding.showDatas.setAdapter(mAdapter);
    }

    public void update() {
        mAdapter.update();
    }
}
