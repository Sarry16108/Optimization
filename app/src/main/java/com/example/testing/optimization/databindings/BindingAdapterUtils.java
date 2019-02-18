package com.example.testing.optimization.databindings;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testing.optimization.R;
import com.example.testing.optimization.entity.PersonalPub;
import com.example.testing.optimization.entity.StockInfo;
import com.example.testing.optimization.globaldata.InitData;
import com.example.testing.optimization.utils.GlobalResourceUtils;
import com.example.testing.optimization.utils.ImageUtils;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BindingAdapterUtils {

    //买卖记录页
    @BindingAdapter("buyItemTextAndColor")    //true：红，false:黑
    public static void buyItemTextAndColor(TextView view, PersonalPub.FbbTodayItemListBean.StockItemInfo stockInfo) {

        if (InitData.RecordStatusDraft.equals(stockInfo.getUserRecordStatus())) {
            view.setTextColor(Color.RED);
            if (TextUtils.isEmpty(stockInfo.getBuyDealTime())) {
                view.setText("挂单");
            } else {
                view.setText("买 " + stockInfo.getBuyDealTime());
            }
        } else {
            view.setText("卖 " + stockInfo.getSellDealTime());
            view.setTextColor(Color.GREEN);
        }
    }

    @BindingAdapter("itemStatusTextAndColor")    //true：红，false:灰
    public static void itemStatusTextAndColor(TextView view, String userRecordStatus) {
        if (InitData.RecordStatusDraft.equals(userRecordStatus)) {
            view.setTextColor(Color.RED);
            view.setText("持仓中");
        } else {
            view.setText("已结算");
            view.setTextColor(Color.GRAY);
        }
    }

    @BindingAdapter("img_url")
    public static void roundImageByUrl(ImageView imageView, String url) {
        ImageUtils.showRoundImage(imageView, url);
    }

    @BindingAdapter("textColor")
    public static void textColor(TextView view, boolean isSpecial) {
        view.setTextColor(GlobalResourceUtils.getColor(isSpecial ? R.color.app_text_gray : R.color.app_text_light_gray));
    }

    //推荐页推荐项颜色
    @BindingAdapter({"recommandTextColor", "isBull"})
    public static void recommandTextColor(TextView view, StockInfo stockInfo, boolean isBull) {
        //1、人数多
        //2、赚的多（这条先不加上）
        if (isBull && stockInfo.getPeopleBullCount() > stockInfo.getPeopleBearCount()/* && stockInfo.getBullProfitMoney() > stockInfo.getBearProfitMoney()*/) {
            view.setTextColor(GlobalResourceUtils.getColor(R.color.app_text_gray));
        } else if (!isBull && stockInfo.getPeopleBullCount() < stockInfo.getPeopleBearCount()/* && stockInfo.getBullProfitMoney() < stockInfo.getBearProfitMoney()*/) {
            view.setTextColor(GlobalResourceUtils.getColor(R.color.app_text_gray));
        } else {
            view.setTextColor(GlobalResourceUtils.getColor(R.color.app_text_light_gray));
        }
    }

    @BindingAdapter("visibility")
    public static void viewVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
