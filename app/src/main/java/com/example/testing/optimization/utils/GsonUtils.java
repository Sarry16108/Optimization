package com.example.testing.optimization.utils;


import android.support.v4.util.ArrayMap;

import com.example.testing.optimization.entity.UserImg;
import com.example.testing.optimization.entity.UserSimpleInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */

public class GsonUtils {
    private static Gson mGson = new Gson();


    public static String castObjectJson(Object object) {
        return mGson.toJson(object);
    }

    public static <T> T castJsonObject(String json, Class<T> type) {
        return mGson.fromJson(json, type);
    }

    public static <T> List<T> castJsonObjList(String json, Class<T> type) {
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        ArrayList<T> arrayList = new ArrayList<>(jsonArray.size());
        for (final JsonElement element : jsonArray) {
            arrayList.add(mGson.fromJson(element, type));
        }
        return arrayList;
    }


    public static <T> List<T> castJsonObjList2(String json, Class<T> type) {
        return mGson.fromJson(json, new TypeToken<List<T>>() {}.getType());
    }

    public static <T> String castMapToJson(Map<String, T> map) {
        String jsonStr = mGson.toJson(map);
        return jsonStr;
    }

    public static <T> Map<String, T> castJsonToMap(String json, Class<T> type) {
        Type inType = new TypeToken<Map<String, T>>() {}.getType();
        return mGson.fromJson(json, inType);
    }

    public static Map<String, UserSimpleInfo> castJsonToMapUserSimpleInfo(String json) {
        Type inType = new TypeToken<Map<String, UserSimpleInfo>>() {}.getType();
        return mGson.fromJson(json, inType);
    }

    public static Map<String, UserImg> castJsonToMapUserImg(String json) {
        Type inType = new TypeToken<Map<String, UserImg>>() {}.getType();
        return mGson.fromJson(json, inType);
    }
}
