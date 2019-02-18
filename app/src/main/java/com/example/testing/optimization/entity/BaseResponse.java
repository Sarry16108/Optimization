package com.example.testing.optimization.entity;

import com.example.testing.optimization.utils.GsonUtils;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/6/1.
 */
public class BaseResponse{

    /**
     * success : true
     * message : 获取成功
     */

    private boolean success;
    private String message;
    protected JsonObject result;


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


    public <T extends BaseResultBean> T getResult(Class<T> clsType) {
        if (result.isJsonNull()) {
            return null;
        } else {
            return GsonUtils.castJsonObject(result.toString(), clsType);
        }
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
