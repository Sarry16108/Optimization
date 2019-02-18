package com.example.testing.optimization.collections;


import com.example.testing.optimization.entity.StockInfo;

import java.util.Comparator;

/**
 * Created by yanghj on 2017/6/3.
 */

public class SortComparator implements Comparator<StockInfo> {
    @Override
    public int compare(StockInfo left, StockInfo right) {
        return (int)(left.getHotDegree() - right.getHotDegree());
    }
}
