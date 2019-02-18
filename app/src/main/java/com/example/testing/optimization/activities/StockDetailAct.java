package com.example.testing.optimization.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.testing.optimization.BR;
import com.example.testing.optimization.R;
import com.example.testing.optimization.baseact.BaseActivity;
import com.example.testing.optimization.databinding.ActStockDetailBinding;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.UserSimpleInfo;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StockDetailAct extends BaseActivity {

    private ActStockDetailBinding mBinding;
    private PersonalPub.FbbTodayItemListBean.StockItemInfo mStockInfo;
    private UserSimpleInfo mPersonInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStockInfo = (PersonalPub.FbbTodayItemListBean.StockItemInfo) getIntent().getSerializableExtra("stockInfo");
        mPersonInfo = (UserSimpleInfo) getIntent().getSerializableExtra("personInfo");
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_stock_detail);
        mBinding.setVariable(BR.personInfo, mPersonInfo);
        mBinding.setVariable(BR.stockInfo, mStockInfo);
    }


}
