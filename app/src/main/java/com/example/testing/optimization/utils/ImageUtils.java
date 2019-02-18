package com.example.testing.optimization.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.testing.optimization.R;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ImageUtils {

    public static Bitmap getImgByBase64(String datas) {
        byte [] bytes = Base64.decode(datas.getBytes(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    //保存成memberId.jpeg，不分datas中格式
    public static String saveImgByBase64(String datas, String memberId) {
        int startPos = datas.indexOf(",");
        if (0 < startPos) {
            startPos += 1;
        } else {
            startPos = 0;
        }

        String path = FileUtils.getImagePath() + memberId + ".jpeg";
        datas = datas.substring(startPos);
        byte [] bytes = Base64.decode(datas.getBytes(), Base64.DEFAULT);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            path = "";
        }

        return path;
    }

    private static RequestOptions mRequestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .fitCenter()
            .circleCrop()
            .format(DecodeFormat.PREFER_ARGB_8888)      //如果设置为rgb565,则没有透明度，剪切后背景为黑色
            .placeholder(R.mipmap.ic_user_default);

    //glide的封装
    public static void showRoundImage(ImageView view, String path) {
        Glide.with(view.getContext())
                .setDefaultRequestOptions(mRequestOptions)
                .load(path)
                .into(view);
    }
}
