package com.example.testing.optimization.entity;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface NetCallback extends NetSuccessCallback{
    void onError(String method, int connCode, String data);
}
